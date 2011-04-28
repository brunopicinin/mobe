package br.ita.mobe.view.generation;

import java.lang.reflect.Field;

import android.content.Context;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.ita.mobe.exception.UnsupportedTypeException;
import br.ita.mobe.proxy.EntityProxy;
import br.ita.mobe.util.ReflectionUtils;
import br.ita.mobe.view.listener.CheckboxChangeListener;
import br.ita.mobe.view.listener.NumberChangeListener;
import br.ita.mobe.view.listener.TextChangeListener;
import br.ita.mobe.view.widget.numpicker.NumberPicker;

public class FormFieldFactory {

	public static ViewGroup createFormField(Context context, Field field, EntityProxy entityProxy) throws UnsupportedTypeException, IllegalArgumentException, IllegalAccessException {
		LinearLayout container = new LinearLayout(context);
		container.setOrientation(LinearLayout.VERTICAL);
		container.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

		TextView labelView = new TextView(context);
		labelView.setText(field.getName() + ":");
		container.addView(labelView);

		Object originalObject = entityProxy.getOriginalObject();
		Class<?> clazz = field.getType();
		if (ReflectionUtils.isIntegerType(clazz)) {
			insertNumberPicker(context, container, field, originalObject);
		} else if (ReflectionUtils.isBooleanType(clazz)) {
			insertCheckbox(context, container, field, originalObject);
		} else if (ReflectionUtils.isFloatType(clazz) || ReflectionUtils.isCharType(clazz) || ReflectionUtils.isStringType(clazz)) {
			insertEditText(context, container, field, originalObject);
		} else {
			throw new UnsupportedTypeException();
		}

		// View ruler = new View(context);
		// ruler.setBackgroundColor(0xFFcccccc);
		// container.addView(ruler, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 2));

		return container;
	}

	private static void insertNumberPicker(Context context, LinearLayout container, Field field, Object originalObject) throws IllegalArgumentException, IllegalAccessException {
		NumberPicker numberPicker = new NumberPicker(context);
		numberPicker.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		Object fieldValue = field.get(originalObject);
		if (fieldValue != null) {
			numberPicker.setCurrent(ReflectionUtils.castInt(fieldValue));
		}
		numberPicker.setOnChangeListener(new NumberChangeListener(field, originalObject));
		container.addView(numberPicker);
	}

	private static void insertCheckbox(Context context, LinearLayout container, Field field, Object originalObject) throws IllegalArgumentException, IllegalAccessException {
		CheckBox checkBox = new CheckBox(context);
		Object fieldValue = field.get(originalObject);
		if (fieldValue != null) {
			checkBox.setChecked(ReflectionUtils.castBoolean(fieldValue));
		}
		checkBox.setOnCheckedChangeListener(new CheckboxChangeListener(field, originalObject));
		container.addView(checkBox);
	}

	private static void insertEditText(Context context, LinearLayout container, Field field, Object originalObject) throws IllegalArgumentException, IllegalAccessException {
		EditText editText = new EditText(context);
		Object fieldValue = field.get(originalObject);
		if (fieldValue != null) {
			editText.setText(ReflectionUtils.castText(fieldValue));
		}
		editText.addTextChangedListener(new TextChangeListener(field, originalObject));
		container.addView(editText);
	}

}
