package br.ita.mobe.pattern.logic;

import java.util.List;

import android.content.Context;
import android.util.Log;
import br.ita.mobe.exception.UnsupportedTypeException;
import br.ita.mobe.pattern.metadata.BeanMetadata;
import br.ita.mobe.pattern.metadata.PropertyDescriptor;
import br.ita.mobe.view.FormView;
import br.ita.mobe.widget.form.FormCheckBox;
import br.ita.mobe.widget.form.FormEditText;
import br.ita.mobe.widget.form.FormNumberPicker;
import br.ita.mobe.widget.form.FormWidget;

public class ConcreteViewProcessor implements ViewProcessor {

	private static final String TAG = "ConcreteViewProcessor";

	// Field data types
	private static final Class<?>[] INT_TYPES = { int.class, Integer.class, short.class, Short.class, long.class, Long.class, byte.class, Byte.class };
	private static final Class<?>[] FLOAT_TYPES = { float.class, Float.class, double.class, Double.class };
	private static final Class<?>[] LOGIC_TYPES = { boolean.class, Boolean.class };
	private static final Class<?>[] CHAR_TYPES = { char.class, Character.class };
	private static final Class<?>[] STRING_TYPES = { String.class };

	@Override
	public FormView generateForm(Context context, BeanMetadata metadata) {
		FormView formView = new FormView(context);
		List<PropertyDescriptor> properties = metadata.getProperties();
		for (PropertyDescriptor property : properties) {
			try {
				FormWidget widget = createWidget(context, property.getType(), property.getName());
				formView.addView(widget);
			} catch (UnsupportedTypeException e) {
				// It's not an error, because it is possible to annotate any field.
				Log.v(TAG, "Trying to create widget of unsupported data type.", e);
			}
		}
		return formView;
	}

	private FormWidget createWidget(Context context, Class<?> clazz, String name) throws UnsupportedTypeException {
		if (typeof(clazz, INT_TYPES)) { // NumberPicker
			return new FormNumberPicker(context, name);
		} else if (typeof(clazz, FLOAT_TYPES)) { // EditText
			return new FormEditText(context, name);
		} else if (typeof(clazz, LOGIC_TYPES)) { // CheckBox
			return new FormCheckBox(context, name);
		} else if (typeof(clazz, CHAR_TYPES)) { // EditText
			return new FormEditText(context, name);
		} else if (typeof(clazz, STRING_TYPES)) { // EditText
			return new FormEditText(context, name);
		} else {
			throw new UnsupportedTypeException();
		}
	}

	private static boolean typeof(Class<?> clazz, Class<?>[] types) {
		for (Class<?> c : types) {
			if (c.equals(clazz)) {
				return true;
			}
		}
		return false;
	}

}
