package br.com.mobe.core.reader;

import java.lang.reflect.Field;

import br.com.mobe.core.annotation.Entity;
import br.com.mobe.core.exception.IllegalMetadataException;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Property;
import br.com.mobe.orm.annotation.Id;
import br.com.mobe.orm.annotation.NotNull;
import br.com.mobe.orm.annotation.Unique;

public class AnnotationMetadataReader implements MetadataReader {

	@Override
	public ClassMetadata createMetadata(Class<?> clazz) throws IllegalMetadataException {
		if (!clazz.isAnnotationPresent(Entity.class)) {
			throw new IllegalMetadataException(clazz, "@Entity annotation not present in class.");
		}
		ClassMetadata metadata = new ClassMetadata(clazz);
		for (Field field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(br.com.mobe.core.annotation.Property.class)) {
				field.setAccessible(true);
				String name = field.getName();
				Class<?> type = field.getType();
				Property property = new Property(name, type);
				boolean idPresent = field.isAnnotationPresent(Id.class);
				property.setPrimaryKey(idPresent);
				if (idPresent) {
					Id annotation = field.getAnnotation(Id.class);
					property.setAutoIncrement(annotation.autoIncrement());
					property.setNotNull(true);
				} else {
					property.setNotNull(field.isAnnotationPresent(NotNull.class));
				}
				property.setUnique(field.isAnnotationPresent(Unique.class));
				metadata.addProperty(property);
			}
		}
		return metadata;
	}
}
