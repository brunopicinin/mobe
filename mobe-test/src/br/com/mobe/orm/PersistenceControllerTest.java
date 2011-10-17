package br.com.mobe.orm;

import java.util.Calendar;
import java.util.List;

import pojo.core.AllFieldsBean;
import pojo.core.CompleteBean;
import pojo.orm.NotNullFieldBean;
import pojo.orm.UniqueFieldBean;
import pojo.orm.id.AutoIncrementIdBean;
import pojo.orm.id.DuplicatedIdBean;
import pojo.orm.id.LongIdBean;
import pojo.orm.id.StringIdBean;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.test.AndroidTestCase;
import br.com.mobe.PkGenerator;
import br.com.mobe.core.exception.IllegalMetadataException;
import br.com.mobe.orm.db.DatabaseHelper;
import br.com.mobe.orm.db.DbUtils;

public class PersistenceControllerTest extends AndroidTestCase {

	private Context context;
	private PersistenceController controller;
	private Class<?>[] classes = { AllFieldsBean.class, CompleteBean.class, AutoIncrementIdBean.class, LongIdBean.class, StringIdBean.class, NotNullFieldBean.class, UniqueFieldBean.class };

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

	private Cursor listAll(SQLiteDatabase db, String table) {
		return db.query(table, null, null, null, null, null, null);
	}

	private Cursor find(long id, SQLiteDatabase db, String table) {
		String[] args = { String.valueOf(id) };
		return db.query(table, null, "rowid=?", args, null, null, null);
	}

	public void testSave() {
		CompleteBean bean = CompleteBean.getExample();
		long rowid = controller.save(bean);

		SQLiteDatabase database = getReadableDatabase();
		String table = DbUtils.getTableName(CompleteBean.class);
		Cursor cursor = find(rowid, database, table);
		assertEquals(1, cursor.getCount());
		assertEquals(11, cursor.getColumnCount());

		cursor.moveToFirst();
		int index = cursor.getColumnIndex("bol1");
		int bol1 = cursor.getInt(index);
		assertEquals(1, bol1);

		index = cursor.getColumnIndex("byt1");
		int byt1 = cursor.getInt(index);
		assertEquals(2, byt1);

		index = cursor.getColumnIndex("sho1");
		int sho1 = cursor.getInt(index);
		assertEquals(4, sho1);

		index = cursor.getColumnIndex("int1");
		int int1 = cursor.getInt(index);
		assertEquals(8, int1);

		index = cursor.getColumnIndex("lon1");
		long lon1 = cursor.getLong(index);
		assertEquals(16l, lon1);

		index = cursor.getColumnIndex("flo1");
		float flo1 = cursor.getFloat(index);
		assertEquals(1.1f, flo1);

		index = cursor.getColumnIndex("dob1");
		double dob1 = cursor.getDouble(index);
		assertEquals(2.2, dob1);

		index = cursor.getColumnIndex("cha1");
		String cha1 = cursor.getString(index);
		assertEquals("w", cha1);

		index = cursor.getColumnIndex("str1");
		String str1 = cursor.getString(index);
		assertEquals("Hello", str1);

		index = cursor.getColumnIndex("cal1");
		long cal1 = cursor.getLong(index);
		assertEquals(bean.getCal1().getTimeInMillis(), cal1);

		index = cursor.getColumnIndex("dat1");
		long dat1 = cursor.getLong(index);
		assertEquals(bean.getDat1().getTime(), dat1);
	}

	public void testSaveEmptyBean() {
		CompleteBean bean = new CompleteBean();
		long rowid = controller.save(bean);

		SQLiteDatabase database = getReadableDatabase();
		String table = DbUtils.getTableName(CompleteBean.class);
		Cursor cursor = find(rowid, database, table);
		assertEquals(1, cursor.getCount());
		assertEquals(11, cursor.getColumnCount());

		cursor.moveToFirst();
		int index = cursor.getColumnIndex("bol1");
		int bol1 = cursor.getInt(index);
		assertEquals(0, bol1);

		index = cursor.getColumnIndex("byt1");
		int byt1 = cursor.getInt(index);
		assertEquals(0, byt1);

		index = cursor.getColumnIndex("sho1");
		int sho1 = cursor.getInt(index);
		assertEquals(0, sho1);

		index = cursor.getColumnIndex("int1");
		int int1 = cursor.getInt(index);
		assertEquals(0, int1);

		index = cursor.getColumnIndex("lon1");
		long lon1 = cursor.getLong(index);
		assertEquals(0l, lon1);

		index = cursor.getColumnIndex("flo1");
		float flo1 = cursor.getFloat(index);
		assertEquals(0f, flo1);

		index = cursor.getColumnIndex("dob1");
		double dob1 = cursor.getDouble(index);
		assertEquals(0d, dob1);

		index = cursor.getColumnIndex("cha1");
		String cha1 = cursor.getString(index);
		assertEquals(null, cha1);

		index = cursor.getColumnIndex("str1");
		String str1 = cursor.getString(index);
		assertEquals(null, str1);

		index = cursor.getColumnIndex("cal1");
		long cal1 = cursor.getLong(index);
		assertEquals(0, cal1);

		index = cursor.getColumnIndex("dat1");
		long dat1 = cursor.getLong(index);
		assertEquals(0, dat1);
	}

	public void testSaveLongId() {
		LongIdBean bean = new LongIdBean();
		bean.setId(PkGenerator.randomLong());
		long rowid = controller.save(bean);

		SQLiteDatabase db = getReadableDatabase();
		String table = DbUtils.getTableName(LongIdBean.class);
		Cursor cursor = find(rowid, db, table);
		assertEquals(1, cursor.getCount());
		assertEquals(6, cursor.getColumnCount());

		cursor.moveToFirst();
		int index = cursor.getColumnIndex("id");
		long id = cursor.getLong(index);
		assertEquals(bean.getId(), id);
	}

	public void testSaveAutoincrementId() {
		AutoIncrementIdBean bean1 = new AutoIncrementIdBean();
		long save1 = controller.save(bean1);
		assertEquals(save1, bean1.getId());

		AutoIncrementIdBean bean2 = new AutoIncrementIdBean();
		long save2 = controller.save(bean2);
		assertEquals(save2, bean2.getId());

		AutoIncrementIdBean bean3 = new AutoIncrementIdBean();
		long save3 = controller.save(bean3);
		assertEquals(save3, bean3.getId());

		SQLiteDatabase db = getReadableDatabase();
		String table = DbUtils.getTableName(AutoIncrementIdBean.class);

		Cursor cursor = find(save1, db, table);
		cursor.moveToFirst();
		int index = cursor.getColumnIndex("id");
		long id1 = cursor.getLong(index);
		assertEquals(1, id1);

		cursor = find(save2, db, table);
		cursor.moveToFirst();
		index = cursor.getColumnIndex("id");
		long id2 = cursor.getLong(index);
		assertEquals(2, id2);

		cursor = find(save3, db, table);
		cursor.moveToFirst();
		index = cursor.getColumnIndex("id");
		long id3 = cursor.getLong(index);
		assertEquals(3, id3);
	}

	public void testSaveStringId() {
		StringIdBean bean = new StringIdBean();
		bean.setStrId(PkGenerator.randomString());
		long rowid = controller.save(bean);

		SQLiteDatabase db = getReadableDatabase();
		String table = DbUtils.getTableName(StringIdBean.class);
		Cursor cursor = find(rowid, db, table);
		assertEquals(1, cursor.getCount());
		assertEquals(6, cursor.getColumnCount());

		cursor.moveToFirst();
		int index = cursor.getColumnIndex("strid");
		String strid = cursor.getString(index);
		assertEquals(bean.getStrId(), strid);
	}

	public void testSaveDuplicatedId() {
		DuplicatedIdBean bean = new DuplicatedIdBean();
		bean.setId1(1);
		bean.setId2(2);
		boolean exception = false;
		try {
			controller.save(bean);
		} catch (IllegalMetadataException e) {
			exception = true;
		}
		assertTrue(exception);
	}

	public void testSaveSameId() {
		LongIdBean bean1 = new LongIdBean();
		bean1.setId(1);
		controller.save(bean1);
		LongIdBean bean2 = new LongIdBean();
		bean2.setId(1);
		boolean exception = false;
		try {
			controller.save(bean2);
		} catch (SQLiteException e) {
			exception = true;
		}
		assertTrue(exception);
	}

	public void testSaveNullId() {
		StringIdBean bean = new StringIdBean();
		boolean exception = false;
		try {
			controller.save(bean);
		} catch (SQLiteException e) {
			exception = true;
		}
		assertTrue(exception);
	}

	public void testSaveNotNull() {
		NotNullFieldBean bean1 = new NotNullFieldBean();
		bean1.setStr1("hello");
		controller.save(bean1);

		NotNullFieldBean bean2 = new NotNullFieldBean();
		boolean exception = false;
		try {
			controller.save(bean2);
		} catch (SQLiteException e) {
			exception = true;
		}
		assertTrue(exception);
	}

	public void testSaveUnique() {
		UniqueFieldBean bean1 = new UniqueFieldBean();
		bean1.setInt1(1);
		controller.save(bean1);

		UniqueFieldBean bean2 = new UniqueFieldBean();
		bean2.setInt1(1);
		boolean exception = false;
		try {
			controller.save(bean2);
		} catch (SQLiteException e) {
			exception = true;
		}
		assertTrue(exception);
	}

	public void testSaveMultiple() {
		AllFieldsBean bean1 = new AllFieldsBean();
		assertTrue(controller.save(bean1) > 0);
		AllFieldsBean bean2 = new AllFieldsBean();
		assertTrue(controller.save(bean2) > 0);
		AllFieldsBean bean3 = new AllFieldsBean();
		assertTrue(controller.save(bean3) > 0);

		SQLiteDatabase db = getReadableDatabase();
		String table = DbUtils.getTableName(AllFieldsBean.class);
		Cursor cursor = listAll(db, table);
		assertEquals(3, cursor.getCount());
	}

	public void testList() {
		CompleteBean bean1 = new CompleteBean();
		assertTrue(controller.save(bean1) > 0);

		CompleteBean bean2 = new CompleteBean();
		bean2.setBol1(false);
		bean2.setInt1(-1259);
		bean2.setDob1(1234.4321);
		bean2.setStr1("world");
		bean2.setCal1(Calendar.getInstance());
		assertTrue(controller.save(bean2) > 0);

		CompleteBean bean3 = CompleteBean.getExample();
		assertTrue(controller.save(bean3) > 0);

		List<CompleteBean> list = controller.list(CompleteBean.class);
		assertEquals(3, list.size());

		assertTrue(list.contains(bean1));
		assertTrue(list.contains(bean2));
		assertTrue(list.contains(bean3));
	}

	public void testFindById() {
		AutoIncrementIdBean bean1 = new AutoIncrementIdBean();
		controller.save(bean1);
		AutoIncrementIdBean bean2 = new AutoIncrementIdBean();
		controller.save(bean2);
		AutoIncrementIdBean bean3 = new AutoIncrementIdBean();
		controller.save(bean3);

		AutoIncrementIdBean persistentBean = controller.findById(AutoIncrementIdBean.class, bean2.getId());
		assertEquals(bean2, persistentBean);
	}

	public void testDelete() {
		AutoIncrementIdBean bean = new AutoIncrementIdBean();
		controller.save(bean);

		SQLiteDatabase db = getReadableDatabase();
		String table = DbUtils.getTableName(AutoIncrementIdBean.class);
		Cursor cursor = listAll(db, table);
		assertEquals(1, cursor.getCount());

		controller.delete(bean);
		cursor = listAll(db, table);
		assertEquals(0, cursor.getCount());
	}

	public void testDeleteMultiple() {
		AutoIncrementIdBean bean1 = new AutoIncrementIdBean();
		controller.save(bean1);
		AutoIncrementIdBean bean2 = new AutoIncrementIdBean();
		controller.save(bean2);
		AutoIncrementIdBean bean3 = new AutoIncrementIdBean();
		controller.save(bean3);

		SQLiteDatabase db = getReadableDatabase();
		String table = DbUtils.getTableName(AutoIncrementIdBean.class);
		Cursor cursor = listAll(db, table);
		assertEquals(3, cursor.getCount());

		controller.delete(bean1);
		cursor = listAll(db, table);
		assertEquals(2, cursor.getCount());

		controller.delete(bean2);
		cursor = listAll(db, table);
		assertEquals(1, cursor.getCount());

		controller.delete(bean3);
		cursor = listAll(db, table);
		assertEquals(0, cursor.getCount());
	}

	public void testDeleteWrongId() {
		LongIdBean bean = new LongIdBean();
		bean.setId(1);
		controller.save(bean);

		LongIdBean bean2 = new LongIdBean();
		bean2.setId(2);
		boolean delete = controller.delete(bean2);
		assertFalse(delete);
	}

	public void testUpdate() {
		AutoIncrementIdBean bean = new AutoIncrementIdBean();
		controller.save(bean);

		// TODO: substituir por getByPk
		List<AutoIncrementIdBean> list = controller.list(AutoIncrementIdBean.class);
		AutoIncrementIdBean savedBean = list.get(0);
		assertEquals(bean, savedBean);

		bean.setInt1(1234);
		bean.setCal1(Calendar.getInstance());
		bean.setStr1("yes");
		controller.update(bean);

		// TODO: substituir por getByPk
		list = controller.list(AutoIncrementIdBean.class);
		savedBean = list.get(0);
		assertEquals(bean, savedBean);
	}

	public void testUpdateWrongId() {
		LongIdBean bean = new LongIdBean();
		bean.setId(1);
		controller.save(bean);

		bean.setId(2);
		boolean update = controller.update(bean);
		assertFalse(update);
	}
}
