package br.com.mobe.orm.db;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import pojo.NoEntityBean;
import pojo.core.AllFieldsBean;
import pojo.core.CompleteBean;
import pojo.core.NoFieldsBean;
import pojo.core.SomeFieldsBean;
import pojo.orm.NotNullFieldBean;
import pojo.orm.UniqueFieldBean;
import pojo.orm.id.AutoIncrementIdBean;
import pojo.orm.id.DuplicatedIdBean;
import pojo.orm.id.LongIdBean;
import pojo.orm.id.StringIdBean;
import br.com.mobe.core.exception.IllegalMetadataException;

public class DatabaseBuilderTest extends DatabaseBuilder {

	@Test
	public void allFields() throws Exception {
		Class<?> clazz = AllFieldsBean.class;
		String name = DbUtils.getTableName(clazz);
		String[] sqls = generateSQLs(clazz, name);
		assertEquals("CREATE TABLE allfieldsbean (bol1 BOOLEAN , int1 INTEGER , dob1 REAL , str1 TEXT , cal1 DATE );", sqls[0]);
		assertEquals("DROP TABLE allfieldsbean;", sqls[1]);
	}

	@Test(expected = IllegalMetadataException.class)
	public void noFields() throws Exception {
		Class<?> clazz = NoFieldsBean.class;
		String name = DbUtils.getTableName(clazz);
		generateSQLs(clazz, name);
	}

	@Test
	public void someFields() throws Exception {
		Class<?> clazz = SomeFieldsBean.class;
		String name = DbUtils.getTableName(clazz);
		String[] sqls = generateSQLs(clazz, name);
		assertEquals("CREATE TABLE somefieldsbean (bol1 BOOLEAN , int1 INTEGER , str1 TEXT , cal1 DATE );", sqls[0]);
		assertEquals("DROP TABLE somefieldsbean;", sqls[1]);
	}

	@Test
	public void completeBean() throws Exception {
		Class<?> clazz = CompleteBean.class;
		String name = DbUtils.getTableName(clazz);
		String[] sqls = generateSQLs(clazz, name);
		assertEquals("CREATE TABLE completebean (bol1 BOOLEAN , byt1 INTEGER , sho1 INTEGER , int1 INTEGER , lon1 INTEGER , flo1 REAL , dob1 REAL , cha1 TEXT , str1 TEXT , cal1 DATE , dat1 DATE );",
				sqls[0]);
		assertEquals("DROP TABLE completebean;", sqls[1]);
	}

	@Test(expected = IllegalMetadataException.class)
	public void noEntity() throws Exception {
		Class<?> clazz = NoEntityBean.class;
		String name = DbUtils.getTableName(clazz);
		generateSQLs(clazz, name);
	}

	@Test
	public void longId() throws Exception {
		Class<?> clazz = LongIdBean.class;
		String name = DbUtils.getTableName(clazz);
		String[] sqls = generateSQLs(clazz, name);
		assertEquals("CREATE TABLE longidbean (id INTEGER NOT NULL PRIMARY KEY , bol1 BOOLEAN , int1 INTEGER , dob1 REAL , str1 TEXT , cal1 DATE );", sqls[0]);
		assertEquals("DROP TABLE longidbean;", sqls[1]);
	}

	@Test
	public void autoIncrement() throws Exception {
		Class<?> clazz = AutoIncrementIdBean.class;
		String name = DbUtils.getTableName(clazz);
		String[] sqls = generateSQLs(clazz, name);
		assertEquals("CREATE TABLE autoincrementidbean (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , bol1 BOOLEAN , int1 INTEGER , dob1 REAL , str1 TEXT , cal1 DATE );", sqls[0]);
		assertEquals("DROP TABLE autoincrementidbean;", sqls[1]);
	}

	@Test
	public void stringId() throws Exception {
		Class<?> clazz = StringIdBean.class;
		String name = DbUtils.getTableName(clazz);
		String[] sqls = generateSQLs(clazz, name);
		assertEquals("CREATE TABLE stringidbean (strid TEXT NOT NULL PRIMARY KEY , bol1 BOOLEAN , int1 INTEGER , dob1 REAL , str1 TEXT , cal1 DATE );", sqls[0]);
		assertEquals("DROP TABLE stringidbean;", sqls[1]);
	}

	@Test(expected = IllegalMetadataException.class)
	public void duplicatedId() throws Exception {
		Class<?> clazz = DuplicatedIdBean.class;
		String name = DbUtils.getTableName(clazz);
		generateSQLs(clazz, name);
	}

	@Test
	public void notNullField() throws Exception {
		Class<?> clazz = NotNullFieldBean.class;
		String name = DbUtils.getTableName(clazz);
		String[] sqls = generateSQLs(clazz, name);
		assertEquals("CREATE TABLE notnullfieldbean (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , bol1 BOOLEAN , int1 INTEGER , dob1 REAL , str1 TEXT NOT NULL , cal1 DATE );", sqls[0]);
		assertEquals("DROP TABLE notnullfieldbean;", sqls[1]);
	}

	@Test
	public void uniqueField() throws Exception {
		Class<?> clazz = UniqueFieldBean.class;
		String name = DbUtils.getTableName(clazz);
		String[] sqls = generateSQLs(clazz, name);
		assertEquals("CREATE TABLE uniquefieldbean (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT , bol1 BOOLEAN , int1 INTEGER UNIQUE , dob1 REAL , str1 TEXT , cal1 DATE );", sqls[0]);
		assertEquals("DROP TABLE uniquefieldbean;", sqls[1]);
	}

}
