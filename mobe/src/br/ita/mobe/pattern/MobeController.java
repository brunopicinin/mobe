package br.ita.mobe.pattern;

import android.content.Context;
import android.view.View;
import br.ita.mobe.pattern.logic.ViewProcessor;
import br.ita.mobe.pattern.metadata.BeanMetadata;
import br.ita.mobe.pattern.metadata.Repository;

public class MobeController {

	public View generateForm(Object bean, Context context) {
		BeanMetadata metadata = Repository.getInstance().getMetadata(bean.getClass());
		ViewProcessor processor = metadata.getProcessor();
		return processor.generateForm(context);
	}

}
