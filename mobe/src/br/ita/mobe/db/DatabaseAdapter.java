package br.ita.mobe.db;

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

	public <E> List<E> list(Class<E> cls) {
		String table = cls.getSimpleName().toLowerCase();
		Cursor cursor = database.query(table, null, null, null, null, null, null);
		if (cursor == null) {
			return null;
		}
		ClassMetadata metadata = Repository.getInstance().getMetadata(cls);
		List<E> list = new ArrayList<E>();
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			try {
				E object = cls.newInstance();
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
		List<PropertyDescriptor> properties = metadata.getProperties();
		for (PropertyDescriptor property : properties) {
			try {
				Class<?> type = property.getType();

				String name = property.getName();
				String colName = name.toLowerCase();
				int columnIndex = cursor.getColumnIndexOrThrow(colName);

				Field field = object.getClass().getDeclaredField(name);
				field.setAccessible(true);

				// boolean types
				if (typeof(type, boolean.class, Boolean.class)) {
					int i = cursor.getInt(columnIndex);
					field.set(object, i != 0);
				}
				// integer types
				else if (typeof(type, byte.class, Byte.class)) {
					byte b = (byte) cursor.getInt(columnIndex);
					field.set(object, b);
				} else if (typeof(type, short.class, Short.class)) {
					short s = cursor.getShort(columnIndex);
					field.set(object, s);
				} else if (typeof(type, int.class, Integer.class)) {
					int i = cursor.getInt(columnIndex);
					field.set(object, i);
				} else if (typeof(type, long.class, Long.class)) {
					long l = cursor.getLong(columnIndex);
					field.set(object, l);
				}
				// decimal types
				else if (typeof(type, float.class, Float.class)) {
					float f = cursor.getFloat(columnIndex);
					field.set(object, f);
				} else if (typeof(type, double.class, Double.class)) {
					double d = cursor.getDouble(columnIndex);
					field.set(object, d);
				}
				// char types
				else if (typeof(type, char.class, Character.class)) {
					char c = cursor.getString(columnIndex).charAt(0);
					field.set(object, c);
				}
				// string type
				else if (typeof(type, String.class)) {
					String s = cursor.getString(columnIndex);
					field.set(object, s);
				}
				// date types
				else if (typeof(type, Calendar.class)) {
					long l = cursor.getLong(columnIndex);
					Calendar calendar = Calendar.getInstance();
					calendar.setTimeInMillis(l);
					field.set(object, calendar);
				} else if (typeof(type, Date.class)) {
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
