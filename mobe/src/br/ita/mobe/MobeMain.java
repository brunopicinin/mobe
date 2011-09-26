package br.ita.mobe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.ita.mobe.pattern.PersistenceController;

/**
 * Application Client.
 * 
 * Class with the simple purpose of testing the framework. Not deployed.
 */
public class MobeMain extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button generate = (Button) findViewById(R.id.generate);
		generate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MobeMain.this, MobeForm.class);
				intent.putExtra("action", "generate");
				startActivity(intent);
			}
		});

		Button populate = (Button) findViewById(R.id.populate);
		populate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MobeMain.this, MobeForm.class);
				intent.putExtra("action", "populate");
				startActivity(intent);
			}
		});

		Button database = (Button) findViewById(R.id.database);
		database.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				PersistenceController pController = new PersistenceController();
				pController.createTables(MobeMain.this, Bean1.class, Bean2.class);
			}
		});
	}
}
