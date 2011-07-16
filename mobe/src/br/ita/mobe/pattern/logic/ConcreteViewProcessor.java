package br.ita.mobe.pattern.logic;

import android.content.Context;
import android.widget.LinearLayout;
import br.ita.mobe.widget.FormView;

public class ConcreteViewProcessor implements ViewProcessor {

	@Override
	public FormView generateForm(Context context) {
		LinearLayout form = new LinearLayout(context);

		return null;
	}

}
