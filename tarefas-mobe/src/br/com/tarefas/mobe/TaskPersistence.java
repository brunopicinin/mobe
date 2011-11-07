package br.com.tarefas.mobe;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import br.com.mobe.orm.PersistenceController;

public class TaskPersistence extends Activity {

	public void createTaskTable() {
		PersistenceController controller = new PersistenceController(this);
		controller.createTables(Task.class);
	}

	public void saveTask() {
		Task task = new Task();
		task.setTitle("Nova tarefa");
		task.setImportance(10);
		task.setDeadline(tomorrow());
		task.setDone(false);
		PersistenceController controller = new PersistenceController(this);
		long id = controller.save(task);
	}

	public void listTasks() {
		PersistenceController controller = new PersistenceController(this);
		List<Task> tasks = controller.list(Task.class);
	}

	public void findTaskById() {
		PersistenceController controller = new PersistenceController(this);
		Task task = controller.findById(Task.class, id);
	}

	public void updateTask() {
		Task task = getPersistentTask(); // task save before
		task.setTitle("Updated task name");
		PersistenceController controller = new PersistenceController(this);
		boolean ok = controller.update(task);
	}

	public void deleteTask() {
		Task task = getPersistentTask(); // task save before
		PersistenceController controller = new PersistenceController(this);
		boolean ok = controller.delete(task);
	}

	// ---

	private Task getPersistentTask() {
		// TODO Auto-generated method stub
		return null;
	}

	private Calendar tomorrow() {
		// TODO Auto-generated method stub
		return null;
	}

	private Object id;
}
