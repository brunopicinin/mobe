package br.com.tarefas.mobe;

import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Debug;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import br.com.mobe.orm.PersistenceController;

public class TaskMobe extends ListActivity {

	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	private static final int ACTIVITY_BENCHMARK = 2;

	private static final int INSERT_ID = Menu.FIRST;
	private static final int DELETE_ID = Menu.FIRST + 1;
	private static final int BENCHMARK_ID = Menu.FIRST + 2;

	public static final String KEY_ROWID = "id";

	private PersistenceController persistenceController;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Debug.startMethodTracing("mobe");
		setContentView(R.layout.tasks_list);
		persistenceController = new PersistenceController(this);
		fistTime();
		fillData();
		registerForContextMenu(getListView());
	}

	@Override
	protected void onDestroy() {
		Debug.stopMethodTracing();
		super.onDestroy();
	}

	private void fistTime() {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		if (prefs.getBoolean("firstTime", true)) {
			persistenceController.createTables(Task.class);
			SharedPreferences.Editor editor = prefs.edit();
			editor.putBoolean("firstTime", false);
			editor.commit();
		}
	}

	private void fillData() {
		List<Task> list = persistenceController.list(Task.class);
		MyAdapter adapter = new MyAdapter(this, R.layout.tasks_row, list);
		setListAdapter(adapter);
	}

	class MyAdapter extends ArrayAdapter<Task> {

		public MyAdapter(Context context, int textViewResourceId, List<Task> objects) {
			super(context, textViewResourceId, objects);
		}

		@Override
		public long getItemId(int position) {
			Task task = getItem(position);
			return task.getId();
		}

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
		Intent intent = new Intent(this, BenchmarkMobe.class);
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
			Task task = new Task();
			task.setId(info.id);
			persistenceController.delete(task);
			fillData();
			return true;
		}
		return super.onContextItemSelected(item);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(this, TaskEdit.class);
		intent.putExtra(KEY_ROWID, id);
		startActivityForResult(intent, ACTIVITY_EDIT);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		fillData();
	}

}
