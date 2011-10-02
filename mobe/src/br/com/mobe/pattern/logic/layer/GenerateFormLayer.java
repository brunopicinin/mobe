package br.com.mobe.pattern.logic.layer;

import android.content.Context;
import br.com.mobe.pattern.logic.process.ViewProcessor;
import br.com.mobe.pattern.metadata.ClassMetadata;
import br.com.mobe.view.FormView;

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
