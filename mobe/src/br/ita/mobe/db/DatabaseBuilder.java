package br.ita.mobe.db;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.ita.mobe.exception.UnsupportedTypeException;
import br.ita.mobe.pattern.metadata.ClassMetadata;
import br.ita.mobe.pattern.metadata.PropertyDescriptor;
import br.ita.mobe.pattern.metadata.Repository;

public class DatabaseBuilder {

	// Field data types
	public static final Class<?>[] LOGIC_TYPES = { boolean.class, Boolean.class };
	public static final Class<?>[] INT_TYPES = { byte.class, Byte.class, short.class, Short.class, int.class, Integer.class, long.class, Long.class };
	public static final Class<?>[] DECIMAL_TYPES = { float.class, Float.class, double.class, Double.class };
	public static final Class<?>[] CHAR_TYPES = { char.class, Character.class };
	public static final Class<?>[] STRING_TYPES = { String.class };
	public static final Class<?>[] DATE_TYPES = { Calendar.class, Date.class };

	private Map<String, String[]> tables; // {table name: [sql create, sql drop]}

	public DatabaseBuilder() {
		tables = new HashMap<String, String[]>();
	}

	public void addTable(Class<?> clazz) throws UnsupportedTypeException {
		String name = clazz.getSimpleName();
		StringBuilder sb = new StringBuilder("create table ").append(name).append(" (");
		sb.append("id integer primary key autoincrement, ");

		ClassMetadata metadata = Repository.getInstance().getMetadata(clazz);
		List<PropertyDescriptor> properties = metadata.getProperties();
		for (PropertyDescriptor property : properties) {
			Class<?> type = property.getType();
			String propName = property.getName().toLowerCase();
			if (typeof(type, LOGIC_TYPES)) {
				sb.append(propName).append(" boolean, ");
			} else if (typeof(type, INT_TYPES)) {
				sb.append(propName).append(" integer, ");
			} else if (typeof(type, DECIMAL_TYPES)) {
				sb.append(propName).append(" real, ");
			} else if (typeof(type, CHAR_TYPES)) {
				sb.append(propName).append(" text, ");
			} else if (typeof(type, STRING_TYPES)) {
				sb.append(propName).append(" text, ");
			} else if (typeof(type, DATE_TYPES)) {
				sb.append(propName).append(" integer, ");
			} else {
				throw new UnsupportedTypeException();
			}
		}
		int length = sb.length();
		sb.delete(length - 2, length);
		sb.append(");");
		// TODO: fazer o sql drop
		String[] sql = { sb.toString(), "" };
		tables.put(name, sql);
	}

	public Set<String> getTables() {
		return tables.keySet();
	}

	public String getSQLCreate(String table) {
		return tables.get(table)[0];
	}

	public String getSQLDrop(String table) {
		return tables.get(table)[1];
	}

	public static boolean typeof(Class<?> clazz, Class<?>[] types) {
		for (Class<?> c : types) {
			if (c.equals(clazz)) {
				return true;
			}
		}
		return false;
	}

}
