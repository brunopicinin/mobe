package br.com.mobe.view;

import android.content.Context;
import br.com.mobe.core.exception.IllegalMetadataException;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Repository;
import br.com.mobe.view.logic.layer.GenerateFormLayer;
import br.com.mobe.view.logic.layer.PopulateFormLayer;

public class ViewController {

	private Context context;

	public ViewController(Context context) {
		this.context = context;
	}

	public FormView generateForm(Object bean) throws IllegalMetadataException {
		ClassMetadata metadata = Repository.getInstance().getMetadata(bean.getClass());
		return new GenerateFormLayer().processLayer(context, null, metadata, null);
	}

	public FormView generatePopulateForm(Object bean) throws IllegalMetadataException {
		ClassMetadata metadata = Repository.getInstance().getMetadata(bean.getClass());
		FormView form = new GenerateFormLayer().processLayer(context, null, metadata, null);
		return new PopulateFormLayer().processLayer(context, form, metadata, bean);
	}

}
