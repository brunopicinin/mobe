package br.com.mobe.view.widget;

import android.content.Context;
import android.view.Gravity;
import android.widget.CheckBox;

public class FormLogic extends FormWidget {

	private CheckBox checkBox;

	public FormLogic(Context context, String name, String displayName) {
		super(context, name, displayName);

		// Remove TextView. Text will be placed in CheckBox
		removeViewAt(0);

		checkBox = new CheckBox(context);
		checkBox.setText(displayName);

		setOrientation(HORIZONTAL);
		setGravity(Gravity.CENTER_VERTICAL);

		addView(checkBox);
	}

	@Override
	public void populateWg(Object value) {
		if ((Boolean) value == true) {
			checkBox.setChecked(true);
		}
	}

	@Override
	public Object getValue(Class<?> type) {
		return checkBox.isChecked();
	}

}
