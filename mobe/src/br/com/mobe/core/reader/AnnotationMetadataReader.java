package br.com.mobe.core.reader;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.com.mobe.core.annotation.Entity;
import br.com.mobe.core.exception.IllegalMetadataException;
import br.com.mobe.core.exception.UnsupportedTypeException;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Property;
import br.com.mobe.core.processor.DateProcessor;
import br.com.mobe.core.processor.DecimalProcessor;
import br.com.mobe.core.processor.LogicProcessor;
import br.com.mobe.core.processor.NumberProcessor;
import br.com.mobe.core.processor.TextProcessor;
import br.com.mobe.core.processor.ViewProcessor;
import br.com.mobe.orm.annotation.Id;
import br.com.mobe.orm.annotation.NotNull;
import br.com.mobe.orm.annotation.Unique;

public class AnnotationMetadataReader implements MetadataReader {

	private Map<Class<?>, ViewProcessor> processorMap;

	public AnnotationMetadataReader() {
		processorMap = new HashMap<Class<?>, ViewProcessor>();

		processorMap.put(boolean.class, new LogicProcessor());
		processorMap.put(Boolean.class, new LogicProcessor());

		processorMap.put(byte.class, new NumberProcessor());
		processorMap.put(Byte.class, new NumberProcessor());
		processorMap.put(short.class, new NumberProcessor());
		processorMap.put(Short.class, new NumberProcessor());
		processorMap.put(int.class, new NumberProcessor());
		processorMap.put(Integer.class, new NumberProcessor());
		processorMap.put(long.class, new NumberProcessor());
		processorMap.put(Long.class, new NumberProcessor());

		processorMap.put(float.class, new DecimalProcessor());
		processorMap.put(Float.class, new DecimalProcessor());
		processorMap.put(double.class, new DecimalProcessor());
		processorMap.put(Double.class, new DecimalProcessor());

		processorMap.put(char.class, new TextProcessor());
		processorMap.put(Character.class, new TextProcessor());
		processorMap.put(String.class, new TextProcessor());

		processorMap.put(Calendar.class, new DateProcessor());
		processorMap.put(Date.class, new DateProcessor());
	}

	@Override
	public ClassMetadata createMetadata(Class<?> clazz) {
		if (!clazz.isAnnotationPresent(Entity.class)) {
			throw new IllegalMetadataException(clazz, "@Entity annotation not present in class.");
		}
		ClassMetadata metadata = new ClassMetadata(clazz);
		for (Field field : clazz.getDeclaredFields()) {
			if (field.isAnnotationPresent(br.com.mobe.core.annotation.Property.class)) {
				field.setAccessible(true);
				String name = field.getName();
				Class<?> type = field.getType();
				ViewProcessor processor = getProcessor(type);
				Property property = new Property(name, type, processor);
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

	/**
	 * Add a new processor for a specific object type.
	 * 
	 * @param type
	 *            The class of the new handled object.
	 * @param processor
	 *            The processor responsible for the logic of this type.
	 */
	public void addNewProcessor(Class<?> type, ViewProcessor processor) {
		processorMap.put(type, processor);
	}

	private ViewProcessor getProcessor(Class<?> type) {
		if (processorMap.keySet().contains(type)) {
			return processorMap.get(type);
		} else {
			throw new UnsupportedTypeException(type);
		}
	}
}
