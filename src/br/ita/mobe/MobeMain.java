package br.ita.mobe;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import br.ita.mobe.widget.NumberPicker;

public class MobeMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LayoutParams defaultParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		LinearLayout container = new LinearLayout(this);
		container.setOrientation(LinearLayout.VERTICAL);
		container.setLayoutParams(defaultParams);

		NumberPicker numberPicker = new NumberPicker(this);
		container.addView(numberPicker);

		setContentView(container);
	}
}
