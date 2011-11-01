package br.com.mobe.core.processor;

import android.content.ContentValues;
import android.content.Context;
import br.com.mobe.view.widget.FormText;
import br.com.mobe.view.widget.FormWidget;

public abstract class TextProcessor extends Processor {

	@Override
	public FormWidget createWidget(Context context, String name) {
		return new FormText(context, name, capitalize(name));
	}

	@Override
	public String getSQLType() {
		// TEXT affinity (2)
		return " TEXT ";
	}

	@Override
	public void putIn(ContentValues values, String key, Object value) {
		values.put(key, String.valueOf(value));
	}

}
