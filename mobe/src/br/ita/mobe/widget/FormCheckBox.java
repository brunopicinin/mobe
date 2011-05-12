package br.ita.mobe.widget;

import java.lang.reflect.Field;

import android.content.Context;
import android.widget.CheckBox;

public class FormCheckBox extends FormWidget {

	public FormCheckBox(Context context, Field field) {
		super(context, field);
		CheckBox checkBox = new CheckBox(context);
		container.addView(checkBox);
	}

}
