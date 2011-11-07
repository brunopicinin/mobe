package br.com.tarefas.convencional;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

public class BenchmarkConvencional extends Activity {

	private static final String TAG = "BenchmarkConvencional";

	private static final int TOTAL_TIMES = 50;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.benchmark);

		// timeBenchmark();
		memoryBenchmark();

		// setResult(RESULT_OK);
		// finish();
	}

	private void timeBenchmark() {
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
	}

	private void memoryBenchmark() {
		deleteDatabase("tasksdb");
		// createTenTasks();
		createHundredTasks();

		Debug.startAllocCounting();
		printMemoryInfo("Before");

		// benchmarkGenerateForm();
		// benchmarkGeneratePopulateForm();
		// benchmarkCreateTable();
		// benchmarkSave();
		// benchmarkList();
		// benchmarkFindById();
		// benchmarkUpdate();
		benchmarkDelete(5);

		printMemoryInfo("After");
		Debug.stopAllocCounting();
	}

	private void printMemoryInfo(String state) {
		// int globalAllocCount = Debug.getGlobalAllocCount();
		int globalAllocSize = Debug.getGlobalAllocSize();
		int globalFreedCount = Debug.getGlobalFreedCount();
		// int globalFreedSize = Debug.getGlobalFreedSize();
		int globalGcInvocationCount = Debug.getGlobalGcInvocationCount();
		// int threadAllocCount = Debug.getThreadAllocCount();
		// int threadAllocSize = Debug.getThreadAllocSize();
		// int threadGcInvocationCount = Debug.getThreadGcInvocationCount();

		StringBuilder msg = new StringBuilder("[").append(state).append("] ");
		// msg.append("globalAllocCount=").append(globalAllocCount).append(", ");
		msg.append("AllocSize=").append(globalAllocSize).append(", ");
		msg.append("FreedCount=").append(globalFreedCount).append(", ");
		// msg.append("globalFreedSize=").append(globalFreedSize).append(", ");
		msg.append("GcCount=").append(globalGcInvocationCount).append(", ");
		// msg.append("threadAllocCount=").append(threadAllocCount).append(", ");
		// msg.append("threadAllocSize=").append(threadAllocSize).append(", ");
		// msg.append("threadGcInvocationCount=").append(threadGcInvocationCount).append(", ");

		Log.d(TAG, msg.toString());
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
