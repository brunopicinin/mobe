package br.ita.mobe.widget.form;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class FormWidget extends LinearLayout {

	public FormWidget(Context context, String name) {
		super(context);

		this.setOrientation(VERTICAL);
		this.setLayoutParams(new LayoutParams(FILL_PARENT, WRAP_CONTENT));

		TextView label = new TextView(context);
		label.setLayoutParams(new LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
		label.setText(name);

		this.addView(label);
	}

}
