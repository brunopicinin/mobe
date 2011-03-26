package br.ita.mobe.view.listener;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import br.ita.mobe.proxy.Field;

public class CheckboxChangeListener extends ViewChangeListener implements OnCheckedChangeListener {

	public CheckboxChangeListener(Field field) {
		this.field = field;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		updateField(isChecked);
	}

}
