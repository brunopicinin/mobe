package br.com.tarefas.mobe;

import java.util.Calendar;

import android.app.Activity;
import android.view.ViewGroup;
import br.com.mobe.view.FormView;
import br.com.mobe.view.ViewController;

public class TaskVisualization extends Activity {

	public void emptyTaskForm() {
		// set XML layout as main
		setContentView(R.layout.main);

		// generate form
		Task task = new Task();
		ViewController controller = new ViewController(this);
		FormView form = controller.generateForm(task);

		// insert form into layout
		ViewGroup container = (ViewGroup) findViewById(R.id.container);
		container.addView(form);
	}

	public void populatedTaskForm() {
		// generate and populate form
		Task task = new Task();
		task.setTitle("Nova tarefa");
		task.setImportance(10);
		task.setDeadline(tomorrow());
		task.setDone(false);
		ViewController controller = new ViewController(this);
		FormView form = controller.generatePopulateForm(task);

		// set form as main layout
		setContentView(form);
	}

	private static Calendar tomorrow() {
		// TODO Auto-generated method stub
		return null;
	}

}
