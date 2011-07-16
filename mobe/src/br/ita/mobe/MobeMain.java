package br.ita.mobe;

import android.app.Activity;
import android.os.Bundle;
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
		FormView form = controller.generateForm(new Bean1(), this);
		this.setContentView(form);
	}
}
