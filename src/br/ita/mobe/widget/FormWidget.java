package br.ita.mobe.widget;

import java.lang.reflect.Field;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class FormWidget {

	private String label;
	private View view;

	public FormWidget(Context context, Field field) {
		label = field.getName();
		TextView textView = new TextView(context);
		textView.setText(label);
		view = textView;
	}

	public String getLabel() {
		return label;
	}

	public View getView() {
		return view;
	}

}
