package br.ita.mobe.view.generation;

import java.lang.reflect.Field;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import br.ita.mobe.exceptions.UnsupportedTypeException;
import br.ita.mobe.proxy.EntityProxy;
import br.ita.mobe.reflection.EntityParser;

public class ViewGenerator {

	public ViewGroup generateBeanView(Context context, Object bean) {
		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.VERTICAL);
		linearLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

		EntityProxy entityProxy = EntityParser.parseEntity(bean);
		for (Field field : entityProxy.getFields()) {
			try {
				ViewGroup formField;
				formField = FormFieldFactory.createFormField(context, field);
				linearLayout.addView(formField);
			} catch (UnsupportedTypeException e) {
				e.printStackTrace();
			}
		}

		return linearLayout;
	}
}
