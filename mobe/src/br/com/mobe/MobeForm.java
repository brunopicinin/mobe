package br.com.mobe;

import java.lang.reflect.Field;
import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.mobe.view.FormView;
import br.com.mobe.view.ViewController;
import br.com.mobe.view.logic.layer.GenerateFormLayer;
import br.com.mobe.view.logic.layer.PopulateFormLayer;

public class MobeForm extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form);
		String action = getIntent().getExtras().getString("action");
		handleIntent(action);
	}

	private void handleIntent(String action) {
		LinearLayout container = (LinearLayout) findViewById(R.id.container);

		ViewController controller = null;
		if (action.equals("generate")) {
			controller = new ViewController(this, new GenerateFormLayer());
		} else if (action.equals("populate")) {
			controller = new ViewController(this, new GenerateFormLayer(), new PopulateFormLayer());
		} else {
			return;
		}

		Object bean = new Bean();
		FormView form = controller.generateForm(bean);
		container.addView(form, 0);

		Button printBean = (Button) findViewById(R.id.printBean);
		printBean.setOnClickListener(new CListener(form));
	}

	class CListener implements OnClickListener {
		private FormView form;

		public CListener(FormView form) {
			this.form = form;
		}

		@Override
		public void onClick(View v) {
			try {
				print(form.getBean());
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