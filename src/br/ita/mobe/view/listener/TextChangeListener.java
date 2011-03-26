package br.ita.mobe.view.listener;

import android.text.Editable;
import android.text.TextWatcher;
import br.ita.mobe.proxy.Field;

public class TextChangeListener extends ViewChangeListener implements TextWatcher {

	public TextChangeListener(Field field) {
		this.field = field;
	}

	@Override
	public void afterTextChanged(Editable s) {
		updateField(s.toString());
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

}
