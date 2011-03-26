package br.ita.mobe.proxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Field {

	private Object owner;
	private String name;
	private Method getter;
	private Method setter;
	private Class<?> type;

	public Field(Object owner) {
		this.owner = owner;
	}

	public Object getOwner() {
		return owner;
	}

	public void setOwner(Object owner) {
		this.owner = owner;
	}

	public Method getGetter() {
		return getter;
	}

	public void setGetter(Method getter) {
		this.getter = getter;
	}

	public Method getSetter() {
		return setter;
	}

	public void setSetter(Method setter) {
		this.setter = setter;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> clazz) {
		this.type = clazz;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		try {
			return getter.invoke(owner, new Object[] {});
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
}
