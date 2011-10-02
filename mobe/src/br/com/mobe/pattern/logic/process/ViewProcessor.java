package br.com.mobe.pattern.logic.process;

import android.content.Context;
import br.com.mobe.pattern.metadata.ClassMetadata;
import br.com.mobe.view.FormView;

public interface ViewProcessor {

	public FormView generateForm(Context context, ClassMetadata metadata);

}
