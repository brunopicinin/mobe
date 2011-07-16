package br.ita.mobe.pattern.logic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import pojo.full.FieldAnnotation;
import android.content.Context;
import android.test.InstrumentationTestCase;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import br.ita.mobe.pattern.metadata.BeanMetadata;
import br.ita.mobe.pattern.metadata.Repository;
import br.ita.mobe.view.FormView;
import br.ita.mobe.widget.NumberPicker;
import br.ita.mobe.widget.form.FormWidget;

public class ConcreteViewProcessorTest extends InstrumentationTestCase {

	private Context context;

	@Override
	public void setUp() {
		context = this.getInstrumentation().getContext();
	}

	public void testContextInjection() {
		FormView form = new FormView(context);
		assertNotNull(form);
	}

	public void testGenerateForm() {
		Object bean = new FieldAnnotation();
		BeanMetadata metadata = Repository.getInstance().getMetadata(bean.getClass());
		ViewProcessor processor = metadata.getProcessor();
		FormView form = processor.generateForm(context, metadata);
		assertNotNull(form);
		assertTrue(form instanceof FormView);
		int childCount = form.getChildCount();
		assertEquals(4, childCount);

		Map<String, Class<?>> fields = new HashMap<String, Class<?>>();
		fields.put("firstName", String.class);
		fields.put("surname", String.class);
		fields.put("myAge", int.class);
		fields.put("alive", boolean.class);
		Set<String> keys = fields.keySet();

		for (int i = 0; i < childCount; i++) {
			View child = form.getChildAt(i);
			assertTrue(child instanceof FormWidget);
			FormWidget formWidget = (FormWidget) child;
			int wgChildCount = formWidget.getChildCount();
			assertEquals(2, wgChildCount);

			View subChild0 = formWidget.getChildAt(0);
			assertTrue(subChild0 instanceof TextView);
			TextView textView = (TextView) subChild0;
			String name = textView.getText().toString();
			assertTrue(keys.contains(name));

			View subChild1 = formWidget.getChildAt(1);
			if (fields.get(name).equals(String.class)) {
				assertTrue(subChild1 instanceof EditText);
			} else if (fields.get(name).equals(int.class)) {
				assertTrue(subChild1 instanceof NumberPicker);
			} else if (fields.get(name).equals(boolean.class)) {
				assertTrue(subChild1 instanceof CheckBox);
			}
		}
	}
}
