package br.ita.mobe.pattern.metadata;

import java.util.ArrayList;
import java.util.List;

import br.ita.mobe.pattern.logic.process.ConcreteViewProcessor;
import br.ita.mobe.pattern.logic.process.ViewProcessor;

public class BeanMetadata {

	private Class<?> type;
	private List<PropertyDescriptor> properties;
	private ViewProcessor processor;

	public BeanMetadata(Class<?> type) {
		this.type = type;
		properties = new ArrayList<PropertyDescriptor>();
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
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

	public ViewProcessor getProcessor() {
		if (processor == null) {
			// default logic processor
			processor = new ConcreteViewProcessor();
		}
		return processor;
	}

	public void setProcessor(ViewProcessor processor) {
		this.processor = processor;
	}

}
