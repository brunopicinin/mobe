package br.ita.mobe.test.widget;

import br.ita.mobe.widget.FormView;
import android.content.Context;
import android.test.InstrumentationTestCase;

public class FormViewTest extends InstrumentationTestCase {

	public void testA() {
		Context context = this.getInstrumentation().getContext();
		FormView formView = new FormView(context);
		assertNotNull(formView);

	}
}
