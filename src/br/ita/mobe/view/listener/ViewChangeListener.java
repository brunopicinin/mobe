package br.ita.mobe.view.listener;

import java.lang.reflect.Field;

public abstract class ViewChangeListener {

	protected Field field;
	protected Object object;

	public ViewChangeListener(Field field, Object object) {
		this.field = field;
		this.object = object;
	}

	protected void updateField(Object newValue) {
		try {
			field.set(object, newValue);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
