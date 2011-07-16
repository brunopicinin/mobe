package br.ita.mobe.widget;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import br.ita.mobe.annotation.Property;
import br.ita.mobe.exception.UnsupportedTypeException;

public class FormView extends LinearLayout {

	private static final String TAG = "FormView";

	// Field data types
	private static final Class<?>[] INT_TYPES = { int.class, Integer.class, short.class, Short.class, long.class, Long.class, byte.class, Byte.class };
	private static final Class<?>[] FLOAT_TYPES = { float.class, Float.class, double.class, Double.class };
	private static final Class<?>[] LOGIC_TYPES = { boolean.class, Boolean.class };
	private static final Class<?>[] CHAR_TYPES = { char.class, Character.class };
	private static final Class<?>[] STRING_TYPES = { String.class };

	private List<FormWidget> widgets;

	// Binded java bean
	private Object formBean;

	/**
	 * Necessary for instantiation through XML. Change if custom attributes are
	 * added.
	 *
	 * @param context
	 * @param attrs
	 */
	public FormView(Context context, AttributeSet attrs) {
		this(context);
	}

	public FormView(Context context) {
		super(context);
		setDefaults();
	}

	private void setDefaults() {
		setOrientation(VERTICAL);
		setLayoutParams(new LayoutParams(FILL_PARENT, WRAP_CONTENT));
	}

	public Object getFormBean() {
		return formBean;
	}

	public void setFormBean(Object bean) {
		this.formBean = bean;
		generateForm();
	}

	private void generateForm() {
		widgets = new ArrayList<FormWidget>();
		Class<?> clazz = formBean.getClass();
		boolean viewClass = clazz.isAnnotationPresent(Property.class);
		for (Field field : clazz.getDeclaredFields()) {
			if (viewClass || field.isAnnotationPresent(Property.class)) {
				field.setAccessible(true);
				try {
					FormWidget wg = createWidget(getContext(), field);
					widgets.add(wg);
				} catch (UnsupportedTypeException e) {
					// It's not an error, because it is possible to annotate any
					// field.
					Log.v(TAG, "Trying to create widget of unsupported data type.", e);
				}
			}
		}
		for (FormWidget widget : widgets) {
			addView(widget.getView());
		}
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
