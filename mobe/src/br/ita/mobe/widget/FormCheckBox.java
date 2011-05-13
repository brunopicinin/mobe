package br.ita.mobe.widget;

import static android.widget.LinearLayout.HORIZONTAL;

import java.lang.reflect.Field;

import android.content.Context;
import android.view.Gravity;
import android.widget.CheckBox;

public class FormCheckBox extends FormWidget {

	public FormCheckBox(Context context, Field field) {
		super(context, field);
		CheckBox checkBox = new CheckBox(context);
		container.setOrientation(HORIZONTAL);
		container.setGravity(Gravity.CENTER_VERTICAL);
		container.addView(checkBox);
	}

}
