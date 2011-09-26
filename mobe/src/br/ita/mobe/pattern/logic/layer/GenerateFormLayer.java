package br.ita.mobe.pattern.logic.layer;

import android.content.Context;
import br.ita.mobe.pattern.logic.process.ViewProcessor;
import br.ita.mobe.pattern.metadata.ClassMetadata;
import br.ita.mobe.view.FormView;

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
