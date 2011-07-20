package br.ita.mobe.view;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import br.ita.mobe.widget.form.FormWidget;

public class FormView extends LinearLayout implements Serializable {

	private static final String TAG = "FormView";

	private Class<?> type;
	private List<FormWidget> widgets;

	// /**
	// * Necessary for instantiation through XML. Change if custom attributes are added.
	// *
	// * @param context
	// * @param attrs
	// */
	// public FormView(Context context, AttributeSet attrs) {
	// this(context);
	// }

	public FormView(Context context, Class<?> type) {
		super(context);
		this.type = type;
		widgets = new ArrayList<FormWidget>();
		setDefaults();
	}

	private void setDefaults() {
		setOrientation(VERTICAL);
		setLayoutParams(new LayoutParams(FILL_PARENT, WRAP_CONTENT));
	}

	public FormWidget findWidget(String name) {
		for (FormWidget widget : widgets) {
			if (widget.getName().equals(name)) {
				return widget;
			}
		}
		return null;
	}

	public void addView(FormWidget widget) {
		widgets.add(widget);
		super.addView(widget);
	}

	public Object getBean() {
		try {
			Object bean = type.newInstance();
			for (FormWidget widget : widgets) {
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
