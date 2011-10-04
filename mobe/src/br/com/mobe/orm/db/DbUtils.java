package br.com.mobe.orm.db;

public class DbUtils {

	public static String getTableName(Class<?> clazz) {
		return clazz.getSimpleName().toLowerCase();
	}

	public static String getColumnName(String propertyName) {
		return propertyName.toLowerCase();
	}

}
