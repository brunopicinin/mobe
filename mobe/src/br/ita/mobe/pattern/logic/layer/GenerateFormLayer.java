package br.ita.mobe.pattern.logic.layer;

import android.content.Context;
import br.ita.mobe.pattern.logic.ViewProcessor;
import br.ita.mobe.pattern.metadata.BeanMetadata;
import br.ita.mobe.view.FormView;

public class GenerateFormLayer implements ProcessingLayer {

	@Override
	public FormView processLayer(Context context, BeanMetadata metadata, FormView formView) {
		ViewProcessor processor = metadata.getProcessor();
		FormView form = processor.generateForm(context, metadata);
		return form;
	}

}
