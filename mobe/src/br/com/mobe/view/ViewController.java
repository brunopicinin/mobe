package br.com.mobe.view;

import android.content.Context;
import br.com.mobe.core.exception.IllegalMetadataException;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.core.metadata.Repository;
import br.com.mobe.view.process.layer.GenerateFormLayer;
import br.com.mobe.view.process.layer.PopulateFormLayer;

public class ViewController {

	private Context context;

	public ViewController(Context context) {
		this.context = context;
	}

	/**
	 * Generate a form based on a Java Bean.
	 * 
	 * @param bean
	 *            The object that will serve as model to the form.
	 * @return The generated form.
	 * @throws IllegalMetadataException
	 */
	public FormView generateForm(Object bean) throws IllegalMetadataException {
		ClassMetadata metadata = Repository.getInstance().getMetadata(bean.getClass());
		return new GenerateFormLayer().processLayer(context, null, metadata, null);
	}

	/**
	 * Generate and populate a form based on a Java Bean.
	 * 
	 * @param bean
	 *            The object that will serve as model to the form and that will be used to populate the form with its field values.
	 * @return The generated and populated form.
	 * @throws IllegalMetadataException
	 */
	public FormView generatePopulateForm(Object bean) throws IllegalMetadataException {
		ClassMetadata metadata = Repository.getInstance().getMetadata(bean.getClass());
		FormView form = new GenerateFormLayer().processLayer(context, null, metadata, null);
		return new PopulateFormLayer().processLayer(context, form, metadata, bean);
	}

}
