package br.com.mobe.core.processor;

import android.content.Context;
import br.com.mobe.view.widget.FormDate;
import br.com.mobe.view.widget.FormWidget;

public class DateProcessor extends ViewProcessor {

	@Override
	public FormWidget createWidget(Context context, String name) {
		return new FormDate(context, name, capitalize(name));
	}

}
