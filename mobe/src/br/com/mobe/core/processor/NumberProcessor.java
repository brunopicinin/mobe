package br.com.mobe.core.processor;

import android.content.Context;
import br.com.mobe.view.widget.FormNumber;
import br.com.mobe.view.widget.FormWidget;

public class NumberProcessor extends ViewProcessor {

	@Override
	public FormWidget createWidget(Context context, String name) {
		return new FormNumber(context, name, capitalize(name));
	}

}
