package br.com.mobe.view.logic.process;

import android.content.Context;
import br.com.mobe.view.widget.form.FormWidget;

public abstract class ViewProcessor {

	public abstract FormWidget createWidget(Context context, String name);

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
