package br.ita.mobe.reflection;

import java.lang.reflect.Field;

import br.ita.mobe.proxy.EntityProxy;

public class EntityParser {

	public static EntityProxy parseEntity(Object object) {
		EntityProxy entityProxy = new EntityProxy(object);
		entityProxy.setFields(object.getClass().getDeclaredFields());
		Field.setAccessible(entityProxy.getFields(), true);
		return entityProxy;
	}
}
