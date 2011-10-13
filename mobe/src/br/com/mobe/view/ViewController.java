package br.com.mobe.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import br.com.mobe.core.exception.IllegalMetadataException;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Repository;
import br.com.mobe.view.logic.layer.FormProcessingLayer;
import br.com.mobe.view.logic.layer.GenerateFormLayer;
import br.com.mobe.view.logic.layer.PopulateFormLayer;

public class ViewController {

	private Context context;
	private List<FormProcessingLayer> layers;

	public ViewController(Context context, FormProcessingLayer... layers) {
		this.context = context;
		this.layers = new ArrayList<FormProcessingLayer>();
		for (FormProcessingLayer layer : layers) {
			this.layers.add(layer);
		}
	}

	public ViewController(Context context) {
		this(context, new GenerateFormLayer(), new PopulateFormLayer());
	}

	public FormView generateForm(Object bean) throws IllegalMetadataException {
		ClassMetadata metadata = Repository.getInstance().getMetadata(bean.getClass());
		FormView form = null;
		for (FormProcessingLayer layer : layers) {
			form = layer.processLayer(context, form, metadata, bean);
		}
		return form;
	}

}
