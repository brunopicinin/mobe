package br.ita.mobe.widget.form;

import android.content.Context;
import android.text.InputType;
import android.widget.EditText;

public class FormDecimal extends FormWidget {

	public FormDecimal(Context context, String name) {
		super(context, name);
		EditText editText = new EditText(context);
		editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		editText.setHint("Example: -12.05");
		addView(editText);
	}

}
