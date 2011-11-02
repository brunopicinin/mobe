package br.com.tarefas.mobe;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import br.com.mobe.orm.PersistenceController;
import br.com.mobe.view.FormView;
import br.com.mobe.view.ViewController;

public class TaskEdit extends Activity {

	private ViewController viewController;
	private PersistenceController persistenceController;

	private FormView form;

	private Long rowid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_edit);

		viewController = new ViewController(this);
		persistenceController = new PersistenceController(this);

		rowid = (savedInstanceState == null) ? null : (Long) savedInstanceState.getSerializable(TaskMobe.KEY_ROWID);
		if (rowid == null) {
			Bundle extras = getIntent().getExtras();
			rowid = (extras == null) ? null : extras.getLong(TaskMobe.KEY_ROWID);
		}

		if (rowid == null) {
			Task task = new Task();
			form = viewController.generateForm(task);
		} else {
			Task task = persistenceController.findById(Task.class, rowid);
			form = viewController.generatePopulateForm(task);
		}

		LinearLayout container = (LinearLayout) findViewById(R.id.container);
		container.addView(form, 0);

		Button confirmButton = (Button) findViewById(R.id.confirm_button);
		confirmButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
			}
		});
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveState();
		outState.putSerializable(TaskMobe.KEY_ROWID, rowid);
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}

	private void saveState() {
		Task task = (Task) form.getBean();
		if (rowid == null) {
			long id = persistenceController.save(task);
			if (id > 0) {
				rowid = id;
			}
		} else {
			persistenceController.update(task);
		}
	}

}
