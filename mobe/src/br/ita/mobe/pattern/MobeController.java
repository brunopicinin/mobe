package br.ita.mobe.pattern;

import android.content.Context;
import br.ita.mobe.pattern.logic.ViewProcessor;
import br.ita.mobe.pattern.metadata.BeanMetadata;
import br.ita.mobe.pattern.metadata.Repository;
import br.ita.mobe.view.FormView;

public class MobeController {

	public FormView generateForm(Context context, Object bean) {
		BeanMetadata metadata = Repository.getInstance().getMetadata(bean.getClass());
		ViewProcessor processor = metadata.getProcessor();
		return processor.generateForm(context, metadata);
	}

}
