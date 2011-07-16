package br.ita.mobe.widget.form;

import android.content.Context;
import android.view.Gravity;
import android.widget.CheckBox;

public class FormCheckBox extends FormWidget {

	public FormCheckBox(Context context, String name) {
		super(context, name);
		CheckBox checkBox = new CheckBox(context);
		setOrientation(HORIZONTAL);
		setGravity(Gravity.CENTER_VERTICAL);
		addView(checkBox);
	}

}
