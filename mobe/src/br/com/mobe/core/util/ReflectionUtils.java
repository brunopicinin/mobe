package br.com.mobe.core.util;

import java.util.Calendar;
import java.util.Date;

@Deprecated
public class ReflectionUtils {

	public static boolean isBoolean(Class<?> type) {
		return typeof(type, boolean.class, Boolean.class);
	}

	public static boolean isByte(Class<?> type) {
		return typeof(type, byte.class, Byte.class);
	}

	public static boolean isShort(Class<?> type) {
		return typeof(type, short.class, Short.class);
	}

	public static boolean isInt(Class<?> type) {
		return typeof(type, int.class, Integer.class);
	}

	public static boolean isLong(Class<?> type) {
		return typeof(type, long.class, Long.class);
	}

	public static boolean isFloat(Class<?> type) {
		return typeof(type, float.class, Float.class);
	}

	public static boolean isDouble(Class<?> type) {
		return typeof(type, double.class, Double.class);
	}

	public static boolean isChar(Class<?> type) {
		return typeof(type, char.class, Character.class);
	}

	public static boolean isString(Class<?> type) {
		return typeof(type, String.class);
	}

	public static boolean isCalendar(Class<?> type) {
		return typeof(type, Calendar.class);
	}

	public static boolean isDate(Class<?> type) {
		return typeof(type, Date.class);
	}

	private static boolean typeof(Class<?> clazz, Class<?>... types) {
		for (Class<?> c : types) {
			if (c.isAssignableFrom(clazz)) {
				return true;
			}
		}
		return false;
	}

}
