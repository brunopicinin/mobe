package br.ita.mobe.pattern.metadata;

import java.util.ArrayList;
import java.util.List;

public class BeanMetadata {

	private List<PropertyDescriptor> properties;

	public BeanMetadata() {
		properties = new ArrayList<PropertyDescriptor>();
	}

	public void setProperties(List<PropertyDescriptor> properties) {
		this.properties = properties;
	}

	public List<PropertyDescriptor> getProperties() {
		return properties;
	}

	public void addProperty(PropertyDescriptor propertyDescriptor) {
		properties.add(propertyDescriptor);
	}

}
