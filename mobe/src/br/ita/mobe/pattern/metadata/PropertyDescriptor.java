package br.ita.mobe.pattern.metadata;

public class PropertyDescriptor {

	private String name;
	private Class<?> type;
	private Object value;

	public PropertyDescriptor(String name, Class<?> type, Object value) {
		this.name = name;
		this.type = type;
		this.value = value;
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

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
