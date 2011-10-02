package br.com.mobe.core.metadata;

import java.util.ArrayList;
import java.util.List;

import br.com.mobe.view.logic.process.GenerateFormProcessor;
import br.com.mobe.view.logic.process.ViewProcessor;

public class ClassMetadata {

	private Class<?> target;
	private List<Property> properties;
	private ViewProcessor processor;

	public ClassMetadata(Class<?> type) {
		this.target = type;
		properties = new ArrayList<Property>();
	}

	public Class<?> getTarget() {
		return target;
	}

	public void setTarget(Class<?> type) {
		this.target = type;
	}

	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void addProperty(Property property) {
		properties.add(property);
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
