package br.com.mobe.pattern.metadata;

import java.util.ArrayList;
import java.util.List;

import br.com.mobe.pattern.logic.process.GenerateFormProcessor;
import br.com.mobe.pattern.logic.process.ViewProcessor;

public class ClassMetadata {

	private Class<?> target;
	private List<PropertyDescriptor> properties;
	private ViewProcessor processor;

	public ClassMetadata(Class<?> type) {
		this.target = type;
		properties = new ArrayList<PropertyDescriptor>();
	}

	public Class<?> getTarget() {
		return target;
	}

	public void setTarget(Class<?> type) {
		this.target = type;
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
			processor = new GenerateFormProcessor();
		}
		return processor;
	}

	public void setProcessor(ViewProcessor processor) {
		this.processor = processor;
	}

}
