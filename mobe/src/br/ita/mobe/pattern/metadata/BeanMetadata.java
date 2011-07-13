package br.ita.mobe.pattern.metadata;

import java.util.ArrayList;
import java.util.List;

import br.ita.mobe.pattern.logic.LogicProcessor;
import br.ita.mobe.pattern.logic.ViewProcessor;

public class BeanMetadata {

	private List<PropertyDescriptor> properties;
	private LogicProcessor processor;

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

	public LogicProcessor getProcessor() {
		if (processor == null) {
			// default logic processor
			processor = new ViewProcessor();
		}
		return processor;
	}

	public void setProcessor(LogicProcessor processor) {
		this.processor = processor;
	}

}
