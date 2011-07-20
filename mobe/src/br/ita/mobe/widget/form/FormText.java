package br.ita.mobe.widget.form;

import android.content.Context;
import android.text.InputType;
import android.widget.EditText;

public class FormText extends FormWidget {

	private EditText editText;

	public FormText(Context context, String name, String displayName) {
		super(context, name, displayName);
		editText = new EditText(context);
		editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
		addView(editText);
	}

	@Override
	public void populateWg(Object value) {
		editText.setText(value.toString());
	}

	@Override
	public Object getValue(Class<?> type) {
		return editText.getText().toString();
	}

}
