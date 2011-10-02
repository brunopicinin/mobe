package br.com.mobe.pattern.reader;

import java.lang.reflect.Field;

import br.com.mobe.annotation.Property;
import br.com.mobe.pattern.metadata.ClassMetadata;
import br.com.mobe.pattern.metadata.PropertyDescriptor;

public class ConcreteMetadataReader implements MetadataReader {

	@Override
	public ClassMetadata createMetadata(Class<?> clazz) {
		ClassMetadata metadata = new ClassMetadata(clazz);
		boolean classAnn = clazz.isAnnotationPresent(Property.class);
		for (Field field : clazz.getDeclaredFields()) {
			if (classAnn || field.isAnnotationPresent(Property.class)) {
				field.setAccessible(true);
				String name = field.getName();
				Class<?> type = field.getType();
				metadata.addProperty(new PropertyDescriptor(name, type));
			}
		}
		return metadata;
	}
}
