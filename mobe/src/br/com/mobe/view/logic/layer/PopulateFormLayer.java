package br.com.mobe.view.logic.layer;

import java.util.List;

import android.content.Context;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Property;
import br.com.mobe.view.FormView;
import br.com.mobe.view.logic.process.ViewProcessor;
import br.com.mobe.view.widget.form.FormWidget;

public class PopulateFormLayer implements FormProcessingLayer {

	@Override
	public FormView processLayer(Context context, FormView formView, ClassMetadata metadata, Object bean) {
		List<Property> properties = metadata.getProperties();
		for (Property property : properties) {
			ViewProcessor processor = property.getViewProcessor();
			String name = property.getName();
			FormWidget widget = formView.findWidget(name);
			processor.populateWidget(widget, name, bean);
		}
		return formView;
	}
}
