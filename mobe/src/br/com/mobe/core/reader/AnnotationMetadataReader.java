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
import br.com.mobe.core.processor.BooleanProcessor;
import br.com.mobe.core.processor.Processor;
import br.com.mobe.core.processor.primitive.ByteProcessor;
import br.com.mobe.core.processor.primitive.CalendarProcessor;
import br.com.mobe.core.processor.primitive.CharProcessor;
import br.com.mobe.core.processor.primitive.DateProcessor;
import br.com.mobe.core.processor.primitive.DoubleProcessor;
import br.com.mobe.core.processor.primitive.FloatProcessor;
import br.com.mobe.core.processor.primitive.IntProcessor;
import br.com.mobe.core.processor.primitive.LongProcessor;
import br.com.mobe.core.processor.primitive.ShortProcessor;
import br.com.mobe.core.processor.primitive.StringProcessor;
import br.com.mobe.orm.annotation.Id;
import br.com.mobe.orm.annotation.NotNull;
import br.com.mobe.orm.annotation.Unique;

public class AnnotationMetadataReader implements MetadataReader {

	private Map<Class<?>, Processor> processorMap;

	public AnnotationMetadataReader() {
		processorMap = new HashMap<Class<?>, Processor>();

		// BooleanProcessor
		processorMap.put(boolean.class, new BooleanProcessor());
		processorMap.put(Boolean.class, new BooleanProcessor());

		// NumberProcessor
		processorMap.put(byte.class, new ByteProcessor());
		processorMap.put(Byte.class, new ByteProcessor());
		processorMap.put(short.class, new ShortProcessor());
		processorMap.put(Short.class, new ShortProcessor());
		processorMap.put(int.class, new IntProcessor());
		processorMap.put(Integer.class, new IntProcessor());
		processorMap.put(long.class, new LongProcessor());
		processorMap.put(Long.class, new LongProcessor());

		// DecimalProcessor
		processorMap.put(float.class, new FloatProcessor());
		processorMap.put(Float.class, new FloatProcessor());
		processorMap.put(double.class, new DoubleProcessor());
		processorMap.put(Double.class, new DoubleProcessor());

		// TextProcessor
		processorMap.put(char.class, new CharProcessor());
		processorMap.put(Character.class, new CharProcessor());
		processorMap.put(String.class, new StringProcessor());

		// TimeProcessor
		processorMap.put(Calendar.class, new CalendarProcessor());
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
				Processor processor = getProcessor(type);
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
	public void addNewProcessor(Class<?> type, Processor processor) {
		processorMap.put(type, processor);
	}

	private Processor getProcessor(Class<?> type) {
		if (processorMap.keySet().contains(type)) {
			return processorMap.get(type);
		} else {
			throw new UnsupportedTypeException(type);
		}
	}
}
