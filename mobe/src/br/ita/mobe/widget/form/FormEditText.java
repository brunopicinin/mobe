package br.ita.mobe.widget.form;

import android.content.Context;
import android.widget.EditText;

public class FormEditText extends FormWidget {

	public FormEditText(Context context, String name) {
		super(context, name);
		EditText editText = new EditText(context);
		addView(editText);
	}

}
