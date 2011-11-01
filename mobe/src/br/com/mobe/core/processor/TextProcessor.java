package br.com.mobe.core.processor;

import android.content.Context;
import br.com.mobe.view.widget.FormText;
import br.com.mobe.view.widget.FormWidget;

public class TextProcessor extends Processor {

	@Override
	public FormWidget createWidget(Context context, String name) {
		return new FormText(context, name, capitalize(name));
	}

	@Override
	public String getSQLType() {
		// TEXT affinity (2)
		return " TEXT ";
	}

}
