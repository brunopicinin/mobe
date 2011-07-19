package br.ita.mobe.widget.form;

import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.text.InputType;
import android.widget.EditText;

public class FormDate extends FormWidget {

	private EditText editText;

	public FormDate(Context context, String name, String displayName) {
		super(context, name, displayName);
		editText = new EditText(context);
		editText.setInputType(InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_DATE);
		editText.setHint("Example: 30/10/2000");
		addView(editText);
	}

	@Override
	public void populateWg(Object value) {
		Calendar calendar = null;
		if (value instanceof Date) {
			calendar = Calendar.getInstance();
			calendar.setTime((Date) value);
		} else if (value instanceof Calendar) {
			calendar = (Calendar) value;
		}
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = calendar.get(Calendar.MONTH) + 1;
		int year = calendar.get(Calendar.YEAR);
		editText.setText(day + "/" + month + "/" + year);
	}
}
