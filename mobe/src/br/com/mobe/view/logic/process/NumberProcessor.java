package br.com.mobe.view.logic.process;

import android.content.Context;
import br.com.mobe.view.widget.form.FormNumber;
import br.com.mobe.view.widget.form.FormWidget;

public class NumberProcessor extends ViewProcessor {

	@Override
	public FormWidget createWidget(Context context, String name) {
		return new FormNumber(context, name, capitalize(name));
	}

}
