package br.ita.mobe.pattern;

import android.content.Context;
import br.ita.mobe.db.DatabaseAdapter;

public class PersistenceController {

	public void createTables(Context context, Class<?>... classes) {
		if (classes.length == 0) {
			throw new IllegalArgumentException("Must have at least one table to create.");
		}
		String name = "mobe.db";
		int version = 1;
		DatabaseAdapter dbAdapter = new DatabaseAdapter(context, name, version);
		dbAdapter.createTables(classes);
	}

	/**
	 * Not implemented yet.
	 */
	public void save(Object bean, Context context) {
		// ClassMetadata metadata = Repository.getInstance().getMetadata(bean);
		// open (create/update) database
		// dbAdapter = DatabaseAdapterProvider.getDbAdapter(context, "mydb", 1);
		// save bean

	}

}
