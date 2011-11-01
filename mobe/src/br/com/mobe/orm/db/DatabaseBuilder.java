package br.com.mobe.orm.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.com.mobe.core.exception.IllegalMetadataException;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Property;
import br.com.mobe.core.metadata.Repository;

public class DatabaseBuilder {

	private Map<String, String[]> tables; // {table name: [SQL create, SQL drop]}

	public DatabaseBuilder() {
		tables = new HashMap<String, String[]>();
	}

	public void addTable(Class<?> clazz) {
		String name = DbUtils.getTableName(clazz);
		String[] sql = generateSQLs(clazz, name);
		tables.put(name, sql);
	}

	protected String[] generateSQLs(Class<?> clazz, String name) {
		ClassMetadata metadata = Repository.getInstance().getMetadata(clazz);
		List<Property> properties = metadata.getProperties();
		if (properties.size() == 0) {
			throw new IllegalMetadataException(clazz, "Trying to create table without columns.");
		}

		// SQL create
		StringBuilder createSQL = new StringBuilder("CREATE TABLE ").append(name).append(" (");
		for (Property property : properties) {
			String propName = DbUtils.getColumnName(property.getName());
			createSQL.append(propName).append(property.getProcessor().getSQLType());
			if (property.isNotNull()) {
				createSQL.append("NOT NULL ");
			}
			if (property.isUnique()) {
				createSQL.append("UNIQUE ");
			}
			if (property.isPrimaryKey()) {
				createSQL.append("PRIMARY KEY ");
				if (property.isAutoIncrement()) {
					createSQL.append("AUTOINCREMENT ");
				}
			}
			createSQL.append(", ");
		}
		int length = createSQL.length();
		createSQL.delete(length - 2, length);
		createSQL.append(");");

		// SQL drop
		StringBuilder dropSQL = new StringBuilder("DROP TABLE ").append(name).append(";");

		return new String[] { createSQL.toString(), dropSQL.toString() };
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
