package br.ita.mobe.view;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import br.ita.mobe.widget.form.FormWidget;

public class FormView extends LinearLayout implements Serializable {

	private List<FormWidget> widgets;

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
		widgets = new ArrayList<FormWidget>();
		setDefaults();
	}

	private void setDefaults() {
		setOrientation(VERTICAL);
		setLayoutParams(new LayoutParams(FILL_PARENT, WRAP_CONTENT));
	}

	public FormWidget findWidget(String name) {
		for (FormWidget widget : widgets) {
			if (widget.getName().equals(name)) {
				return widget;
			}
		}
		return null;
	}

	public void addView(FormWidget widget) {
		widgets.add(widget);
		super.addView(widget);
	}
}
