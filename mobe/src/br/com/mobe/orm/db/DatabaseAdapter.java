package br.com.mobe.orm.db;

import static br.com.mobe.core.util.ReflectionUtils.isBoolean;
import static br.com.mobe.core.util.ReflectionUtils.isByte;
import static br.com.mobe.core.util.ReflectionUtils.isCalendar;
import static br.com.mobe.core.util.ReflectionUtils.isChar;
import static br.com.mobe.core.util.ReflectionUtils.isDate;
import static br.com.mobe.core.util.ReflectionUtils.isDouble;
import static br.com.mobe.core.util.ReflectionUtils.isFloat;
import static br.com.mobe.core.util.ReflectionUtils.isInt;
import static br.com.mobe.core.util.ReflectionUtils.isLong;
import static br.com.mobe.core.util.ReflectionUtils.isShort;
import static br.com.mobe.core.util.ReflectionUtils.isString;

import java.lang.reflect.Field;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import br.com.mobe.core.exception.IllegalMetadataException;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Property;
import br.com.mobe.core.metadata.Repository;
import br.com.mobe.orm.exception.IllegalQueryException;

public class DatabaseAdapter {

	public static final String TAG = "DatabaseAdapter";

	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;

	private final Context context;
	private String dbName;
	private int dbVersion;

	public DatabaseAdapter(Context context, String dbName, int dbVersion) {
		this.context = context;
		this.dbName = dbName;
		this.dbVersion = dbVersion;
	}

	public void createTables(Set<Class<?>> classes) {
		DatabaseBuilder builder = new DatabaseBuilder();
		for (Class<?> clazz : classes) {
			builder.addTable(clazz);
		}
		dbHelper = new DatabaseHelper(context, dbName, dbVersion, builder);
		database = dbHelper.getWritableDatabase();
	}

	public void open() {
		dbHelper = new DatabaseHelper(context, dbName, dbVersion);
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public long save(Object object) {
		String table = DbUtils.getTableName(object.getClass());
		ContentValues values = getContentValues(object, false);
		long rowid = database.insert(table, null, values);
		updateId(object, rowid);
		return rowid;
	}

	private void updateId(Object object, long rowid) {
		Class<? extends Object> clazz = object.getClass();
		ClassMetadata metadata = Repository.getInstance().getMetadata(clazz);
		Property primaryKey = metadata.getPrimaryKey();
		// update only AutoIncrement Id
		if (primaryKey != null && primaryKey.isAutoIncrement()) {
			try {
				Field field = clazz.getDeclaredField(primaryKey.getName());
				field.setAccessible(true);
				field.set(object, rowid);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private ContentValues getContentValues(Object object, boolean isUpdate) {
		Class<? extends Object> clazz = object.getClass();
		ContentValues values = new ContentValues();
		ClassMetadata metadata = Repository.getInstance().getMetadata(clazz);
		List<Property> properties = metadata.getProperties();
		for (Property property : properties) {
			if (isUpdate && property.isPrimaryKey()) {
				// update cannot change IDs
				continue;
			} else if (!isUpdate && property.isAutoIncrement()) {
				// save cannot change AutoIncrement
				continue;
			}
			try {
				String name = property.getName();
				Field field = clazz.getDeclaredField(name);
				field.setAccessible(true);
				Object value = field.get(object);
				if (property.isNotNull() && value == null) {
					throw new SQLiteException("Property " + name + " must be not null.");
				}
				if (value == null) {
					values.putNull(name);
				} else {
					property.getProcessor().putIn(values, name, value);
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return values;
	}

	public <E> List<E> list(Class<E> clazz) {
		String table = DbUtils.getTableName(clazz);
		Cursor cursor = database.query(table, null, null, null, null, null, null);
		if (cursor == null) {
			return null;
		}
		ClassMetadata metadata = Repository.getInstance().getMetadata(clazz);
		List<E> list = new ArrayList<E>();
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			try {
				E object = clazz.newInstance();
				populateObject(object, cursor, metadata);
				list.add(object);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				Log.e(TAG, "Impossible to instantiate object. Unaccessible empty construtctor in class " + clazz.getName() + ".");
			}
			cursor.moveToNext();
		}
		return list;
	}

	private static void populateObject(Object object, Cursor cursor, ClassMetadata metadata) {
		List<Property> properties = metadata.getProperties();
		for (Property property : properties) {
			try {
				String propertyName = property.getName();
				String colName = DbUtils.getColumnName(propertyName);
				int columnIndex = cursor.getColumnIndexOrThrow(colName);

				Field field = object.getClass().getDeclaredField(propertyName);
				field.setAccessible(true);
				field.set(object, property.getProcessor().getFrom(cursor, columnIndex));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
	}

	public <E> E findById(Class<E> clazz, Object id) {
		ClassMetadata metadata = Repository.getInstance().getMetadata(clazz);
		Property primaryKey = metadata.getPrimaryKey();
		if (primaryKey == null) {
			throw new IllegalMetadataException(clazz, "Impossible to find object without id.");
		}
		if (id == null) {
			throw new InvalidParameterException("Id must not be null.");
		}
		Class<?> pkType = primaryKey.getType();
		Class<?> idType = id.getClass();
		if (!equivalentType(pkType, idType)) {
			throw new InvalidParameterException("Id parameter is not of the same type of object id.");
		}
		String table = DbUtils.getTableName(clazz);
		String selection = primaryKey.getName() + "=?";
		String selectionArg = primaryKey.getProcessor().valueToString(id);
		Cursor cursor = database.query(table, null, selection, new String[] { selectionArg }, null, null, null);
		cursor.moveToFirst();
		try {
			E object = clazz.newInstance();
			populateObject(object, cursor, metadata);
			return object;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		}
	}

	private boolean equivalentType(Class<?> pkType, Class<?> idType) {
		if (pkType.equals(idType)) {
			return true;
		} else if (isBoolean(pkType) && isBoolean(idType)) {
			return true;
		} else if ((isByte(pkType) || isShort(pkType) || isInt(pkType) || isLong(pkType)) && (isByte(idType) || isShort(idType) || isInt(idType) || isLong(idType))) {
			return true;
		} else if ((isFloat(pkType) || isDouble(pkType)) && (isFloat(idType)) || isDouble(idType)) {
			return true;
		} else if (isChar(pkType) && isChar(idType)) {
			return true;
		} else if (isString(pkType) && isString(idType)) {
			return true;
		} else if (isCalendar(pkType) && isCalendar(idType)) {
			return true;
		} else if (isDate(pkType) && isDate(idType)) {
			return true;
		}
		return false;
	}

	public boolean delete(Object object) {
		String[] params = getPkQueryParams(object);
		return database.delete(params[0], params[1], new String[] { params[2] }) > 0;
	}

	/**
	 * @param object
	 * @return {table, whereClause, whereArgs}
	 */
	private String[] getPkQueryParams(Object object) {
		Class<?> clazz = object.getClass();
		String table = DbUtils.getTableName(clazz); // First parameter
		ClassMetadata metadata = Repository.getInstance().getMetadata(clazz);
		Property primaryKey = metadata.getPrimaryKey();
		if (primaryKey == null) {
			throw new IllegalMetadataException(clazz, "Invalid object. No primary key.");
		}
		String whereClause = null;
		String whereArgs = null;
		try {
			String name = primaryKey.getName();
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			Object value = field.get(object);
			if (value == null) {
				throw new IllegalQueryException("Id must not be null.");
			}
			whereClause = DbUtils.getColumnName(name) + "=?"; // Second parameter
			whereArgs = primaryKey.getProcessor().valueToString(value); // Third parameter
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return new String[] { table, whereClause, whereArgs };
	}

	public boolean update(Object object) {
		String[] params = getPkQueryParams(object);
		ContentValues values = getContentValues(object, true);
		return database.update(params[0], values, params[1], new String[] { params[2] }) > 0;
	}
}
