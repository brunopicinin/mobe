package br.ita.mobe.widget;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import java.lang.reflect.Field;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;

public class FormNumberPicker extends FormWidget {

	public FormNumberPicker(Context context, Field field) {
		super(context, field);
		NumberPicker numberPicker = new NumberPicker(context);
		numberPicker.setLayoutParams(new LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
		container.addView(numberPicker);
	}

}
