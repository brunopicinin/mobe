package br.com.mobe.view;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import br.com.mobe.view.widget.form.FormWidget;

public class FormView extends LinearLayout implements Serializable {

	private static final String TAG = "FormView";

	private Class<?> type;
	private Map<String, FormWidget> widgets;

	public FormView(Context context, Class<?> type) {
		super(context);
		this.type = type;
		widgets = new HashMap<String, FormWidget>();
		setDefaults();
	}

	private void setDefaults() {
		setOrientation(VERTICAL);
		setLayoutParams(new LayoutParams(FILL_PARENT, WRAP_CONTENT));
	}

	public FormWidget findWidget(String name) {
		if (widgets.keySet().contains(name)) {
			return widgets.get(name);
		}
		return null;
	}

	public void addView(FormWidget widget) {
		widgets.put(widget.getName(), widget);
		super.addView(widget);
	}

	public Object getBean() {
		try {
			Object bean = type.newInstance();
			Collection<FormWidget> wgList = widgets.values();
			for (FormWidget widget : wgList) {
				Field field = type.getDeclaredField(widget.getName());
				field.setAccessible(true);
				field.set(bean, widget.getValue(field.getType()));
			}
			return bean;
		} catch (Exception e) {
			Log.e(TAG, "", e);
			return null;
		}
	}
}
