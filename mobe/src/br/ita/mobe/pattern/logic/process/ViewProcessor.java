package br.ita.mobe.pattern.logic.process;

import android.content.Context;
import br.ita.mobe.pattern.metadata.ClassMetadata;
import br.ita.mobe.view.FormView;

public interface ViewProcessor {

	public FormView generateForm(Context context, ClassMetadata metadata);

}
