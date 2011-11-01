package br.com.tarefas.convencional;

import java.util.Calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TasksDbAdapter {

	public static final String KEY_TITLE = "title";
	public static final String KEY_IMPORTANCE = "importance";
	public static final String KEY_DEADLINE = "deadline";
	public static final String KEY_DONE = "done";
	public static final String KEY_ROWID = "_id";

	private static final String TAG = "TasksDbAdapter";
	private DatabaseHelper mDbHelper;
	private SQLiteDatabase mDb;

	/**
	 * Database creation sql statement
	 */
	private static final String DATABASE_CREATE = "create table task (_id integer primary key autoincrement, title text, importance integer, deadline date, done boolean);";

	private static final String DATABASE_NAME = "tasksdb";
	private static final String DATABASE_TABLE = "task";
	private static final int DATABASE_VERSION = 1;

	private final Context context;

	private static class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
			db.execSQL("DROP TABLE IF EXISTS task");
			onCreate(db);
		}
	}

	public TasksDbAdapter(Context context) {
		this.context = context;
	}

	public TasksDbAdapter open() {
		mDbHelper = new DatabaseHelper(context);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		mDbHelper.close();
	}

	public long saveTask(String title, int importance, Calendar deadline, boolean done) {
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, title);
		values.put(KEY_IMPORTANCE, importance);
		values.put(KEY_DEADLINE, deadline.getTimeInMillis());
		values.put(KEY_DONE, done);
		return mDb.insert(DATABASE_TABLE, null, values);
	}

	public boolean deleteTask(long rowId) {
		return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}

	public Cursor listTasks() {
		return mDb.query(DATABASE_TABLE, null, null, null, null, null, null);
	}

	public Cursor findTaskById(long rowId) {
		Cursor cursor = mDb.query(DATABASE_TABLE, null, KEY_ROWID + "=" + rowId, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		return cursor;
	}

	public boolean updateTask(long rowId, String title, int importance, Calendar deadline, boolean done) {
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, title);
		values.put(KEY_IMPORTANCE, importance);
		values.put(KEY_DEADLINE, deadline.getTimeInMillis());
		values.put(KEY_DONE, done);
		return mDb.update(DATABASE_TABLE, values, KEY_ROWID + "=" + rowId, null) > 0;
	}
}
