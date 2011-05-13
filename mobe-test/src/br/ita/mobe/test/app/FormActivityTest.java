package br.ita.mobe.test.app;

import java.util.ArrayList;
import java.util.List;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import br.ita.mobe.app.FormActivity;
import br.ita.mobe.test.example.Bean1;
import br.ita.mobe.test.example.Bean2;
import br.ita.mobe.test.example.Bean3;
import br.ita.mobe.widget.NumberPicker;

public class FormActivityTest extends ActivityInstrumentationTestCase2<FormActivity> {

	private FormActivity formActivity;
	private Bean1 bean1; // class annotation
	private Bean2 bean2; // field annotation
	private Bean3 bean3; // no annotation

	public FormActivityTest() {
		super("br.ita.mobe.app", FormActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		formActivity = getActivity();
		bean1 = new Bean1();
		bean2 = new Bean2();
		bean3 = new Bean3();
	}

	public void testPreConditions() {
		assertNotNull(formActivity);
	}

	/**
	 * Get the editable views from a ViewGroup recursively.
	 * 
	 * @param viewGroup
	 *            - The Views container.
	 * @return A list of editable views.
	 */
	private List<View> getEditableViews(ViewGroup viewGroup) {
		List<View> editables = new ArrayList<View>();
		int childCount = viewGroup.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = viewGroup.getChildAt(i);
			if (child instanceof EditText || child instanceof CheckBox || child instanceof NumberPicker) {
				editables.add(child);
			} else if (child instanceof ViewGroup) {
				ViewGroup group = (ViewGroup) child;
				List<View> childEditables = getEditableViews(group);
				editables.addAll(childEditables);
			}
		}
		return editables;
	}

	/**
	 * Test view generation for a Bean without the View annotation.
	 */
	@UiThreadTest
	public void testSetBean_NoAnn() {
		formActivity.setBean(bean3);
		List<View> editableViews = getEditableViews(formActivity.getContainer());
		assertEquals(editableViews.size(), 0);
	}

	/**
	 * Test view generation for a Bean with class annotation.
	 */
	@UiThreadTest
	public void testSetBean_ClassAnn() {
		formActivity.setBean(bean1);
		List<View> editableViews = getEditableViews(formActivity.getContainer());

		assertEquals(4, editableViews.size());

		// boolean visitedBrunoText = false;
		// boolean visitedEmptyText = false;
		// boolean visitedCheckBox = false;
		// boolean visitedNumberPicker = false;
		//
		// for (View child : editableViews) {
		// if (child instanceof EditText) {
		// EditText editText = (EditText) child;
		// String text = editText.getText().toString();
		// if (text.equals("Bruno")) {
		// visitedBrunoText = true;
		// } else if (StringUtils.isEmpty(text)) {
		// visitedEmptyText = true;
		// } else {
		// throw new AssertionError(text);
		// }
		// } else if (child instanceof CheckBox) {
		// CheckBox checkBox = (CheckBox) child;
		// boolean checked = checkBox.isChecked();
		// assertTrue(checked);
		// visitedCheckBox = true;
		// } else if (child instanceof NumberPicker) {
		// NumberPicker numberPicker = (NumberPicker) child;
		// int current = numberPicker.getCurrent();
		// assertEquals(22, current);
		// visitedNumberPicker = true;
		// } else {
		// throw new Error("Wrong view created.");
		// }
		// }
		//
		// if (!(visitedBrunoText && visitedEmptyText && visitedCheckBox && visitedNumberPicker)) {
		// throw new Error("Not all fields populated correctly.");
		// }
	}

	/**
	 * Test view generation for a Bean with field annotations.
	 */
	@UiThreadTest
	public void testSetBean_FieldAnn() {
		formActivity.setBean(bean2);
		List<View> editableViews = getEditableViews(formActivity.getContainer());

		assertEquals(2, editableViews.size());

		// boolean visitedBrunoText = false;
		// boolean visitedNumberPicker = false;
		//
		// for (View child : editableViews) {
		// if (child instanceof EditText) {
		// EditText editText = (EditText) child;
		// String text = editText.getText().toString();
		// assertEquals("Bruno", text);
		// visitedBrunoText = true;
		// } else if (child instanceof NumberPicker) {
		// NumberPicker numberPicker = (NumberPicker) child;
		// int current = numberPicker.getCurrent();
		// assertEquals(22, current);
		// visitedNumberPicker = true;
		// }
		// }
		//
		// if (!(visitedBrunoText && visitedNumberPicker)) {
		// throw new Error("Not all fields populated correctly.");
		// }
	}

}
