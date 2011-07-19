package br.ita.mobe.pattern.logic.layer;

import java.util.List;

import android.content.Context;
import br.ita.mobe.pattern.metadata.BeanMetadata;
import br.ita.mobe.pattern.metadata.PropertyDescriptor;
import br.ita.mobe.view.FormView;
import br.ita.mobe.widget.form.FormWidget;

public class PopulateFormLayer implements ProcessingLayer {

	@Override
	public FormView processLayer(Context context, BeanMetadata metadata, FormView formView) {
		List<PropertyDescriptor> properties = metadata.getProperties();
		for (PropertyDescriptor property : properties) {
			FormWidget widget = formView.findWidget(property.getName());
			widget.populate(property.getValue());
		}
		return formView;
	}

}
