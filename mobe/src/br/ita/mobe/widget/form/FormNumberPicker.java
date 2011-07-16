package br.ita.mobe.widget.form;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import android.content.Context;
import br.ita.mobe.widget.NumberPicker;

public class FormNumberPicker extends FormWidget {

	public FormNumberPicker(Context context, String name) {
		super(context, name);
		NumberPicker numberPicker = new NumberPicker(context);
		numberPicker.setLayoutParams(new LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
		addView(numberPicker);
	}

}
