package br.ita.mobe.db;

import java.util.Set;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import br.ita.mobe.exception.UnsupportedTypeException;

public class DatabaseAdapter {

	// private static Map<String, DatabaseBuilder> builders;

	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;

	private final Context context;
	private String dbName;
	private int dbVersion;

	public DatabaseAdapter(Context context, String dbName, int dbVersion) {
		this.context = context;
		this.dbName = dbName;
		this.dbVersion = dbVersion;
	}

	public void createTables(Set<Class<?>> classes) throws SQLiteException, UnsupportedTypeException {
		DatabaseBuilder builder = new DatabaseBuilder();
		for (Class<?> clazz : classes) {
			builder.addTable(clazz);
		}
		dbHelper = new DatabaseHelper(context, dbName, dbVersion);
		dbHelper.setBuilder(builder);
		database = dbHelper.getWritableDatabase();
	}

}
