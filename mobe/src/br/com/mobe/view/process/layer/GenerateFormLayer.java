package br.com.mobe.view.process.layer;

import java.util.List;

import android.content.Context;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Property;
import br.com.mobe.core.processor.Processor;
import br.com.mobe.view.FormView;
import br.com.mobe.view.widget.FormWidget;

public class GenerateFormLayer implements FormProcessingLayer {

	@Override
	public FormView processLayer(Context context, FormView formView, ClassMetadata metadata, Object bean) {
		FormView form = new FormView(context, metadata.getTarget());
		List<Property> properties = metadata.getProperties();
		for (Property property : properties) {
			if (!property.isHidden()) {
				Processor processor = property.getProcessor();
				FormWidget widget = processor.createWidget(context, property.getName());
				form.addView(widget);
			}
		}
		return form;
	}

}
