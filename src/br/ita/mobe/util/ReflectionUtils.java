package br.ita.mobe.util;

import java.lang.reflect.Method;

public class ReflectionUtils {

	private static final Class<?>[] INT_TYPES = { int.class, Integer.class, short.class, Short.class, long.class, Long.class, byte.class, Byte.class };
	private static final Class<?>[] FLOAT_TYPES = { float.class, Float.class, double.class, Double.class };
	private static final Class<?>[] LOGIC_TYPES = { boolean.class, Boolean.class };
	private static final Class<?>[] CHAR_TYPES = { char.class, Character.class };
	private static final Class<?>[] STRING_TYPES = { String.class };

	public static boolean isIntegerType(Class<?> clazz) {
		return typeCheck(clazz, INT_TYPES);
	}

	public static boolean isFloatType(Class<?> clazz) {
		return typeCheck(clazz, FLOAT_TYPES);
	}

	public static boolean isBooleanType(Class<?> clazz) {
		return typeCheck(clazz, LOGIC_TYPES);
	}

	public static boolean isCharType(Class<?> clazz) {
		return typeCheck(clazz, CHAR_TYPES);
	}

	public static boolean isStringType(Class<?> clazz) {
		return typeCheck(clazz, STRING_TYPES);
	}

	private static boolean typeCheck(Class<?> clazz, Class<?>[] types) {
		for (Class<?> c2 : types) {
			if (c2.equals(clazz)) {
				return true;
			}
		}
		return false;
	}

	public static int castInt(Object value) {
		if (value.getClass().equals(int.class) || value.getClass().equals(Integer.class)) {
			return ((Integer) value).intValue();
		} else if (value.getClass().equals(short.class) || value.getClass().equals(Short.class)) {
			return ((Short) value).intValue();
		} else if (value.getClass().equals(long.class) || value.getClass().equals(Long.class)) {
			return ((Long) value).intValue();
		} else if (value.getClass().equals(byte.class) || value.getClass().equals(Byte.class)) {
			return ((Byte) value).intValue();
		}
		throw new ClassCastException("Error casting int type.");
	}

	public static boolean castBoolean(Object value) {
		if (value.getClass().equals(boolean.class) || value.getClass().equals(Boolean.class)) {
			return ((Boolean) value).booleanValue();
		}
		throw new ClassCastException("Error casting boolean type.");
	}

	public static String castText(Object value) {
		return value.toString();
	}

	public static boolean isGetter(Method method) {
		if (!(method.getName().startsWith("get") || method.getName().startsWith("is"))) {
			return false;
		}
		if (method.getParameterTypes().length != 0) {
			return false;
		}
		if (void.class.equals(method.getReturnType())) {
			return false;
		}
		return true;
	}

	public static boolean isSetter(Method method) {
		if (!method.getName().startsWith("set")) {
			return false;
		}
		if (method.getParameterTypes().length != 1) {
			return false;
		}
		if (!void.class.equals(method.getReturnType())) {
			return false;
		}
		return true;
	}

	public static boolean isClassGetter(Method method) {
		if (method.getName().equals("getClass")) {
			return true;
		}
		return false;
	}

	/**
	 * @param getter
	 * @return camelCase field name.
	 */
	public static String getFieldName(Method getter) {
		String name;
		if (getter.getName().startsWith("get") /* || getter.getName().startsWith("set") */) {
			name = getter.getName().substring(3);
		} else if (getter.getName().startsWith("is")) {
			name = getter.getName().substring(2);
		} else {
			throw new IllegalArgumentException("Method must be a getter.");
		}
		return name.substring(0, 1).toLowerCase() + name.substring(1);
	}

}
