package br.com.mobe.core.metadata;

import java.util.ArrayList;
import java.util.List;

import br.com.mobe.core.exception.IllegalMetadataException;
import br.com.mobe.view.logic.process.GenerateFormProcessor;
import br.com.mobe.view.logic.process.ViewProcessor;

public class ClassMetadata {

	private Class<?> target;
	private List<Property> properties;
	private boolean hasPrimaryKey = false;

	// TODO errado!
	private ViewProcessor processor;

	public ClassMetadata(Class<?> target) {
		this.target = target;
		properties = new ArrayList<Property>();
	}

	public Class<?> getTarget() {
		return target;
	}

	public List<Property> getProperties() {
		return properties;
	}

	public void addProperty(Property property) throws IllegalMetadataException {
		if (property.isPrimaryKey()) {
			if (hasPrimaryKey) {
				throw new IllegalMetadataException(target, "Class has more than one id: " + target.getName());
			} else {
				hasPrimaryKey = true;
			}
		}
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

	public boolean hasPrimaryKey() {
		for (Property property : properties) {
			if (property.isPrimaryKey()) {
				return true;
			}
		}
		return false;
	}

}
