package br.com.mobe.pattern.logic.layer;

import android.content.Context;
import br.com.mobe.pattern.metadata.ClassMetadata;
import br.com.mobe.view.FormView;

public interface FormProcessingLayer {

	public FormView processLayer(Context context, FormView formView, ClassMetadata metadata, Object bean);

}
