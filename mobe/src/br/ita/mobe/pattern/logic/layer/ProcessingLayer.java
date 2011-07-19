package br.ita.mobe.pattern.logic.layer;

import android.content.Context;
import br.ita.mobe.pattern.metadata.BeanMetadata;
import br.ita.mobe.view.FormView;

public interface ProcessingLayer {

	public FormView processLayer(Context context, BeanMetadata metadata, FormView formView);

}
