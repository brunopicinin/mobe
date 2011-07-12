package br.ita.mobe.pattern.metadata;

public class PropertyDescriptor {
	private String name;
	private Class<?> type;

	public PropertyDescriptor(String name, Class<?> type) {
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

}
