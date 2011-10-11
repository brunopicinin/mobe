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
		String[] sql = generateSQLs(clazz, name);
		tables.put(name, sql);
	}

	// public method for testing purposes
	public String[] generateSQLs(Class<?> clazz, String name) throws UnsupportedTypeException {
		// SQL create
		StringBuilder createSQL = new StringBuilder("CREATE TABLE ").append(name).append(" (");
		ClassMetadata metadata = Repository.getInstance().getMetadata(clazz);
		List<Property> properties = metadata.getProperties();
		StringBuilder coldefSQL = new StringBuilder();
		StringBuilder pkSQL = new StringBuilder("PRIMARY KEY(");
		for (Property property : properties) {
			Class<?> type = property.getType();
			String propName = DbUtils.getColumnName(property.getName());
			if (isBoolean(type)) {
				coldefSQL.append(propName).append(" BOOLEAN, "); // NUMERIC affinity (5) -- save as 0 or 1
			} else if (isByte(type) || isShort(type) || isInt(type) || isLong(type)) {
				coldefSQL.append(propName).append(" INTEGER, "); // INTEGER affinity (1)
			} else if (isFloat(type) || isDouble(type)) {
				coldefSQL.append(propName).append(" REAL, "); // REAL affinity (4)
			} else if (isChar(type) || isString(type)) {
				coldefSQL.append(propName).append(" TEXT, "); // TEXT affinity (2)
			} else if (isCalendar(type) || isDate(type)) {
				coldefSQL.append(propName).append(" DATE, "); // NUMERIC affinity (5) -- save time in milliseconds
			} else {
				throw new UnsupportedTypeException(type);
			}
			if (property.isPrimaryKey()) {
				pkSQL.append(propName).append(", ");
			}
		}
		if (pkSQL.length() == 12) { // no PK
			stripEnd(coldefSQL);
			createSQL.append(coldefSQL).append(");");
		} else {
			stripEnd(pkSQL);
			createSQL.append(coldefSQL).append(pkSQL).append("));");
		}
		// SQL drop
		StringBuilder dropSQL = new StringBuilder("DROP TABLE ").append(name).append(";");
		return new String[] { createSQL.toString(), dropSQL.toString() };
	}

	private void stripEnd(StringBuilder sb) {
		int length = sb.length();
		sb.delete(length - 2, length);
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
