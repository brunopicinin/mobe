package br.com.tarefas.convencional;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class TaskConvencional extends ListActivity {

	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	private static final int ACTIVITY_BENCHMARK = 2;

	private static final int INSERT_ID = Menu.FIRST;
	private static final int DELETE_ID = Menu.FIRST + 1;
	private static final int BENCHMARK_ID = Menu.FIRST + 2;

	private TasksDbAdapter dbAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tasks_list);
		dbAdapter = new TasksDbAdapter(this);
		dbAdapter.open();
		fillData();
		registerForContextMenu(getListView());
	}

	private void fillData() {
		Cursor cursor = dbAdapter.listTasks();
		startManagingCursor(cursor);

		String[] from = new String[] { TasksDbAdapter.KEY_TITLE };
		int[] to = new int[] { R.id.task_title };

		SimpleCursorAdapter tasks = new SimpleCursorAdapter(this, R.layout.tasks_row, cursor, from, to);
		setListAdapter(tasks);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, INSERT_ID, 0, "Criar Tarefa");
		menu.add(0, BENCHMARK_ID, 0, "Benchmark");
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch (item.getItemId()) {
		case INSERT_ID:
			createTask();
			break;
		case BENCHMARK_ID:
			benchmark();
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	private void createTask() {
		Intent intent = new Intent(this, TaskEdit.class);
		startActivityForResult(intent, ACTIVITY_CREATE);
	}

	private void benchmark() {
		Intent intent = new Intent(this, BenchmarkConvencional.class);
		startActivityForResult(intent, ACTIVITY_BENCHMARK);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DELETE_ID, 0, "Deletar Tarefa");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DELETE_ID:
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
			dbAdapter.deleteTask(info.id);
			fillData();
			return true;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(this, TaskEdit.class);
		intent.putExtra(TasksDbAdapter.KEY_ROWID, id);
		startActivityForResult(intent, ACTIVITY_EDIT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		fillData();
	}

}
