package br.ita.mobe.reflection;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import br.ita.mobe.proxy.Field;

public class EntityParser {

	public static List<Field> parseFields(Object object) {
		List<Field> fields = new ArrayList<Field>();
		for (Method method : object.getClass().getMethods()) {
			if (isGetter(method) && !isClassGetter(method)) {
				Field field = new Field(object);
				field.setName(getFieldName(method));
				field.setGetter(method);
				field.setSetter(setter(method, object.getClass()));
				field.setType(method.getReturnType());
				fields.add(field);
			}
		}
		return fields;
	}

	private static Method setter(Method method, Class<?> clazz) {
		for (Method m2 : clazz.getMethods()) {
			// TODO: testar os tipos de retorno e parâmetro também
			if (isSetter(m2) && getFieldName(m2).equals(getFieldName(method))) {
				return m2;
			}
		}
		return null;
	}

	private static boolean isGetter(Method method) {
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

	private static boolean isSetter(Method method) {
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

	private static boolean isClassGetter(Method method) {
		if (method.getName().equals("getClass")) {
			return true;
		}
		return false;
	}

	/**
	 * @param method
	 * @return camel-case field name.
	 */
	public static String getFieldName(Method method) {
		String name;
		if (method.getName().startsWith("get") || method.getName().startsWith("set")) {
			name = method.getName().substring(3);
		} else if (method.getName().startsWith("is")) {
			name = method.getName().substring(2);
		} else {
			throw new IllegalArgumentException("method must be getter or setter");
		}
		return name.substring(0, 1).toLowerCase() + name.substring(1);
	}
}
