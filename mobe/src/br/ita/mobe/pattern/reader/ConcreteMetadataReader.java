package br.ita.mobe.pattern.reader;

import java.lang.reflect.Field;

import br.ita.mobe.annotation.View;
import br.ita.mobe.pattern.metadata.BeanMetadata;
import br.ita.mobe.pattern.metadata.PropertyDescriptor;

public class ConcreteMetadataReader implements MetadataReader {

	@Override
	public BeanMetadata createMetadata(Class<?> clazz) {
		BeanMetadata metadata = new BeanMetadata();
		boolean classAnn = clazz.isAnnotationPresent(View.class);
		for (Field field : clazz.getDeclaredFields()) {
			if (classAnn || field.isAnnotationPresent(View.class)) {
				field.setAccessible(true);
				metadata.addProperty(new PropertyDescriptor(field.getName(), field.getType()));
			}
		}
		return metadata;
	}

}
