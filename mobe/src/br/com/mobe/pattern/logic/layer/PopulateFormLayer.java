package br.com.mobe.pattern.logic.layer;

import java.lang.reflect.Field;
import java.util.List;

import android.content.Context;
import android.util.Log;
import br.com.mobe.pattern.metadata.ClassMetadata;
import br.com.mobe.pattern.metadata.PropertyDescriptor;
import br.com.mobe.view.FormView;
import br.com.mobe.widget.form.FormWidget;

public class PopulateFormLayer implements FormProcessingLayer {

	private static final String TAG = "PopulateFormLayer";

	@Override
	public FormView processLayer(Context context, FormView formView, ClassMetadata metadata, Object bean) {
		// TODO: como integrar com o Metadata Processor?

		Class<?> clazz = bean.getClass();
		List<PropertyDescriptor> properties = metadata.getProperties();
		for (PropertyDescriptor property : properties) {
			String propertyName = property.getName();
			FormWidget widget = formView.findWidget(propertyName);
			Object value = null;
			try {
				Field field = clazz.getDeclaredField(propertyName);
				field.setAccessible(true);
				value = field.get(bean);
			} catch (SecurityException e) {
				Log.e(TAG, "", e);
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				Log.e(TAG, "", e);
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				Log.e(TAG, "", e);
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				Log.e(TAG, "", e);
				e.printStackTrace();
			}
			widget.populate(value);
		}
		return formView;
	}

}
