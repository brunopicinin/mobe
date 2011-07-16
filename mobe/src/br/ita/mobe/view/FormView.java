package br.ita.mobe.view;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class FormView extends LinearLayout {

	/**
	 * Necessary for instantiation through XML. Change if custom attributes are added.
	 * 
	 * @param context
	 * @param attrs
	 */
	public FormView(Context context, AttributeSet attrs) {
		this(context);
	}

	public FormView(Context context) {
		super(context);
		setDefaults();
	}

	private void setDefaults() {
		setOrientation(VERTICAL);
		setLayoutParams(new LayoutParams(FILL_PARENT, WRAP_CONTENT));
	}

}
