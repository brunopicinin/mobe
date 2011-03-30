package br.ita.mobe;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ScrollView;
import br.ita.mobe.example.Bean1;
import br.ita.mobe.view.generation.ViewGenerator;

public class MobeMain extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Bean1 bean = new Bean1();

		ViewGenerator viewGenerator = new ViewGenerator();
		ViewGroup viewGroup = viewGenerator.generateBeanView(MobeMain.this, bean);

		ScrollView scrollView = (ScrollView) findViewById(R.id.form_container);
		scrollView.addView(viewGroup);
	}

}
