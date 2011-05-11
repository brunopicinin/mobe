package br.ita.mobe.app;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import br.ita.mobe.annotation.View;
import br.ita.mobe.widget.FormWidget;

public class FormActivity extends Activity {

	// Binded java bean
	private Object bean;

	private ScrollView viewport;
	private LinearLayout container;
	private List<FormWidget> widgets;

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
		generateForm();
	}

	private void generateForm() {
		widgets = new ArrayList<FormWidget>();

		viewport = new ScrollView(this);
		viewport.setLayoutParams(new LayoutParams(FILL_PARENT, FILL_PARENT));

		container = new LinearLayout(this);
		container.setOrientation(LinearLayout.VERTICAL);
		container.setLayoutParams(new LayoutParams(FILL_PARENT, WRAP_CONTENT));

		Class<?> clazz = bean.getClass();
		boolean viewClass = clazz.isAnnotationPresent(View.class);
		for (Field field : clazz.getDeclaredFields()) {
			if (viewClass || field.isAnnotationPresent(View.class)) {
				field.setAccessible(true);
				FormWidget wg = createWidget(field);
				widgets.add(wg);
			}
		}
		for (FormWidget widget : widgets) {
			container.addView(widget.getView());
		}

		viewport.addView(container);
		setContentView(viewport);
	}

	/**
	 * Factory method to generate widget based on bean field.
	 * 
	 * @param field
	 * @return generated widget
	 */
	private FormWidget createWidget(Field field) {
		return new FormWidget(getApplicationContext(), field);
	}
}
