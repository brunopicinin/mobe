package br.ita.mobe.app;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ScrollView;
import br.ita.mobe.widget.FormView;

public class FormActivity extends Activity {

	private ScrollView viewport;
	private FormView formView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		viewport = new ScrollView(this);
		viewport.setLayoutParams(new LayoutParams(FILL_PARENT, FILL_PARENT));

		formView = new FormView(this);

		viewport.addView(formView);
		setContentView(viewport);
	}

	public FormView getFormView() {
		return formView;
	}

	public Object getFormBean() {
		return formView.getFormBean();
	}

	public void setFormBean(Object formBean) {
		formView.setFormBean(formBean);
	}

}
