package br.com.mobe.core.processor;

import android.content.Context;
import br.com.mobe.view.widget.FormDate;
import br.com.mobe.view.widget.FormWidget;

public abstract class TimeProcessor extends Processor {

	@Override
	public FormWidget createWidget(Context context, String name) {
		return new FormDate(context, name, capitalize(name));
	}

	@Override
	public String getSQLType() {
		// NUMERIC affinity (5) -- save time in milliseconds
		return " DATE ";
	}

}
