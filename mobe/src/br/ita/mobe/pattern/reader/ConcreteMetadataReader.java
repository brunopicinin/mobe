package br.ita.mobe.pattern.reader;

import java.lang.reflect.Field;

import android.util.Log;
import br.ita.mobe.annotation.Property;
import br.ita.mobe.pattern.metadata.BeanMetadata;
import br.ita.mobe.pattern.metadata.PropertyDescriptor;

public class ConcreteMetadataReader implements MetadataReader {

	private static final String TAG = "ConcreteMetadataReader";

	@Override
	public BeanMetadata createMetadata(Object bean) {
		BeanMetadata metadata = new BeanMetadata();
		Class<? extends Object> clazz = bean.getClass();
		boolean classAnn = clazz.isAnnotationPresent(Property.class);
		for (Field field : clazz.getDeclaredFields()) {
			if (classAnn || field.isAnnotationPresent(Property.class)) {
				field.setAccessible(true);
				String name = field.getName();
				Class<?> type = field.getType();
				Object value = null;
				try {
					value = field.get(bean);
				} catch (IllegalArgumentException e) {
					Log.e(TAG, "", e);
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					Log.e(TAG, "", e);
					e.printStackTrace();
				}
				metadata.addProperty(new PropertyDescriptor(name, type, value));
			}
		}
		return metadata;
	}
}
