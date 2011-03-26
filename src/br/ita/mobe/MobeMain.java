package br.ita.mobe;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import br.ita.mobe.example.MyEntity;
import br.ita.mobe.proxy.Field;
import br.ita.mobe.reflection.EntityParser;
import br.ita.mobe.view.generator.ViewGenerator;

public class MobeMain extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		List<Field> fields = EntityParser.parseFields(new MyEntity());
		// List<Field> fields = EntityParser.parseFields(new FullEntity());

		LinearLayout form = (LinearLayout) findViewById(R.id.form);

		for (Field field : fields) {
			LinearLayout fieldInput = ViewGenerator.generateFieldInput(MobeMain.this, field);
			form.addView(fieldInput);
		}
	}

}
