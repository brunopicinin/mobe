package br.ita.mobe.pattern;

import android.content.Context;
import br.ita.mobe.pattern.metadata.BeanMetadata;
import br.ita.mobe.pattern.metadata.Repository;

public class PersistenceController {

	public void save(Object bean, Context context) {
		BeanMetadata metadata = Repository.getInstance().getMetadata(bean);

		// open (create/update) database

		// save bean

	}
}
