package br.com.mobe.orm;

import pojo.full.ClassAnnotation;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.test.AndroidTestCase;
import br.com.mobe.core.exception.UnsupportedTypeException;
import br.com.mobe.orm.db.DatabaseHelper;

public class PersistenceControllerTest extends AndroidTestCase {

	private Context context;
	private PersistenceController controller;
	private Class<?>[] classes = { ClassAnnotation.class };

	@Override
	protected void setUp() throws Exception {
		context = getContext();
		controller = new PersistenceController(context);
		controller.createTables(classes);
	}

	@Override
	protected void tearDown() throws Exception {
		context.deleteDatabase(PersistenceController.name);
	}

	public void testCreateTables() throws SQLiteException, UnsupportedTypeException {
		// Table was create on setUp, just test if it exists.
		SQLiteDatabase database = getReadableDatabase();
		String[] args = { ClassAnnotation.class.getSimpleName().toLowerCase() };
		Cursor cursor = database.rawQuery("SELECT * FROM sqlite_master WHERE type='table' AND name=?;", args);
		int count = cursor.getCount();
		assertEquals(1, count);
	}

	private SQLiteDatabase getReadableDatabase() {
		DatabaseHelper helper = new DatabaseHelper(context, PersistenceController.name, PersistenceController.version);
		SQLiteDatabase database = helper.getReadableDatabase();
		return database;
	}

	public void testSave() throws UnsupportedTypeException {
		ClassAnnotation bean = new ClassAnnotation();
		long id = controller.save(bean);
		SQLiteDatabase database = getReadableDatabase();
		String table = ClassAnnotation.class.getSimpleName().toLowerCase();
		String[] columns = { "rowid" };
		String[] args = { String.valueOf(id) };
		Cursor cursor = database.query(table, columns, "rowid=?", args, null, null, null);
		int count = cursor.getCount();
		assertEquals(1, count);
	}
}
