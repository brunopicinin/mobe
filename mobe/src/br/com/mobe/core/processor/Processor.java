package br.com.mobe.core.processor;

import java.lang.reflect.Field;

import android.content.Context;
import android.util.Log;
import br.com.mobe.view.widget.FormWidget;

public abstract class Processor {

	private static final String TAG = "Processor";

	public abstract FormWidget createWidget(Context context, String name);

	public void populateWidget(FormWidget widget, String name, Object bean) {
		Class<?> clazz = bean.getClass();
		Object value = null;
		try {
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			value = field.get(bean);
		} catch (SecurityException e) {
			Log.e(TAG, "", e);
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			Log.e(TAG, "", e);
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			Log.e(TAG, "", e);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			Log.e(TAG, "", e);
			e.printStackTrace();
		}
		widget.populate(value);
	}

	public abstract String getSQLType();

	protected String capitalize(String name) {
		StringBuilder sb = new StringBuilder();
		sb.append(Character.toUpperCase(name.charAt(0))); // first letter capitalized
		int length = name.length();
		for (int i = 1; i < length; i++) {
			char c = name.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(" ");
			}
			sb.append(c);
		}
		return sb.toString();
	}

}
