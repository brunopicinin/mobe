package br.ita.mobe;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ScrollView;
import br.ita.mobe.pattern.MobeController;
import br.ita.mobe.pattern.logic.layer.GenerateFormLayer;
import br.ita.mobe.pattern.logic.layer.PopulateFormLayer;
import br.ita.mobe.view.FormView;

public class MobeForm extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		String action = getIntent().getExtras().getString("action");
		handleIntent(action);
	}

	private void handleIntent(String action) {
		MobeController controller = null;
		if (action.equals("generate")) {
			controller = new MobeController(new GenerateFormLayer());
		} else if (action.equals("populate")) {
			controller = new MobeController(new GenerateFormLayer(), new PopulateFormLayer());
		}
		Object bean = new Bean2();
		FormView form = controller.generateForm(MobeForm.this, bean);
		ScrollView viewport = new ScrollView(MobeForm.this);
		viewport.setLayoutParams(new LayoutParams(FILL_PARENT, WRAP_CONTENT));
		viewport.addView(form);
		MobeForm.this.setContentView(viewport);
	}
}
