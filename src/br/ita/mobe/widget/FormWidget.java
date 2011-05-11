package br.ita.mobe.widget;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static android.widget.LinearLayout.VERTICAL;

import java.lang.reflect.Field;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class FormWidget {

	protected LinearLayout container;

	public FormWidget(Context context, Field field) {
		// context is treated in subclass

		container = new LinearLayout(context);
		container.setOrientation(VERTICAL);
		container.setLayoutParams(new LayoutParams(FILL_PARENT, WRAP_CONTENT));

		TextView label = new TextView(context);
		label.setText(field.getName());

		container.addView(label);
	}

	public View getView() {
		return container;
	}

}
