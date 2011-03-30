package br.ita.mobe.proxy;

import java.lang.reflect.Field;

public class EntityProxy {

	private Object originalObject;
	private Field[] fields;

	public Object getOriginalObject() {
		return originalObject;
	}

	public void setOriginalObject(Object originalObject) {
		this.originalObject = originalObject;
	}

	public Field[] getFields() {
		return fields;
	}

	public void setFields(Field[] fields) {
		this.fields = fields;
	}

	public EntityProxy(Object object) {
		this.originalObject = object;
	}
}
