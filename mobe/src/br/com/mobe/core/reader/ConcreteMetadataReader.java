package br.com.mobe.core.reader;

import java.lang.reflect.Field;

import br.com.mobe.core.annotation.Mobe;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Property;
import br.com.mobe.orm.annotation.PrimaryKey;

public class ConcreteMetadataReader implements MetadataReader {

	@Override
	public ClassMetadata createMetadata(Class<?> clazz) {
		ClassMetadata metadata = new ClassMetadata(clazz);
		boolean classAnn = clazz.isAnnotationPresent(Mobe.class);
		for (Field field : clazz.getDeclaredFields()) {
			if (classAnn || field.isAnnotationPresent(Mobe.class)) {
				field.setAccessible(true);
				String name = field.getName();
				Class<?> type = field.getType();
				Property property = new Property(name, type);
				property.setPrimaryKey(field.isAnnotationPresent(PrimaryKey.class));
				metadata.addProperty(property);
			}
		}
		return metadata;
	}
}
