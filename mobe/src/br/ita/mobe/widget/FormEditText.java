package br.ita.mobe.widget;

import java.lang.reflect.Field;

import android.content.Context;
import android.widget.EditText;

public class FormEditText extends FormWidget {

	public FormEditText(Context context, Field field) {
		super(context, field);
		EditText editText = new EditText(context);
		container.addView(editText);
	}

}
