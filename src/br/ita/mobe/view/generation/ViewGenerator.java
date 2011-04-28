package br.ita.mobe.view.generation;

import java.lang.reflect.Field;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import br.ita.mobe.annotation.View;
import br.ita.mobe.proxy.EntityProxy;
import br.ita.mobe.reflection.EntityParser;

public class ViewGenerator {

	/**
	 * Generates a View based on annotated Bean.
	 * 
	 * @param context
	 * @param bean
	 * @return generated View
	 */
	public ViewGroup generateBeanView(Context context, Object bean) {
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

		EntityProxy entityProxy = EntityParser.parseEntity(bean);
		if (bean.getClass().isAnnotationPresent(View.class)) {
			for (Field field : entityProxy.getFields()) {
				ViewGroup formField = FormFieldFactory.createFormField(context, field, entityProxy);
				linearLayout.addView(formField);
			}
		} else {
			for (Field field : entityProxy.getFields()) {
				if (field.isAnnotationPresent(View.class)) {
					ViewGroup formField = FormFieldFactory.createFormField(context, field, entityProxy);
					linearLayout.addView(formField);
				}
			}
		}

		return linearLayout;
	}
}
