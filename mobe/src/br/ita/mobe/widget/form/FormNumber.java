package br.ita.mobe.widget.form;

import android.content.Context;
import android.text.InputType;
import android.widget.EditText;

public class FormNumber extends FormWidget {

	private EditText editText;

	public FormNumber(Context context, String name, String displayName) {
		super(context, name, displayName);
		editText = new EditText(context);
		editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
		editText.setHint("Example: -125");
		addView(editText);
	}

	@Override
	public void populateWg(Object value) {
		editText.setText(value.toString());
	}

	@Override
	public Object getValue(Class<?> type) {
		String string = editText.getText().toString();
		if (isEmpty(string)) {
			string = "0";
		}
		if (type.equals(byte.class) || type.equals(Byte.class)) {
			return Byte.valueOf(string);
		} else if (type.equals(short.class) || type.equals(Short.class)) {
			return Short.valueOf(string);
		} else if (type.equals(int.class) || type.equals(Integer.class)) {
			return Integer.valueOf(string);
		} else if (type.equals(long.class) || type.equals(Long.class)) {
			return Long.valueOf(string);
		}
		return null;
	}

}
