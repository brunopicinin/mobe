package br.ita.mobe.pattern.logic;

import android.content.Context;
import br.ita.mobe.pattern.metadata.BeanMetadata;
import br.ita.mobe.view.FormView;

public interface ViewProcessor {

	public FormView generateForm(Context context, BeanMetadata metadata);

}
