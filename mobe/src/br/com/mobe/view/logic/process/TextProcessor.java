package br.com.mobe.view.logic.process;

import android.content.Context;
import br.com.mobe.view.widget.form.FormText;
import br.com.mobe.view.widget.form.FormWidget;

public class TextProcessor extends ViewProcessor {

	@Override
	public FormWidget createWidget(Context context, String name) {
		return new FormText(context, name, capitalize(name));
	}

}
