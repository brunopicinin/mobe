package br.com.mobe.core.processor;

import android.content.Context;
import br.com.mobe.view.widget.FormLogic;
import br.com.mobe.view.widget.FormWidget;

public class BooleanProcessor extends Processor {

	@Override
	public FormWidget createWidget(Context context, String name) {
		return new FormLogic(context, name, capitalize(name));
	}

	@Override
	public String getSQLType() {
		// NUMERIC affinity (5) -- save as 0 or 1
		return " BOOLEAN ";
	}

}
