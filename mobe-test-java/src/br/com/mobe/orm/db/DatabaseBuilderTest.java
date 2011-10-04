package br.com.mobe.orm.db;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

import pojo.DefaultBean;
import pojo.pk.MultiplePkBean;
import pojo.pk.SimplePkBean;
import br.com.mobe.core.exception.UnsupportedTypeException;

public class DatabaseBuilderTest {

	@Test
	public void generateSQLs_noPk() throws UnsupportedTypeException {
		DatabaseBuilder builder = new DatabaseBuilder();
		Class<?> clazz = DefaultBean.class;
		String name = DbUtils.getTableName(clazz);
		String[] sql = builder.generateSQLs(clazz, name);
		assertEquals("CREATE TABLE defaultbean (bol1 BOOLEAN, int1 INTEGER, dob1 REAL, cha1 TEXT, str1 TEXT, cal1 DATE, dat1 DATE);", sql[0]);
		assertEquals("DROP TABLE defaultbean;", sql[1]);
	}

	@Test
	public void generateSQLs_SimplePk() throws UnsupportedTypeException {
		DatabaseBuilder builder = new DatabaseBuilder();
		Class<?> clazz = SimplePkBean.class;
		String name = DbUtils.getTableName(clazz);
		String[] sql = builder.generateSQLs(clazz, name);
		assertEquals("CREATE TABLE simplepkbean (pk INTEGER, bol1 BOOLEAN, int1 INTEGER, flo1 REAL, str1 TEXT, PRIMARY KEY(pk));", sql[0]);
		assertEquals("DROP TABLE simplepkbean;", sql[1]);
	}

	@Test
	public void generateSQLs_MultiplePk() throws UnsupportedTypeException {
		DatabaseBuilder builder = new DatabaseBuilder();
		Class<?> clazz = MultiplePkBean.class;
		String name = DbUtils.getTableName(clazz);
		String[] sql = builder.generateSQLs(clazz, name);
		assertEquals("CREATE TABLE multiplepkbean (pint INTEGER, pstring TEXT, bol1 BOOLEAN, int1 INTEGER, flo1 REAL, str1 TEXT, PRIMARY KEY(pint, pstring));", sql[0]);
		assertEquals("DROP TABLE multiplepkbean;", sql[1]);
	}

}
