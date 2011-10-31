package br.com.mobe.view.logic.process;

import android.content.Context;
import br.com.mobe.view.widget.form.FormDate;
import br.com.mobe.view.widget.form.FormWidget;

public class DateProcessor extends ViewProcessor {

	@Override
	public FormWidget createWidget(Context context, String name) {
		return new FormDate(context, name, capitalize(name));
	}

}
