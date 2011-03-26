package br.ita.mobe.view.generator;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.ita.mobe.proxy.Field;
import br.ita.mobe.view.listener.CheckboxChangeListener;
import br.ita.mobe.view.listener.NumberChangeListener;
import br.ita.mobe.view.listener.TextChangeListener;
import br.ita.mobe.view.widget.numpicker.NumberPicker;

public class ViewGenerator {

	private static final Class<?>[] INT_TYPES = { int.class, Integer.class, short.class, Short.class, long.class, Long.class, byte.class, Byte.class };
	private static final Class<?>[] FLOAT_TYPES = { float.class, Float.class, double.class, Double.class };
	private static final Class<?>[] LOGIC_TYPES = { boolean.class, Boolean.class };
	private static final Class<?>[] CHAR_TYPES = { char.class, Character.class };
	private static final Class<?>[] STRING_TYPES = { String.class };

	public static LinearLayout generateFieldInput(Context context, Field field) {
		LinearLayout container = new LinearLayout(context);
		container.setOrientation(LinearLayout.VERTICAL);

		TextView labelView = new TextView(context);
		labelView.setText(field.getName() + ":");
		container.addView(labelView);

		Class<?> clazz = field.getType();
		if (isInteger(clazz)) {
			insertNumberPicker(context, container, field);
		} else if (isFloat(clazz)) {
			insertEditText(context, container, field);
		} else if (isBoolean(clazz)) {
			insertCheckbox(context, container, field);
		} else if (isChar(clazz)) {
			insertEditText(context, container, field);
		} else if (isString(clazz)) {
			insertEditText(context, container, field);
		} else {
			System.out.println("Formato não suportado");
		}

		View ruler = new View(context);
		ruler.setBackgroundColor(0xFFcccccc);
		container.addView(ruler, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 2));

		return container;
	}

	private static void insertNumberPicker(Context context, LinearLayout container, Field field) {
		NumberPicker numberPicker = new NumberPicker(context);
		numberPicker.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		Object value = field.getValue();
		if (value != null) {
			numberPicker.setCurrent(castInt(value));
		}
		numberPicker.setOnChangeListener(new NumberChangeListener(field));
		container.addView(numberPicker);
	}

	private static int castInt(Object value) {
		if (value.getClass().equals(int.class) || value.getClass().equals(Integer.class)) {
			return ((Integer) value).intValue();
		} else if (value.getClass().equals(short.class) || value.getClass().equals(Short.class)) {
			return ((Short) value).intValue();
		} else if (value.getClass().equals(long.class) || value.getClass().equals(Long.class)) {
			return ((Long) value).intValue();
		} else if (value.getClass().equals(byte.class) || value.getClass().equals(Byte.class)) {
			return ((Byte) value).intValue();
		}
		throw new RuntimeException("Error casting Int.");
	}

	private static void insertCheckbox(Context context, LinearLayout container, Field field) {
		CheckBox checkBox = new CheckBox(context);
		Object value = field.getValue();
		if (value != null) {
			checkBox.setChecked(castBoolean(value));
		}
		checkBox.setOnCheckedChangeListener(new CheckboxChangeListener(field));
		container.addView(checkBox);
	}

	private static boolean castBoolean(Object value) {
		if (value.getClass().equals(boolean.class) || value.getClass().equals(Boolean.class)) {
			return ((Boolean) value).booleanValue();
		}
		throw new RuntimeException("Error casting Boolean.");
	}

	private static void insertEditText(Context context, LinearLayout container, Field field) {
		EditText editText = new EditText(context);
		Object value = field.getValue();
		if (value != null) {
			editText.setText(castText(value));
		}
		editText.addTextChangedListener(new TextChangeListener(field));
		container.addView(editText);
	}

	private static CharSequence castText(Object value) {
		return value.toString();
		// if (value.getClass().equals(float.class) ||
		// value.getClass().equals(Float.class)) {
		// return ((Float) value).toString();
		// }
		// throw new RuntimeException("Error casting Text or float.");
	}

	private static boolean isInteger(Class<?> clazz) {
		return typeCheck(clazz, INT_TYPES);
	}

	private static boolean isFloat(Class<?> clazz) {
		return typeCheck(clazz, FLOAT_TYPES);
	}

	private static boolean isBoolean(Class<?> clazz) {
		return typeCheck(clazz, LOGIC_TYPES);
	}

	private static boolean isChar(Class<?> clazz) {
		return typeCheck(clazz, CHAR_TYPES);
	}

	private static boolean isString(Class<?> clazz) {
		return typeCheck(clazz, STRING_TYPES);
	}

	private static boolean typeCheck(Class<?> clazz, Class<?>[] types) {
		for (Class<?> c2 : types) {
			if (c2.equals(clazz)) {
				return true;
			}
		}
		return false;
	}

}
