package br.ita.mobe.pattern;

import java.util.HashSet;
import java.util.Set;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import br.ita.mobe.db.DatabaseAdapter;
import br.ita.mobe.exception.UnsupportedTypeException;

public class PersistenceController {

	private Context context;

	// temp
	private String name = "mobe.db";
	private int version = 1;

	public PersistenceController(Context context) {
		this.context = context;
	}

	public void createTables(Class<?>... classes) throws SQLiteException, UnsupportedTypeException {
		if (classes.length == 0) {
			throw new IllegalArgumentException("Must have at least one table to create.");
		}
		Set<Class<?>> classesSet = new HashSet<Class<?>>();
		for (Class<?> clazz : classes) {
			classesSet.add(clazz); // avoid repeated classes
		}
		DatabaseAdapter dbAdapter = new DatabaseAdapter(context, name, version);
		dbAdapter.createTables(classesSet);
	}

	public void save(Object bean) throws UnsupportedTypeException {
		// TODO Test controllers method arguments. Ex: createTables
		DatabaseAdapter dbAdapter = new DatabaseAdapter(context, name, version);
		dbAdapter.open();
		dbAdapter.save(bean);
	}

}
