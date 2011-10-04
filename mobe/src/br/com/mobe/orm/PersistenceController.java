package br.com.mobe.orm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import br.com.mobe.core.exception.UnsupportedTypeException;
import br.com.mobe.orm.db.DatabaseAdapter;

public class PersistenceController {

	private Context context;

	// temp
	public static final String name = "mobe.db";
	public static final int version = 1;

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

	public long save(Object bean) throws UnsupportedTypeException {
		// TODO Test controllers method arguments. Ex: createTables
		DatabaseAdapter dbAdapter = new DatabaseAdapter(context, name, version);
		dbAdapter.open();
		return dbAdapter.save(bean);
	}

	public <E> List<E> list(Class<E> clazz) {
		DatabaseAdapter dbAdapter = new DatabaseAdapter(context, name, version);
		dbAdapter.open();
		return dbAdapter.list(clazz);
	}

}
