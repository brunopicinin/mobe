package br.ita.mobe.view.listener;

import java.lang.reflect.Field;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class CheckboxChangeListener extends ViewChangeListener implements OnCheckedChangeListener {

	public CheckboxChangeListener(Field field, Object object) {
		super(field, object);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		updateField(isChecked);
	}

}
