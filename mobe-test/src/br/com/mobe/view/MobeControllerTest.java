package br.com.mobe.view;

import pojo.full.FieldAnnotation;
import android.content.Context;
import android.test.InstrumentationTestCase;
import android.view.View;
import br.com.mobe.view.FormView;
import br.com.mobe.view.ViewController;
import br.com.mobe.view.widget.form.FormWidget;

public class MobeControllerTest extends InstrumentationTestCase {

	public void testGenerateForm() {
		Context context = getInstrumentation().getContext();
		Object bean = new FieldAnnotation();
		ViewController controller = new ViewController(context);
		FormView form = controller.generateForm(bean);
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
