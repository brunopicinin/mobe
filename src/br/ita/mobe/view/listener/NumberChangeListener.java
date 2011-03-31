package br.ita.mobe.view.listener;

import java.lang.reflect.Field;

import br.ita.mobe.view.widget.numpicker.NumberPicker;
import br.ita.mobe.view.widget.numpicker.NumberPicker.OnChangedListener;

public class NumberChangeListener extends ViewChangeListener implements OnChangedListener {

	public NumberChangeListener(Field field, Object object) {
		super(field, object);
	}

	@Override
	public void onChanged(NumberPicker picker, int oldVal, int newVal) {
		updateField(newVal);
	}
}
