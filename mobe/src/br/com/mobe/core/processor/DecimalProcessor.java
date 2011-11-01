package br.com.mobe.core.processor;

import android.content.Context;
import br.com.mobe.view.widget.FormDecimal;
import br.com.mobe.view.widget.FormWidget;

public class DecimalProcessor extends ViewProcessor {

	@Override
	public FormWidget createWidget(Context context, String name) {
		return new FormDecimal(context, name, capitalize(name));
	}

}
