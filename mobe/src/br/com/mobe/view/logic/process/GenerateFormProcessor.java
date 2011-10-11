package br.com.mobe.view.logic.process;

import static br.com.mobe.core.util.ReflectionUtils.isBoolean;
import static br.com.mobe.core.util.ReflectionUtils.isByte;
import static br.com.mobe.core.util.ReflectionUtils.isCalendar;
import static br.com.mobe.core.util.ReflectionUtils.isChar;
import static br.com.mobe.core.util.ReflectionUtils.isDate;
import static br.com.mobe.core.util.ReflectionUtils.isDouble;
import static br.com.mobe.core.util.ReflectionUtils.isFloat;
import static br.com.mobe.core.util.ReflectionUtils.isInt;
import static br.com.mobe.core.util.ReflectionUtils.isLong;
import static br.com.mobe.core.util.ReflectionUtils.isShort;
import static br.com.mobe.core.util.ReflectionUtils.isString;

import java.util.List;

import android.content.Context;
import android.util.Log;
import br.com.mobe.core.exception.UnsupportedTypeException;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Property;
import br.com.mobe.view.FormView;
import br.com.mobe.view.widget.form.FormDate;
import br.com.mobe.view.widget.form.FormDecimal;
import br.com.mobe.view.widget.form.FormLogic;
import br.com.mobe.view.widget.form.FormNumber;
import br.com.mobe.view.widget.form.FormText;
import br.com.mobe.view.widget.form.FormWidget;

public class GenerateFormProcessor implements ViewProcessor {

	private static final String TAG = "ConcreteViewProcessor";

	@Override
	public FormView generateForm(Context context, ClassMetadata metadata) {
		FormView formView = new FormView(context, metadata.getTarget());
		List<Property> properties = metadata.getProperties();
		for (Property property : properties) {
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

	private FormWidget createWidget(Context context, Class<?> type, String name) throws UnsupportedTypeException {
		String capitalizedName = capitalize(name);
		if (isBoolean(type)) {
			return new FormLogic(context, name, capitalizedName);
		} else if (isByte(type) || isShort(type) || isInt(type) || isLong(type)) {
			return new FormNumber(context, name, capitalizedName);
		} else if (isFloat(type) || isDouble(type)) {
			return new FormDecimal(context, name, capitalizedName);
		} else if (isChar(type) || isString(type)) {
			return new FormText(context, name, capitalizedName);
		} else if (isCalendar(type) || isDate(type)) {
			return new FormDate(context, name, capitalizedName);
		} else {
			throw new UnsupportedTypeException(type);
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

}
