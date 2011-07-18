package br.ita.mobe;

import static android.view.ViewGroup.LayoutParams.FILL_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.ScrollView;
import br.ita.mobe.pattern.MobeController;
import br.ita.mobe.view.FormView;

/**
 * Application Client.
 * 
 * Class with the simple purpose of testing the framework. Not deployed.
 */
public class MobeMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MobeController controller = new MobeController();
		Object bean = new Bean2();
		FormView form = controller.generateForm(this, bean);
		ScrollView viewport = new ScrollView(this);
		viewport.setLayoutParams(new LayoutParams(FILL_PARENT, WRAP_CONTENT));
		viewport.addView(form);
		this.setContentView(viewport);
	}
}
