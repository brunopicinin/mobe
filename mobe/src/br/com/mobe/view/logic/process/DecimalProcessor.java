package br.com.mobe.view.logic.process;

import android.content.Context;
import br.com.mobe.view.widget.form.FormDecimal;
import br.com.mobe.view.widget.form.FormWidget;

public class DecimalProcessor extends ViewProcessor {

	@Override
	public FormWidget createWidget(Context context, String name) {
		return new FormDecimal(context, name, capitalize(name));
	}

}
