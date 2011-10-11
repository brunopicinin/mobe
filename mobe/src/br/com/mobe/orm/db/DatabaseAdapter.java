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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import br.com.mobe.core.exception.IllegalMetadataException;
import br.com.mobe.core.exception.UnsupportedTypeException;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Property;
import br.com.mobe.core.metadata.Repository;
import br.com.mobe.orm.exception.DatabaseException;

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

	public void createTables(Set<Class<?>> classes) throws SQLiteException, UnsupportedTypeException, IllegalMetadataException {
		DatabaseBuilder builder = new DatabaseBuilder();
		for (Class<?> clazz : classes) {
			builder.addTable(clazz);
		}
		dbHelper = new DatabaseHelper(context, dbName, dbVersion, builder);
		database = dbHelper.getWritableDatabase();
	}

	public void open() throws SQLException {
		dbHelper = new DatabaseHelper(context, dbName, dbVersion);
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public long save(Object object) {
		String table = DbUtils.getTableName(object.getClass());
		ContentValues values = getContentValues(object);
		return database.insert(table, null, values);
	}

	private ContentValues getContentValues(Object object) {
		Class<? extends Object> clazz = object.getClass();
		ContentValues values = new ContentValues();
		ClassMetadata metadata = Repository.getInstance().getMetadata(clazz);
		List<Property> properties = metadata.getProperties();
		for (Property property : properties) {
			try {
				String name = property.getName();
				Field field = clazz.getDeclaredField(name);
				field.setAccessible(true);
				Object value = field.get(object);
				if (value == null) {
					values.putNull(name);
				} else {
					Class<?> type = value.getClass();
					if (isBoolean(type)) {
						values.put(name, (Boolean) value);
					} else if (isByte(type)) {
						values.put(name, (Byte) value);
					} else if (isShort(type)) {
						values.put(name, (Short) value);
					} else if (isInt(type)) {
						values.put(name, (Integer) value);
					} else if (isLong(type)) {
						values.put(name, (Long) value);
					} else if (isFloat(type)) {
						values.put(name, (Float) value);
					} else if (isDouble(type)) {
						values.put(name, (Double) value);
					} else if (isChar(type) || isString(type)) {
						values.put(name, String.valueOf(value));
					} else if (isCalendar(type)) {
						values.put(name, ((Calendar) value).getTimeInMillis());
					} else if (isDate(type)) {
						values.put(name, ((Date) value).getTime());
					} else {
						UnsupportedTypeException ex = new UnsupportedTypeException(type);
						Log.e(TAG, "Class changed since table was created.", ex);
					}
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
				Class<?> type = property.getType();

				String propertyName = property.getName();
				String colName = DbUtils.getColumnName(propertyName);
				int columnIndex = cursor.getColumnIndexOrThrow(colName);

				Field field = object.getClass().getDeclaredField(propertyName);
				field.setAccessible(true);

				if (isBoolean(type)) {
					int i = cursor.getInt(columnIndex);
					field.set(object, i != 0);
				} else if (isByte(type)) {
					byte b = (byte) cursor.getInt(columnIndex);
					field.set(object, b);
				} else if (isShort(type)) {
					short s = cursor.getShort(columnIndex);
					field.set(object, s);
				} else if (isInt(type)) {
					int i = cursor.getInt(columnIndex);
					field.set(object, i);
				} else if (isLong(type)) {
					long l = cursor.getLong(columnIndex);
					field.set(object, l);
				} else if (isFloat(type)) {
					float f = cursor.getFloat(columnIndex);
					field.set(object, f);
				} else if (isDouble(type)) {
					double d = cursor.getDouble(columnIndex);
					field.set(object, d);
				} else if (isChar(type)) {
					char c = cursor.getString(columnIndex).charAt(0);
					field.set(object, c);
				} else if (isString(type)) {
					String s = cursor.getString(columnIndex);
					field.set(object, s);
				} else if (isCalendar(type)) {
					long l = cursor.getLong(columnIndex);
					Calendar calendar = Calendar.getInstance();
					calendar.setTimeInMillis(l);
					field.set(object, calendar);
				} else if (isDate(type)) {
					long l = cursor.getLong(columnIndex);
					Date date = new Date(l);
					field.set(object, date);
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean delete(Object object) throws DatabaseException {
		String[][] params = getPkQueryParams(object);
		return database.delete(params[0][0], params[1][0], params[2]) > 0;
	}

	/**
	 * Get parameters to execute a query with primary key arguments.
	 * 
	 * @param object
	 * @return An array in the form {{table}, {whereClause}, whereArgs}
	 * @throws DatabaseException
	 */
	private String[][] getPkQueryParams(Object object) throws DatabaseException {
		Class<?> clazz = object.getClass();
		String table = DbUtils.getTableName(clazz); // First parameter
		ClassMetadata metadata = Repository.getInstance().getMetadata(clazz);
		if (!metadata.hasPrimaryKey()) {
			throw new DatabaseException("Invalid object. No primary key.");
		}
		StringBuilder whereClause = new StringBuilder(); // Second parameter
		List<String> whereArgs = new ArrayList<String>(); // Third parameter
		List<Property> properties = metadata.getProperties();
		for (Property property : properties) {
			if (property.isPrimaryKey()) {
				String name = property.getName();
				try {
					Field field = clazz.getDeclaredField(name);
					field.setAccessible(true);
					Object value = field.get(object);
					if (value == null) {
						throw new DatabaseException("Invalid object. Null primary key.");
					}
					Class<?> type = value.getClass();
					if (isBoolean(type) || isByte(type) || isShort(type) || isInt(type) || isLong(type) || isFloat(type) || isDouble(type) || isChar(type) || isString(type)) {
						whereArgs.add(String.valueOf(value));
					} else if (isCalendar(type)) {
						whereArgs.add(String.valueOf(((Calendar) value).getTimeInMillis()));
					} else if (isDate(type)) {
						whereArgs.add(String.valueOf(((Date) value).getTime()));
					}
					whereClause.append(DbUtils.getColumnName(name)).append("=? AND ");
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
		int length = whereClause.length();
		whereClause.delete(length - 5, length);
		String[] p1 = { table };
		String[] p2 = { whereClause.toString() };
		String[] p3 = whereArgs.toArray(new String[0]);
		String[][] res = { p1, p2, p3 };
		return res;
	}

	public boolean update(Object object) throws DatabaseException {
		String[][] params = getPkQueryParams(object);
		ContentValues values = getContentValues(object);
		return database.update(params[0][0], values, params[1][0], params[2]) > 0;
	}
}
