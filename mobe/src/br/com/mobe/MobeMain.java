package br.com.mobe;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.mobe.core.exception.IllegalMetadataException;
import br.com.mobe.core.exception.UnsupportedTypeException;
import br.com.mobe.orm.PersistenceController;

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
					controller.createTables(Bean.class);
				} catch (UnsupportedTypeException e) {
					Log.e(TAG, "Type: " + e.getType(), e);
				} catch (IllegalMetadataException e) {
					Log.e(TAG, "Entity: " + e.getEntity(), e);
				}
			}
		});

		Button db_save = (Button) findViewById(R.id.db_save);
		db_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				PersistenceController controller = new PersistenceController(MobeMain.this);
				Bean bean = new Bean();
				controller.save(bean);
			}
		});

		Button db_list = (Button) findViewById(R.id.db_list);
		db_list.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				PersistenceController controller = new PersistenceController(MobeMain.this);
				List<Bean> list = controller.list(Bean.class);
				Log.d(TAG, "list size = " + list.size());
				for (Bean bean : list) {
					Log.d(TAG, bean.toString());
				}
			}
		});
	}
}
