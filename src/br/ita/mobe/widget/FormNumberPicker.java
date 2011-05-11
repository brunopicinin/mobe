package br.ita.mobe.widget;

import java.lang.reflect.Field;

import android.content.Context;

public class FormNumberPicker extends FormWidget {

	public FormNumberPicker(Context context, Field field) {
		super(context, field);
		NumberPicker numberPicker = new NumberPicker(context);
		container.addView(numberPicker);
	}

}
