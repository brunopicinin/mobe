package br.com.mobe.view.logic.layer;

import java.lang.reflect.Field;
import java.util.List;

import android.content.Context;
import android.util.Log;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Property;
import br.com.mobe.view.FormView;
import br.com.mobe.view.widget.form.FormWidget;

public class PopulateFormLayer implements FormProcessingLayer {

	private static final String TAG = "PopulateFormLayer";

	@Override
	public FormView processLayer(Context context, FormView formView, ClassMetadata metadata, Object bean) {
		// TODO: como integrar com o Metadata Processor?

		Class<?> clazz = bean.getClass();
		List<Property> properties = metadata.getProperties();
		for (Property property : properties) {
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
