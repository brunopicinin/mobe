package br.com.mobe.core.reader;

import static br.com.mobe.core.util.ReflectionUtils.isBoolean;
import static br.com.mobe.core.util.ReflectionUtils.isByte;
import static br.com.mobe.core.util.ReflectionUtils.isCalendar;
import static br.com.mobe.core.util.ReflectionUtils.isChar;
import static br.com.mobe.core.util.ReflectionUtils.isDate;
import static br.com.mobe.core.util.ReflectionUtils.isDouble;
import static br.com.mobe.core.util.ReflectionUtils.isFloat;
import static br.com.mobe.core.util.ReflectionUtils.isInt;
import static br.com.mobe.core.util.ReflectionUtils.isLong;
import static br.com.mobe.core.util.ReflectionUtils.isShort;
import static br.com.mobe.core.util.ReflectionUtils.isString;

import java.lang.reflect.Field;

import br.com.mobe.core.annotation.Entity;
import br.com.mobe.core.exception.IllegalMetadataException;
import br.com.mobe.core.exception.UnsupportedTypeException;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Property;
import br.com.mobe.orm.annotation.Id;
import br.com.mobe.orm.annotation.NotNull;
import br.com.mobe.orm.annotation.Unique;
import br.com.mobe.view.logic.process.DateProcessor;
import br.com.mobe.view.logic.process.DecimalProcessor;
import br.com.mobe.view.logic.process.LogicProcessor;
import br.com.mobe.view.logic.process.NumberProcessor;
import br.com.mobe.view.logic.process.TextProcessor;
import br.com.mobe.view.logic.process.ViewProcessor;

public class AnnotationMetadataReader implements MetadataReader {

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

	private ViewProcessor getProcessor(Class<?> type) {
		if (isBoolean(type)) {
			return new LogicProcessor();
		} else if (isByte(type) || isShort(type) || isInt(type) || isLong(type)) {
			return new NumberProcessor();
		} else if (isFloat(type) || isDouble(type)) {
			return new DecimalProcessor();
		} else if (isChar(type) || isString(type)) {
			return new TextProcessor();
		} else if (isCalendar(type) || isDate(type)) {
			return new DateProcessor();
		} else {
			throw new UnsupportedTypeException(type);
		}
	}
}
