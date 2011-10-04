package br.com.mobe.core.metadata;

public class Property {

	private String name;
	private Class<?> type;
	private boolean isPrimaryKey = false;

	public Property(String name, Class<?> type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public Class<?> getType() {
		return type;
	}

	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

}
