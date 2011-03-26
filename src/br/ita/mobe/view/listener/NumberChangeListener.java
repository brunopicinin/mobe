package br.ita.mobe.view.listener;

import br.ita.mobe.proxy.Field;
import br.ita.mobe.view.widget.numpicker.NumberPicker;
import br.ita.mobe.view.widget.numpicker.NumberPicker.OnChangedListener;

public class NumberChangeListener extends ViewChangeListener implements OnChangedListener {

	public NumberChangeListener(Field field) {
		this.field = field;
	}

	@Override
	public void onChanged(NumberPicker picker, int oldVal, int newVal) {
		updateField(newVal);
	}
}
