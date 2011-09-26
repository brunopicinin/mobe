package br.ita.mobe.pattern;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import br.ita.mobe.db.DatabaseAdapter;
import br.ita.mobe.exception.UnsupportedTypeException;

public class PersistenceController {

	public void createTables(Context context, Class<?>... classes) throws SQLiteException, UnsupportedTypeException {
		if (classes.length == 0) {
			throw new IllegalArgumentException("Must have at least one table to create.");
		}
		Set<Class<?>> classesSet = new HashSet<Class<?>>();
		for (Class<?> clazz : classes) {
			// avoid repeated classes
			classesSet.add(clazz);
		}
		String name = "mobe.db";
		int version = 1;
		DatabaseAdapter dbAdapter = new DatabaseAdapter(context, name, version);
		dbAdapter.createTables(classesSet);
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
