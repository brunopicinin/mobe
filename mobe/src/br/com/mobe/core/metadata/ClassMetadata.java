package br.com.mobe.core.metadata;

import java.util.ArrayList;
import java.util.List;

import br.com.mobe.core.exception.IllegalMetadataException;
import br.com.mobe.view.logic.process.GenerateFormProcessor;
import br.com.mobe.view.logic.process.ViewProcessor;

public class ClassMetadata {

	private Class<?> target;
	private List<Property> properties;
	private Property primaryKey;

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

	public Property getPrimaryKey() {
		return primaryKey;
	}

	public void addProperty(Property property) {
		if (property.isPrimaryKey()) {
			if (primaryKey != null) {
				throw new IllegalMetadataException(target, "Class has more than one id.");
			} else {
				primaryKey = property;
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

}
