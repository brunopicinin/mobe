package br.com.mobe.core.processor;

import android.content.Context;
import br.com.mobe.view.widget.FormDecimal;
import br.com.mobe.view.widget.FormWidget;

public class DecimalProcessor extends Processor {

	@Override
	public FormWidget createWidget(Context context, String name) {
		return new FormDecimal(context, name, capitalize(name));
	}

	@Override
	public String getSQLType() {
		// REAL affinity (4)
		return " REAL ";
	}

}
