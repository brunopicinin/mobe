package br.com.mobe.view.logic.process;

import android.content.Context;
import br.com.mobe.core.metadata.ClassMetadata;
import br.com.mobe.view.FormView;

public interface ViewProcessor {

	public FormView generateForm(Context context, ClassMetadata metadata);

}
