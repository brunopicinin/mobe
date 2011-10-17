package br.com.mobe.orm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import br.com.mobe.orm.db.DatabaseAdapter;

public class PersistenceController {

	private Context context;

	// temp
	public static final String name = "mobe.db";
	public static final int version = 1;

	public PersistenceController(Context context) {
		this.context = context;
	}

	/**
	 * Create tables based on annotated classes.
	 * 
	 * @param classes
	 *            The models of the application.
	 */
	public void createTables(Class<?>... classes) {
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

	/**
	 * Save a new object to the database.
	 * 
	 * @param object
	 *            The object to be saved.
	 * @return The rowId of the saved object.
	 */
	public long save(Object object) {
		// TODO Test controllers method arguments. Ex: createTables
		DatabaseAdapter dbAdapter = new DatabaseAdapter(context, name, version);
		dbAdapter.open();
		long rowid = dbAdapter.save(object);
		dbAdapter.close();
		if (rowid < 0) {
			throw new SQLiteException("Object save error.");
		}
		return rowid;
	}

	/**
	 * List all objects of a given class saved in the database.
	 * 
	 * @param clazz
	 *            The class to consult.
	 * @return The list of persistent objects.
	 */
	public <E> List<E> list(Class<E> clazz) {
		DatabaseAdapter dbAdapter = new DatabaseAdapter(context, name, version);
		dbAdapter.open();
		List<E> list = dbAdapter.list(clazz);
		dbAdapter.close();
		return list;
	}

	/**
	 * Remove an object from the database based on its primary key.
	 * 
	 * @param object
	 *            The object to be removed.
	 * @return True if the operation was successful. False otherwise.
	 */
	public boolean delete(Object object) {
		DatabaseAdapter dbAdapter = new DatabaseAdapter(context, name, version);
		dbAdapter.open();
		boolean delete = dbAdapter.delete(object);
		dbAdapter.close();
		return delete;
	}

	/**
	 * Update the values of a given object in the database. The primary key values must never be altered, otherwise the object won't be found.
	 * 
	 * @param object
	 *            The object to be updated.
	 * @return True if the operation was successful. False otherwise.
	 */
	public boolean update(Object object) {
		DatabaseAdapter dbAdapter = new DatabaseAdapter(context, name, version);
		dbAdapter.open();
		boolean update = dbAdapter.update(object);
		dbAdapter.close();
		return update;
	}

}
