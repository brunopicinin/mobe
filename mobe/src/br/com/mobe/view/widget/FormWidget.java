package br.com.mobe.view.widget;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class FormWidget extends LinearLayout {

	private String name;

	public FormWidget(Context context, String name, String displayName) {
		super(context);
		this.name = name;

		this.setOrientation(VERTICAL);
		this.setLayoutParams(new LayoutParams(FILL_PARENT, WRAP_CONTENT));

		TextView label = new TextView(context);
		label.setLayoutParams(new LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
		label.setText(displayName);

		this.addView(label);
	}

	public String getName() {
		return name;
	}

	public void populate(Object value) {
		if (value != null) {
			populateWg(value);
		}
	}

	protected abstract void populateWg(Object value);

	public abstract Object getValue(Class<?> type);

	protected static boolean isEmpty(String string) {
		if (string == null) {
			return true;
		}
		String trim = string.trim();
		if (trim.length() == 0) {
			return true;
		}
		return false;
	}

}
