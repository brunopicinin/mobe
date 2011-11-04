package br.com.tarefas.convencional;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.widget.CheckBox;
import android.widget.EditText;

public class BenchmarkConvencional extends Activity {

	private static final int TOTAL_TIMES = 50;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.benchmark);

		// start benchmark
		Debug.startMethodTracing("convencional");

		// createTenTasks();

		createHundredTasks();

		for (int i = 0; i < TOTAL_TIMES; i++) {
			// benchmarkGenerateForm();

			// benchmarkGeneratePopulateForm();

			// benchmarkCreateTable();
			// deleteDatabase("tasksdb");

			// benchmarkSave();

			// benchmarkList();

			// benchmarkFindById();

			// benchmarkUpdate();

			benchmarkDelete(i + 1);
		}

		deleteDatabase("tasksdb");

		// finish benchmark
		Debug.stopMethodTracing();

		setResult(RESULT_OK);
		finish();
	}

	private void createTenTasks() {
		TasksDbAdapter adapter = new TasksDbAdapter(this);
		adapter.open();
		for (int i = 0; i < 10; i++) {
			adapter.saveTask("titutlo", 10, Calendar.getInstance(), true);
		}
	}

	private void createHundredTasks() {
		TasksDbAdapter adapter = new TasksDbAdapter(this);
		adapter.open();
		for (int i = 0; i < 100; i++) {
			adapter.saveTask("titutlo", 10, Calendar.getInstance(), true);
		}
	}

	private void benchmarkGenerateForm() {
		setContentView(R.layout.task_edit);
		setContentView(R.layout.benchmark);
	}

	private void benchmarkGeneratePopulateForm() {
		setContentView(R.layout.task_edit);

		EditText titleEdit = (EditText) findViewById(R.id.title_edit);
		EditText importanceEdit = (EditText) findViewById(R.id.importance_edit);
		EditText deadlineEdit = (EditText) findViewById(R.id.deadline_edit);
		CheckBox doneEdit = (CheckBox) findViewById(R.id.done_edit);

		titleEdit.setText("Texto do titudo");
		importanceEdit.setText("10");
		deadlineEdit.setText("30/10/2010");
		doneEdit.setChecked(true);

		setContentView(R.layout.benchmark);
	}

	private void benchmarkCreateTable() {
		TasksDbAdapter adapter = new TasksDbAdapter(this);
		adapter.open();
	}

	private void benchmarkSave() {
		TasksDbAdapter adapter = new TasksDbAdapter(this);
		adapter.open();
		adapter.saveTask("titutlo", 10, Calendar.getInstance(), true);
	}

	private void benchmarkList() {
		TasksDbAdapter adapter = new TasksDbAdapter(this);
		adapter.open();
		adapter.listTasks();
	}

	private void benchmarkFindById() {
		TasksDbAdapter adapter = new TasksDbAdapter(this);
		adapter.open();
		adapter.findTaskById(5);
	}

	private void benchmarkUpdate() {
		TasksDbAdapter adapter = new TasksDbAdapter(this);
		adapter.open();
		adapter.updateTask(5, "novo titulo", 2, Calendar.getInstance(), false);
	}

	private void benchmarkDelete(int id) {
		TasksDbAdapter adapter = new TasksDbAdapter(this);
		adapter.open();
		adapter.deleteTask(id);
	}
}
