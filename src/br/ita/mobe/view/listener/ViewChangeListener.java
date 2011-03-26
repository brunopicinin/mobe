package br.ita.mobe.view.listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import br.ita.mobe.proxy.Field;

public abstract class ViewChangeListener {

	protected Field field;

	protected void updateField(Object newValue) {
		Method setter = field.getSetter();
		try {
			setter.invoke(field.getOwner(), newValue);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
