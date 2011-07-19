package br.ita.mobe.pattern.logic;

import pojo.full.FieldAnnotation;
import android.content.Context;
import android.test.InstrumentationTestCase;
import android.view.View;
import br.ita.mobe.pattern.logic.process.ViewProcessor;
import br.ita.mobe.pattern.metadata.BeanMetadata;
import br.ita.mobe.pattern.metadata.Repository;
import br.ita.mobe.view.FormView;
import br.ita.mobe.widget.form.FormWidget;

public class ConcreteViewProcessorTest extends InstrumentationTestCase {

	public void testGenerateForm() {
		Context context = this.getInstrumentation().getContext();
		Object bean = new FieldAnnotation();
		BeanMetadata metadata = Repository.getInstance().getMetadata(bean);
		ViewProcessor processor = metadata.getProcessor();
		FormView form = processor.generateForm(context, metadata);
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
