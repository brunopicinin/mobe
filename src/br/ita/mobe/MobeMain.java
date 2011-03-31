package br.ita.mobe;

import java.lang.reflect.Field;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.ita.mobe.example.Bean1;
import br.ita.mobe.view.generation.ViewGenerator;

public class MobeMain extends Activity {

	private Bean1 mBean;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mBean = new Bean1();

		ViewGenerator viewGenerator = new ViewGenerator();
		ViewGroup viewGroup = viewGenerator.generateBeanView(MobeMain.this, mBean);

		LinearLayout formContainer = (LinearLayout) findViewById(R.id.form_container);
		formContainer.addView(viewGroup);

		Button printButton = new Button(MobeMain.this);
		printButton.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		printButton.setText("Print");
		printButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					printBeanFields();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		});
		viewGroup.addView(printButton);
	}

	private void printBeanFields() throws IllegalArgumentException, IllegalAccessException {
		LinearLayout printContainer = (LinearLayout) findViewById(R.id.print_bean);
		for (Field field : mBean.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			TextView textView = new TextView(MobeMain.this);
			textView.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
			textView.setText(field.getName() + ": " + field.get(mBean));
			printContainer.addView(textView);
		}
		View ruler = new View(MobeMain.this);
		ruler.setBackgroundColor(0xFFcccccc);
		printContainer.addView(ruler, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, 2));
	}

}
