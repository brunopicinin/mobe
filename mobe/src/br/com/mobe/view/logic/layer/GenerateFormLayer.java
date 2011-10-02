package br.com.mobe.view.logic.layer;

import android.content.Context;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.view.FormView;
import br.com.mobe.view.logic.process.ViewProcessor;

public class GenerateFormLayer implements FormProcessingLayer {

	@Override
	public FormView processLayer(Context context, FormView formView, ClassMetadata metadata, Object bean) {
		return processLayer(context, metadata);
	}

	public FormView processLayer(Context context, ClassMetadata metadata) {
		ViewProcessor processor = metadata.getProcessor();
		FormView form = processor.generateForm(context, metadata);
		return form;
	}

}
