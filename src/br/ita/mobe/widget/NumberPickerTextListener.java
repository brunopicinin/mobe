package br.ita.mobe.widget;

import android.text.Editable;
import android.text.TextWatcher;

public class NumberPickerTextListener implements TextWatcher {

	NumberPicker numberPicker;

	public NumberPickerTextListener(NumberPicker numberPicker) {
		this.numberPicker = numberPicker;
	}

	@Override
	public void afterTextChanged(Editable s) {
		if (numberPicker == null) {
			return;
		}
		String str = s.toString();
		if (!str.equals("")) {
			// Check the new value and ensure it's in range
			numberPicker.validateCurrentView2(str);
		}
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
	}

}
