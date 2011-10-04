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
import br.com.mobe.core.exception.UnsupportedTypeException;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Property;
import br.com.mobe.core.metadata.Repository;

public class DatabaseAdapter {

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

	public void createTables(Set<Class<?>> classes) throws SQLiteException, UnsupportedTypeException {
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
		// TODO Where to call this?
		dbHelper.close();
	}

	public long save(Object object) throws UnsupportedTypeException {
		// TODO Check if tables are already created
		ContentValues values = new ContentValues();
		Class<? extends Object> clazz = object.getClass();
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
						throw new UnsupportedTypeException(type);
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
		String table = clazz.getSimpleName().toLowerCase();
		return database.insert(table, null, values);
	}

	public <E> List<E> list(Class<E> clazz) {
		String table = clazz.getSimpleName().toLowerCase();
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
				populate(object, cursor, metadata);
				list.add(object);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}
			cursor.moveToNext();
		}
		return list;
	}

	private static void populate(Object object, Cursor cursor, ClassMetadata metadata) {
		List<Property> properties = metadata.getProperties();
		for (Property property : properties) {
			try {
				Class<?> type = property.getType();

				String name = property.getName();
				String colName = name.toLowerCase();
				int columnIndex = cursor.getColumnIndexOrThrow(colName);

				Field field = object.getClass().getDeclaredField(name);
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
}
