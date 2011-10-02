package br.ita.mobe;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.ita.mobe.exception.UnsupportedTypeException;
import br.ita.mobe.pattern.PersistenceController;

/**
 * Application Client.
 * 
 * Class with the simple purpose of testing the framework. Not deployed.
 */
public class MobeMain extends Activity {

	protected static final String TAG = "MobeMain";

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

		Button database = (Button) findViewById(R.id.db_create);
		database.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					PersistenceController controller = new PersistenceController(MobeMain.this);
					controller.createTables(Bean1.class, Bean2.class);
				} catch (SQLiteException e) {
					e.printStackTrace();
				} catch (UnsupportedTypeException e) {
					e.printStackTrace();
				}
			}
		});

		Button db_save = (Button) findViewById(R.id.db_save);
		db_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					PersistenceController controller = new PersistenceController(MobeMain.this);
					Bean2 bean2 = new Bean2();
					controller.save(bean2);
				} catch (UnsupportedTypeException e) {
					Log.e(TAG, "Type: " + e.getType(), e);
				}
			}
		});

		Button db_list = (Button) findViewById(R.id.db_list);
		db_list.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				PersistenceController controller = new PersistenceController(MobeMain.this);
				List<Bean2> list = controller.list(Bean2.class);
				Log.d(TAG, "list size = " + list.size());
				for (Bean2 bean : list) {
					Log.d(TAG, bean.toString());
				}
			}
		});
	}
}
