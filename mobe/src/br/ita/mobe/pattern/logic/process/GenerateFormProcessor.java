package br.ita.mobe.pattern.logic.process;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.util.Log;
import br.ita.mobe.exception.UnsupportedTypeException;
import br.ita.mobe.pattern.metadata.ClassMetadata;
import br.ita.mobe.pattern.metadata.PropertyDescriptor;
import br.ita.mobe.view.FormView;
import br.ita.mobe.widget.form.FormDate;
import br.ita.mobe.widget.form.FormDecimal;
import br.ita.mobe.widget.form.FormLogic;
import br.ita.mobe.widget.form.FormNumber;
import br.ita.mobe.widget.form.FormText;
import br.ita.mobe.widget.form.FormWidget;

public class GenerateFormProcessor implements ViewProcessor {

	private static final String TAG = "ConcreteViewProcessor";

	// Field data types
	public static final Class<?>[] LOGIC_TYPES = { boolean.class, Boolean.class };
	public static final Class<?>[] INT_TYPES = { byte.class, Byte.class, short.class, Short.class, int.class, Integer.class, long.class, Long.class };
	public static final Class<?>[] DECIMAL_TYPES = { float.class, Float.class, double.class, Double.class };
	public static final Class<?>[] CHAR_TYPES = { char.class, Character.class };
	public static final Class<?>[] STRING_TYPES = { String.class };
	public static final Class<?>[] DATE_TYPES = { Calendar.class, Date.class };

	@Override
	public FormView generateForm(Context context, ClassMetadata metadata) {
		FormView formView = new FormView(context, metadata.getTarget());
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
		String capitalizedName = capitalize(name);
		if (typeof(clazz, LOGIC_TYPES)) {
			return new FormLogic(context, name, capitalizedName);
		} else if (typeof(clazz, INT_TYPES)) {
			return new FormNumber(context, name, capitalizedName);
		} else if (typeof(clazz, DECIMAL_TYPES)) {
			return new FormDecimal(context, name, capitalizedName);
		} else if (typeof(clazz, CHAR_TYPES)) {
			return new FormText(context, name, capitalizedName);
		} else if (typeof(clazz, STRING_TYPES)) {
			return new FormText(context, name, capitalizedName);
		} else if (typeof(clazz, DATE_TYPES)) {
			return new FormDate(context, name, capitalizedName);
		} else {
			throw new UnsupportedTypeException();
		}
	}

	private String capitalize(String name) {
		StringBuilder sb = new StringBuilder();
		sb.append(Character.toUpperCase(name.charAt(0))); // first letter capitalized
		int length = name.length();
		for (int i = 1; i < length; i++) {
			char c = name.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(" ");
			}
			sb.append(c);
		}
		return sb.toString();
	}

	public static boolean typeof(Class<?> clazz, Class<?>[] types) {
		for (Class<?> c : types) {
			if (c.equals(clazz)) {
				return true;
			}
		}
		return false;
	}

}
