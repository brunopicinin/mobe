package br.ita.mobe.pattern;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import br.ita.mobe.pattern.logic.layer.GenerateFormLayer;
import br.ita.mobe.pattern.logic.layer.ProcessingLayer;
import br.ita.mobe.pattern.metadata.BeanMetadata;
import br.ita.mobe.pattern.metadata.Repository;
import br.ita.mobe.view.FormView;

public class MobeController {

	private List<ProcessingLayer> layers;

	public MobeController(ProcessingLayer... layers) {
		this.layers = new ArrayList<ProcessingLayer>();
		for (ProcessingLayer layer : layers) {
			this.layers.add(layer);
		}
	}

	public MobeController() {
		this(new GenerateFormLayer());
	}

	public FormView generateForm(Context context, Object bean) {
		BeanMetadata metadata = Repository.getInstance().getMetadata(bean.getClass());
		FormView form = null;
		for (ProcessingLayer layer : layers) {
			form = layer.processLayer(context, metadata, form);
		}
		// ViewProcessor processor = metadata.getProcessor();
		// return processor.generateForm(context, metadata);
		return form;
	}

}
