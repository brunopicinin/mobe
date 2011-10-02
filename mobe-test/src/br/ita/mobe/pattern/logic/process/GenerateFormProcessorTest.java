package br.ita.mobe.pattern.logic.process;

import pojo.full.FieldAnnotation;
import android.content.Context;
import android.test.InstrumentationTestCase;
import android.view.View;
import br.com.mobe.pattern.logic.process.ViewProcessor;
import br.com.mobe.pattern.metadata.ClassMetadata;
import br.com.mobe.pattern.metadata.Repository;
import br.com.mobe.view.FormView;
import br.com.mobe.widget.form.FormWidget;

public class GenerateFormProcessorTest extends InstrumentationTestCase {

	public void testGenerateForm() {
		Context context = this.getInstrumentation().getContext();
		ClassMetadata metadata = Repository.getInstance().getMetadata(FieldAnnotation.class);
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
