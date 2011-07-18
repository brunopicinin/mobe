package br.ita.mobe.widget.form;

import android.content.Context;
import android.text.InputType;
import android.widget.EditText;

public class FormDate extends FormWidget {

	public FormDate(Context context, String name) {
		super(context, name);
		EditText editText = new EditText(context);
		editText.setInputType(InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_DATE);
		editText.setHint("Example: 30/10/2000");
		addView(editText);
	}

}
