package br.com.mobe.orm;

import pojo.full.ClassAnnotation;
import pojo.semi.DefaultBean;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.test.AndroidTestCase;
import br.com.mobe.core.exception.UnsupportedTypeException;
import br.com.mobe.orm.db.DatabaseHelper;
import br.com.mobe.orm.db.DbUtils;

public class PersistenceControllerTest extends AndroidTestCase {

	private Context context;
	private PersistenceController controller;
	private Class<?>[] classes = { ClassAnnotation.class, DefaultBean.class };

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
		String[] args = { DbUtils.getTableName(ClassAnnotation.class), DbUtils.getTableName(DefaultBean.class) };
		Cursor cursor = database.rawQuery("SELECT * FROM sqlite_master WHERE type='table' AND (name=? OR name=?);", args);
		int count = cursor.getCount();
		assertEquals(2, count);
	}

	private SQLiteDatabase getReadableDatabase() {
		DatabaseHelper helper = new DatabaseHelper(context, PersistenceController.name, PersistenceController.version);
		SQLiteDatabase database = helper.getReadableDatabase();
		return database;
	}

	public void testSave() throws UnsupportedTypeException, IllegalArgumentException, IllegalAccessException {
		DefaultBean bean = new DefaultBean();
		long id = controller.save(bean);

		SQLiteDatabase database = getReadableDatabase();
		String table = DbUtils.getTableName(DefaultBean.class);
		String[] args = { String.valueOf(id) };
		Cursor cursor = database.query(table, null, "rowid=?", args, null, null, null);
		assertEquals(1, cursor.getCount());
		assertEquals(7, cursor.getColumnCount());

		cursor.moveToFirst();
		int index = cursor.getColumnIndex("bol1");
		int bol1 = cursor.getInt(index);
		assertEquals(1, bol1);

		index = cursor.getColumnIndex("int1");
		int int1 = cursor.getInt(index);
		assertEquals(10, int1);

		index = cursor.getColumnIndex("dob1");
		double dob1 = cursor.getDouble(index);
		assertEquals(1.1, dob1);

		index = cursor.getColumnIndex("cha1");
		String cha1 = cursor.getString(index);
		assertEquals("a", cha1);

		index = cursor.getColumnIndex("str1");
		String str1 = cursor.getString(index);
		assertEquals("hello", str1);

		index = cursor.getColumnIndex("cal1");
		long cal1 = cursor.getLong(index);
		assertEquals(bean.getCal1().getTimeInMillis(), cal1);

		index = cursor.getColumnIndex("dat1");
		long dat1 = cursor.getLong(index);
		assertEquals(bean.getDat1().getTime(), dat1);
	}
}
