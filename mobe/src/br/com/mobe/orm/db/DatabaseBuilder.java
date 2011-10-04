package br.com.mobe.orm.db;

import static br.com.mobe.core.util.ReflectionUtils.isBoolean;
import static br.com.mobe.core.util.ReflectionUtils.isByte;
import static br.com.mobe.core.util.ReflectionUtils.isCalendar;
import static br.com.mobe.core.util.ReflectionUtils.isChar;
import static br.com.mobe.core.util.ReflectionUtils.isDate;
import static br.com.mobe.core.util.ReflectionUtils.isDouble;
import static br.com.mobe.core.util.ReflectionUtils.isFloat;
import static br.com.mobe.core.util.ReflectionUtils.isInt;
import static br.com.mobe.core.util.ReflectionUtils.isLong;
import static br.com.mobe.core.util.ReflectionUtils.isShort;
import static br.com.mobe.core.util.ReflectionUtils.isString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.mobe.core.exception.UnsupportedTypeException;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Property;
import br.com.mobe.core.metadata.Repository;

public class DatabaseBuilder {

	private Map<String, String[]> tables; // {table name: [SQL create, SQL drop]}

	public DatabaseBuilder() {
		tables = new HashMap<String, String[]>();
	}

	public void addTable(Class<?> clazz) throws UnsupportedTypeException {
		String name = DbUtils.getTableName(clazz);
		StringBuilder createSql = new StringBuilder("create table ").append(name).append(" (");
		ClassMetadata metadata = Repository.getInstance().getMetadata(clazz);
		List<Property> properties = metadata.getProperties();
		for (Property property : properties) {
			Class<?> type = property.getType();
			String propName = DbUtils.getColumnName(property.getName());
			if (isBoolean(type)) {
				createSql.append(propName).append(" boolean, "); // NUMERIC affinity (5) -- save as 0 or 1
			} else if (isByte(type) || isShort(type) || isInt(type) || isLong(type)) {
				createSql.append(propName).append(" integer, "); // INTEGER affinity (1)
			} else if (isFloat(type) || isDouble(type)) {
				createSql.append(propName).append(" real, "); // REAL affinity (4)
			} else if (isChar(type) || isString(type)) {
				createSql.append(propName).append(" text, "); // TEXT affinity (2)
			} else if (isCalendar(type) || isDate(type)) {
				createSql.append(propName).append(" date, "); // NUMERIC affinity (5) -- TODO: rever melhor maneira de persistir data
			} else {
				throw new UnsupportedTypeException();
			}
		}
		int length = createSql.length();
		createSql.delete(length - 2, length);
		createSql.append(");");

		StringBuilder dropSql = new StringBuilder("drop table ").append(name).append(";");

		String[] sql = { createSql.toString(), dropSql.toString() };
		tables.put(name, sql);
	}

	public Set<String> getTables() {
		return tables.keySet();
	}

	public String getSQLCreate(String table) {
		return tables.get(table)[0];
	}

	public String getSQLDrop(String table) {
		return tables.get(table)[1];
	}

}
