package br.ita.mobe.widget.form;

import android.content.Context;
import android.text.InputType;
import android.widget.EditText;

public class FormText extends FormWidget {

	public FormText(Context context, String name) {
		super(context, name);
		EditText editText = new EditText(context);
		editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
		addView(editText);
	}

}
