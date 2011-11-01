package br.com.mobe.core.processor;

import android.content.Context;
import br.com.mobe.view.widget.FormLogic;
import br.com.mobe.view.widget.FormWidget;

public class LogicProcessor extends ViewProcessor {

	@Override
	public FormWidget createWidget(Context context, String name) {
		return new FormLogic(context, name, capitalize(name));
	}

}
