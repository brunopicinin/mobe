package br.com.mobe.core.metadata;

import br.com.mobe.core.processor.Processor;

public class Property {

	private Processor processor;

	// Core
	private String name;
	private Class<?> type;

	// View
	private boolean isHidden;

	// ORM
	private boolean isPrimaryKey = false;
	private boolean autoIncrement;

	private boolean notNull = false;
	private boolean unique = false;

	public Property(String name, Class<?> type, Processor processor) {
		this.name = name;
		this.type = type;
		this.processor = processor;
	}

	public Processor getProcessor() {
		return processor;
	}

	public String getName() {
		return name;
	}

	public Class<?> getType() {
		return type;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public boolean isPrimaryKey() {
		return isPrimaryKey;
	}

	public void setPrimaryKey(boolean isPrimaryKey) {
		this.isPrimaryKey = isPrimaryKey;
	}

	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

}
