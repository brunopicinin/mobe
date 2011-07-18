package br.ita.mobe.widget.form;

import android.content.Context;
import android.text.InputType;
import android.widget.EditText;

public class FormNumber extends FormWidget {

	public FormNumber(Context context, String name) {
		super(context, name);
		// NumberPicker numberPicker = new NumberPicker(context);
		// numberPicker.setLayoutParams(new LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
		EditText editText = new EditText(context);
		editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_SIGNED);
		editText.setHint("Example: -125");
		addView(editText);
	}

}
