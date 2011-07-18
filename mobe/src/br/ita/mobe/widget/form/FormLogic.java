package br.ita.mobe.widget.form;

import android.content.Context;
import android.view.Gravity;
import android.widget.CheckBox;

public class FormLogic extends FormWidget {

	public FormLogic(Context context, String name) {
		super(context, name);

		// Remove TextView. Text will be placed in CheckBox
		removeViewAt(0);

		CheckBox checkBox = new CheckBox(context);
		checkBox.setText(name);

		setOrientation(HORIZONTAL);
		setGravity(Gravity.CENTER_VERTICAL);

		addView(checkBox);
	}

}
