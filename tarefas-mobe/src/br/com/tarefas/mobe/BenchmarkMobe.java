package br.com.tarefas.mobe;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.widget.LinearLayout;
import br.com.mobe.orm.PersistenceController;
import br.com.mobe.view.FormView;
import br.com.mobe.view.ViewController;

public class BenchmarkMobe extends Activity {

	private static final int TOTAL_TIMES = 50;

	private ViewController vc;
	private PersistenceController pc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.benchmark);

		vc = new ViewController(this);
		pc = new PersistenceController(this);

		// start benchmark
		Debug.startMethodTracing("mobe");

		deleteDatabase("mobeorm.db");
		pc.createTables(Task.class);

		// createTenTasks();
		createHundredTasks();

		for (int i = 0; i < TOTAL_TIMES; i++) {
			// benchmarkGenerateForm();

			// benchmarkGeneratePopulateForm();

			// deleteDatabase("mobeorm.db");
			// benchmarkCreateTable();

			// benchmarkSave();

			// benchmarkList();

			// benchmarkFindById();

			// benchmarkUpdate();

			benchmarkDelete(i + 1);
		}

		// finish benchmark
		Debug.stopMethodTracing();

		setResult(RESULT_OK);
		finish();
	}

	private void createTenTasks() {
		for (int i = 0; i < 10; i++) {
			Task task = new Task("titulo", 10, Calendar.getInstance(), true);
			pc.save(task);
		}
	}

	private void createHundredTasks() {
		for (int i = 0; i < 100; i++) {
			Task task = new Task("titulo", 10, Calendar.getInstance(), true);
			pc.save(task);
		}
	}

	private void benchmarkGenerateForm() {
		setContentView(R.layout.task_edit);
		FormView form = vc.generateForm(new Task());
		LinearLayout container = (LinearLayout) findViewById(R.id.container);
		container.addView(form, 0);
		setContentView(R.layout.benchmark);
	}

	private void benchmarkGeneratePopulateForm() {
		setContentView(R.layout.task_edit);
		Task task = new Task("title", 10, Calendar.getInstance(), true);
		FormView form = vc.generatePopulateForm(task);
		LinearLayout container = (LinearLayout) findViewById(R.id.container);
		container.addView(form, 0);
		setContentView(R.layout.benchmark);
	}

	private void benchmarkCreateTable() {
		pc.createTables(Task.class);
	}

	private void benchmarkSave() {
		Task task = new Task("title", 10, Calendar.getInstance(), true);
		pc.save(task);
	}

	private void benchmarkList() {
		pc.list(Task.class);
	}

	private void benchmarkFindById() {
		pc.findById(Task.class, 5);
	}

	private void benchmarkUpdate() {
		Task task = new Task("novo titulo", 2, Calendar.getInstance(), false);
		task.setId(5L);
		pc.update(task);
	}

	private void benchmarkDelete(int id) {
		Task task = new Task();
		task.setId((long) id);
		pc.delete(task);
	}

}
