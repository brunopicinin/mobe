package br.ita.mobe.pattern.reader;

import java.lang.reflect.Field;

import br.ita.mobe.annotation.Property;
import br.ita.mobe.pattern.metadata.BeanMetadata;
import br.ita.mobe.pattern.metadata.PropertyDescriptor;

public class ConcreteMetadataReader implements MetadataReader {

	@Override
	public BeanMetadata createMetadata(Class<?> clazz) {
		BeanMetadata metadata = new BeanMetadata();
		boolean classAnn = clazz.isAnnotationPresent(Property.class);
		for (Field field : clazz.getDeclaredFields()) {
			if (classAnn || field.isAnnotationPresent(Property.class)) {
				field.setAccessible(true);
				metadata.addProperty(new PropertyDescriptor(field.getName(), field.getType()));
			}
		}
		return metadata;
	}

}
