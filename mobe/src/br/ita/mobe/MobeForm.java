package br.ita.mobe;

import java.lang.reflect.Field;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.ita.mobe.pattern.MobeController;
import br.ita.mobe.pattern.logic.layer.GenerateFormLayer;
import br.ita.mobe.pattern.logic.layer.PopulateFormLayer;
import br.ita.mobe.view.FormView;

public class MobeForm extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form);

		String action = getIntent().getExtras().getString("action");
		handleIntent(action);
	}

	private void handleIntent(String action) {
		MobeController controller = null;
		if (action.equals("generate")) {
			controller = new MobeController(new GenerateFormLayer());
		} else if (action.equals("populate")) {
			controller = new MobeController(new GenerateFormLayer(), new PopulateFormLayer());
		} else {
			return;
		}

		LinearLayout container = (LinearLayout) findViewById(R.id.container);

		Object bean = new Bean2();
		FormView form = controller.generateForm(this, bean);
		container.addView(form, 0);

		Button printBean = (Button) findViewById(R.id.printBean);
		printBean.setOnClickListener(new CListener(bean));
	}

	class CListener implements OnClickListener {
		private Object bean;

		public CListener(Object bean) {
			this.bean = bean;
		}

		@Override
		public void onClick(View v) {
			try {
				print(bean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		private void print(Object object) throws IllegalArgumentException, IllegalAccessException {
			TextView tv = (TextView) findViewById(R.id.beanOut);
			StringBuilder sb = new StringBuilder();
			for (Field field : object.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				sb.append(field.getName()).append(": ").append(prettify(field.get(object))).append("\n");
			}
			tv.setText(sb.toString());
		}

		private Object prettify(Object value) {
			if (value instanceof Calendar) {
				Calendar c = ((Calendar) value);
				return c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR);
			}
			return value;
		}
	}

}