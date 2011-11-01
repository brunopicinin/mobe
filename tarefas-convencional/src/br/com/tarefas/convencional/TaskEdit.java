package br.com.tarefas.convencional;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class TaskEdit extends Activity {

	private EditText titleEdit;
	private EditText importanceEdit;
	private EditText deadlineEdit;
	private CheckBox doneEdit;

	private Long rowId;

	private TasksDbAdapter dbAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dbAdapter = new TasksDbAdapter(this);
		dbAdapter.open();

		setContentView(R.layout.task_edit);

		titleEdit = (EditText) findViewById(R.id.title_edit);
		importanceEdit = (EditText) findViewById(R.id.importance_edit);
		deadlineEdit = (EditText) findViewById(R.id.deadline_edit);
		doneEdit = (CheckBox) findViewById(R.id.done_edit);

		rowId = (savedInstanceState == null) ? null : (Long) savedInstanceState.getSerializable(TasksDbAdapter.KEY_ROWID);
		if (rowId == null) {
			Bundle extras = getIntent().getExtras();
			rowId = (extras == null) ? null : extras.getLong(TasksDbAdapter.KEY_ROWID);
		}

		populateFields();

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
		outState.putSerializable(TasksDbAdapter.KEY_ROWID, rowId);
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}

	@Override
	protected void onResume() {
		super.onResume();
		populateFields();
	}

	private void populateFields() {
		if (rowId != null) {
			Cursor task = dbAdapter.findTaskById(rowId);
			startManagingCursor(task);

			titleEdit.setText(task.getString(task.getColumnIndexOrThrow(TasksDbAdapter.KEY_TITLE)));

			int importance = task.getInt(task.getColumnIndexOrThrow(TasksDbAdapter.KEY_IMPORTANCE));
			importanceEdit.setText(String.valueOf(importance));

			int milis = task.getInt(task.getColumnIndexOrThrow(TasksDbAdapter.KEY_DEADLINE));
			Calendar deadline = Calendar.getInstance();
			deadline.setTimeInMillis(milis);
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			deadlineEdit.setText(dateFormat.format(deadline.getTime()));

			doneEdit.setChecked(task.getInt(task.getColumnIndexOrThrow(TasksDbAdapter.KEY_DONE)) != 0);
		}
	}

	private void saveState() {
		String title = titleEdit.getText().toString();
		int importance = Integer.valueOf(importanceEdit.getText().toString());
		Calendar deadline = Calendar.getInstance();
		String dl = deadlineEdit.getText().toString();
		deadline.set(Calendar.DAY_OF_MONTH, Integer.valueOf(dl.substring(0, 2)));
		deadline.set(Calendar.MONTH, Integer.valueOf(dl.substring(3, 5)) - 1);
		deadline.set(Calendar.YEAR, Integer.valueOf(dl.substring(6)));
		boolean done = doneEdit.isChecked();

		if (rowId == null) {
			long id = dbAdapter.saveTask(title, importance, deadline, done);
			if (id > 0) {
				rowId = id;
			}
		} else {
			dbAdapter.updateTask(rowId, title, importance, deadline, done);
		}
	}

}
