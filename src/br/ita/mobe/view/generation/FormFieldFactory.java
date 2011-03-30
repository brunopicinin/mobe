package br.ita.mobe.view.generation;

import java.lang.reflect.Field;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.ita.mobe.exceptions.UnsupportedTypeException;
import br.ita.mobe.utils.ReflectionUtils;
import br.ita.mobe.view.widget.numpicker.NumberPicker;

public class FormFieldFactory {

	public static ViewGroup createFormField(Context context, Field field) throws UnsupportedTypeException {
		LinearLayout container = new LinearLayout(context);
		container.setOrientation(LinearLayout.VERTICAL);
		container.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

		TextView labelView = new TextView(context);
		labelView.setText(field.getName() + ":");
		container.addView(labelView);

		Class<?> clazz = field.getType();
		if (ReflectionUtils.isIntegerType(clazz)) {
			insertNumberPicker(context, container, field);
		} else if (ReflectionUtils.isBooleanType(clazz)) {
			insertCheckbox(context, container, field);
		} else if (ReflectionUtils.isFloatType(clazz) || ReflectionUtils.isCharType(clazz) || ReflectionUtils.isStringType(clazz)) {
			insertEditText(context, container, field);
		} else {
			throw new UnsupportedTypeException();
		}

		// View ruler = new View(context);
		// ruler.setBackgroundColor(0xFFcccccc);
		// container.addView(ruler, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 2));

		return container;
	}

	private static void insertNumberPicker(Context context, LinearLayout container, Field field) {
		NumberPicker numberPicker = new NumberPicker(context);
		numberPicker.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		// Object value = field.getValue();
		// if (value != null) {
		// numberPicker.setCurrent(ReflectionUtils.castInt(value));
		// }
		// numberPicker.setOnChangeListener(new NumberChangeListener(field));
		container.addView(numberPicker);
	}

	private static void insertCheckbox(Context context, LinearLayout container, Field field) {
		CheckBox checkBox = new CheckBox(context);
		// Object value = field.getValue();
		// if (value != null) {
		// checkBox.setChecked(ReflectionUtils.castBoolean(value));
		// }
		// checkBox.setOnCheckedChangeListener(new CheckboxChangeListener(field));
		container.addView(checkBox);
	}

	private static void insertEditText(Context context, LinearLayout container, Field field) {
		EditText editText = new EditText(context);
		// Object value = field.getValue();
		// if (value != null) {
		// editText.setText(ReflectionUtils.castText(value));
		// }
		// editText.addTextChangedListener(new TextChangeListener(field));
		container.addView(editText);
	}

}
