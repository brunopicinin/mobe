package br.ita.mobe.db;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import br.ita.mobe.exception.UnsupportedTypeException;
import br.ita.mobe.pattern.metadata.ClassMetadata;
import br.ita.mobe.pattern.metadata.PropertyDescriptor;
import br.ita.mobe.pattern.metadata.Repository;

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
		List<PropertyDescriptor> properties = metadata.getProperties();
		for (PropertyDescriptor property : properties) {
			try {
				String name = property.getName();
				Field field = clazz.getDeclaredField(name);
				field.setAccessible(true);
				Object value = field.get(object);
				if (value == null) {
					values.putNull(name);
				} else {
					Class<?> valueType = value.getClass();
					// boolean types
					if (typeof(valueType, boolean.class, Boolean.class)) {
						values.put(name, (Boolean) value);
					}
					// integer types
					else if (typeof(valueType, byte.class, Byte.class)) {
						values.put(name, (Byte) value);
					} else if (typeof(valueType, short.class, Short.class)) {
						values.put(name, (Short) value);
					} else if (typeof(valueType, int.class, Integer.class)) {
						values.put(name, (Integer) value);
					} else if (typeof(valueType, long.class, Long.class)) {
						values.put(name, (Long) value);
					}
					// decimal types
					else if (typeof(valueType, float.class, Float.class)) {
						values.put(name, (Float) value);
					} else if (typeof(valueType, double.class, Double.class)) {
						values.put(name, (Double) value);
					}
					// char types
					else if (typeof(valueType, char.class, Character.class)) {
						values.put(name, String.valueOf(value));
					}
					// string type
					else if (typeof(valueType, String.class)) {
						values.put(name, String.valueOf(value));
					}
					// date types
					else if (typeof(valueType, Calendar.class)) {
						values.put(name, ((Calendar) value).getTimeInMillis());
					} else if (typeof(valueType, Date.class)) {
						values.put(name, ((Date) value).getTime());
					} else {
						throw new UnsupportedTypeException(valueType);
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

	public static boolean typeof(Class<?> clazz, Class<?>... types) {
		for (Class<?> c : types) {
			if (c.isAssignableFrom(clazz)) {
				return true;
			}
		}
		return false;
	}
}
