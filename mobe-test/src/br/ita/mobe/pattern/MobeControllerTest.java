package br.ita.mobe.pattern;

import pojo.full.FieldAnnotation;
import android.content.Context;
import android.test.InstrumentationTestCase;
import android.view.View;
import br.ita.mobe.view.FormView;
import br.ita.mobe.widget.form.FormWidget;

public class MobeControllerTest extends InstrumentationTestCase {

	public void testGenerateForm() {
		Context context = getInstrumentation().getContext();
		Object bean = new FieldAnnotation();
		MobeController controller = new MobeController();
		FormView form = controller.generateForm(context, bean);
		assertNotNull(form);
		assertTrue(form instanceof FormView);

		int childCount = form.getChildCount();
		assertEquals(4, childCount);

		for (int i = 0; i < childCount; i++) {
			View child = form.getChildAt(i);
			assertTrue(child instanceof FormWidget);
		}
	}

}
