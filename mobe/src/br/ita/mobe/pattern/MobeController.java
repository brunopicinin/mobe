package br.ita.mobe.pattern;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import br.ita.mobe.pattern.logic.layer.FormProcessingLayer;
import br.ita.mobe.pattern.logic.layer.GenerateFormLayer;
import br.ita.mobe.pattern.logic.layer.PopulateFormLayer;
import br.ita.mobe.pattern.metadata.ClassMetadata;
import br.ita.mobe.pattern.metadata.Repository;
import br.ita.mobe.view.FormView;

public class MobeController {

	private List<FormProcessingLayer> layers;

	public MobeController(FormProcessingLayer... layers) {
		this.layers = new ArrayList<FormProcessingLayer>();
		for (FormProcessingLayer layer : layers) {
			this.layers.add(layer);
		}
	}

	public MobeController() {
		this(new GenerateFormLayer(), new PopulateFormLayer());
	}

	public FormView generateForm(Context context, Object bean) {
		ClassMetadata metadata = Repository.getInstance().getMetadata(bean.getClass());
		FormView form = null;
		for (FormProcessingLayer layer : layers) {
			form = layer.processLayer(context, form, metadata, bean);
		}
		return form;
	}

}
