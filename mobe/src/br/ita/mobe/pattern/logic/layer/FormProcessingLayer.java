package br.ita.mobe.pattern.logic.layer;

import android.content.Context;
import br.ita.mobe.pattern.metadata.ClassMetadata;
import br.ita.mobe.view.FormView;

public interface FormProcessingLayer {

	public FormView processLayer(Context context, FormView formView, ClassMetadata metadata, Object bean);

}
