package br.ita.mobe.app;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.LinearLayout.VERTICAL;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import br.ita.mobe.annotation.View;
import br.ita.mobe.exception.UnsupportedTypeException;
import br.ita.mobe.widget.FormCheckBox;
import br.ita.mobe.widget.FormEditText;
import br.ita.mobe.widget.FormNumberPicker;
import br.ita.mobe.widget.FormWidget;

public class FormActivity extends Activity {

	private static final String TAG = "FormActivity";

	// Field data types
	private static final Class<?>[] INT_TYPES = { int.class, Integer.class, short.class, Short.class, long.class, Long.class, byte.class, Byte.class };
	private static final Class<?>[] FLOAT_TYPES = { float.class, Float.class, double.class, Double.class };
	private static final Class<?>[] LOGIC_TYPES = { boolean.class, Boolean.class };
	private static final Class<?>[] CHAR_TYPES = { char.class, Character.class };
	private static final Class<?>[] STRING_TYPES = { String.class };

	private ScrollView viewport;
	private LinearLayout container;
	private List<FormWidget> widgets;

	// Binded java bean
	private Object bean;

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
		generateForm();
	}

	private void generateForm() {
		widgets = new ArrayList<FormWidget>();

		viewport = new ScrollView(this);
		viewport.setLayoutParams(new LayoutParams(FILL_PARENT, FILL_PARENT));

		container = new LinearLayout(this);
		container.setOrientation(VERTICAL);
		container.setLayoutParams(new LayoutParams(FILL_PARENT, WRAP_CONTENT));

		Class<?> clazz = bean.getClass();
		boolean viewClass = clazz.isAnnotationPresent(View.class);
		for (Field field : clazz.getDeclaredFields()) {
			if (viewClass || field.isAnnotationPresent(View.class)) {
				field.setAccessible(true);
				try {
					FormWidget wg = createWidget(this, field);
					widgets.add(wg);
				} catch (UnsupportedTypeException e) {
					// It's not an error, because it is possible to annotate any field.
					Log.v(TAG, "Trying to create widget of unsupported data type.", e);
				}
			}
		}
		for (FormWidget widget : widgets) {
			container.addView(widget.getView());
		}

		viewport.addView(container);
		setContentView(viewport);
	}

	/**
	 * Factory method to generate widget based on bean field.
	 * 
	 * @param clazz
	 *            - Bean class
	 * @param field
	 *            - Bean field
	 * @param formActivity
	 * @return generated widget
	 * @throws UnsupportedTypeException
	 */
	private FormWidget createWidget(Context context, Field field) throws UnsupportedTypeException {
		Class<?> clazz = field.getType();
		if (isIn(clazz, INT_TYPES)) { // NumberPicker
			return new FormNumberPicker(context, field);
		} else if (isIn(clazz, FLOAT_TYPES)) { // EditText
			return new FormEditText(context, field);
		} else if (isIn(clazz, LOGIC_TYPES)) { // CheckBox
			return new FormCheckBox(context, field);
		} else if (isIn(clazz, CHAR_TYPES)) { // EditText
			return new FormEditText(context, field);
		} else if (isIn(clazz, STRING_TYPES)) { // EditText
			return new FormEditText(context, field);
		} else {
			throw new UnsupportedTypeException();
		}
	}

	private static boolean isIn(Class<?> clazz, Class<?>[] types) {
		for (Class<?> c : types) {
			if (c.equals(clazz)) {
				return true;
			}
		}
		return false;
	}
}
