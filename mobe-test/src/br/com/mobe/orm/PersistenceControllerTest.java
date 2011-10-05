package br.com.mobe.orm;

import java.util.Calendar;
import java.util.List;

import pojo.full.ClassAnnotation;
import pojo.semi.DefaultBean;
import pojo.semi.pk.SimplePkBean;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import br.com.mobe.TestUtils;
import br.com.mobe.core.exception.UnsupportedTypeException;
import br.com.mobe.orm.db.DatabaseHelper;
import br.com.mobe.orm.db.DbUtils;
import br.com.mobe.orm.exception.DatabaseException;

public class PersistenceControllerTest extends AndroidTestCase {

	private Context context;
	private PersistenceController controller;
	private Class<?>[] classes = { ClassAnnotation.class, DefaultBean.class, SimplePkBean.class };

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

	public void testCreateTables() throws Exception {
		// Table was create on setUp, just test if it exists.
		SQLiteDatabase database = getReadableDatabase();
		int len = classes.length;
		String[] args = new String[len];
		for (int i = 0; i < len; i++) {
			args[i] = DbUtils.getTableName(classes[i]);
		}
		StringBuilder sb = new StringBuilder("SELECT * FROM sqlite_master WHERE type='table' AND (name=?");
		if (len >= 1) {
			for (int i = 1; i < len; i++) {
				sb.append(" OR name=?");
			}
		} else {
			throw new Exception("Wrong test configuration.");
		}
		sb.append(");");
		Cursor cursor = database.rawQuery(sb.toString(), args);
		int count = cursor.getCount();
		assertEquals(len, count);
	}

	private SQLiteDatabase getReadableDatabase() {
		DatabaseHelper helper = new DatabaseHelper(context, PersistenceController.name, PersistenceController.version);
		SQLiteDatabase database = helper.getReadableDatabase();
		return database;
	}

	public void testSave() throws UnsupportedTypeException, IllegalArgumentException, IllegalAccessException, DatabaseException {
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

	public void testSaveSimplePk() throws UnsupportedTypeException, DatabaseException {
		SimplePkBean bean = new SimplePkBean(TestUtils.generateRandomPk());
		controller.save(bean);
		SimplePkBean bean2 = new SimplePkBean(TestUtils.generateRandomPk());
		controller.save(bean2);
		SimplePkBean bean3 = new SimplePkBean(TestUtils.generateRandomPk());
		controller.save(bean3);

		SQLiteDatabase db = getReadableDatabase();
		String table = DbUtils.getTableName(SimplePkBean.class);
		Cursor query = db.query(table, null, null, null, null, null, null);
		assertEquals(3, query.getCount());
	}

	public void testList() throws UnsupportedTypeException, DatabaseException {
		DefaultBean bean1 = new DefaultBean();
		bean1.setBol1(false);
		bean1.getCal1().add(Calendar.YEAR, 2);
		controller.save(bean1);

		DefaultBean bean2 = new DefaultBean();
		bean2.setInt1(-2);
		bean2.getDat1().setYear(3000);
		controller.save(bean2);

		DefaultBean bean3 = new DefaultBean();
		bean3.setDob1(3.14);
		controller.save(bean3);

		DefaultBean bean4 = new DefaultBean();
		bean4.setCha1('z');
		controller.save(bean4);

		DefaultBean bean5 = new DefaultBean();
		bean5.setStr1("good bye");
		controller.save(bean5);

		List<DefaultBean> list = controller.list(DefaultBean.class);
		assertEquals(5, list.size());

		assertTrue(list.contains(bean1));
		assertTrue(list.contains(bean2));
		assertTrue(list.contains(bean3));
		assertTrue(list.contains(bean4));
		assertTrue(list.contains(bean5));
	}

	public void testListSimplePk() throws UnsupportedTypeException, DatabaseException {
		SimplePkBean bean1 = new SimplePkBean(TestUtils.generateRandomPk());
		controller.save(bean1);

		SimplePkBean bean2 = new SimplePkBean(TestUtils.generateRandomPk());
		controller.save(bean2);

		SimplePkBean bean3 = new SimplePkBean(TestUtils.generateRandomPk());
		controller.save(bean3);

		SimplePkBean bean4 = new SimplePkBean(TestUtils.generateRandomPk());
		controller.save(bean4);

		SimplePkBean bean5 = new SimplePkBean(TestUtils.generateRandomPk());
		controller.save(bean5);

		List<SimplePkBean> list = controller.list(SimplePkBean.class);
		assertEquals(5, list.size());

		assertTrue(list.contains(bean1));
		assertTrue(list.contains(bean2));
		assertTrue(list.contains(bean3));
		assertTrue(list.contains(bean4));
		assertTrue(list.contains(bean5));
	}

}
