package br.ita.mobe.view.listener;

import java.lang.reflect.Field;

import android.text.Editable;
import android.text.TextWatcher;

public class TextChangeListener extends ViewChangeListener implements TextWatcher {

	public TextChangeListener(Field field, Object object) {
		super(field, object);
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
