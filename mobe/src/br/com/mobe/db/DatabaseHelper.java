package br.com.mobe.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private DatabaseBuilder builder;

	public DatabaseHelper(Context context, String dbName, int dbVersion) {
		super(context, dbName, null, dbVersion);
	}

	public DatabaseHelper(Context context, String dbName, int dbVersion, DatabaseBuilder builder) {
		super(context, dbName, null, dbVersion);
		this.builder = builder;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		for (String table : builder.getTables()) {
			String createSQL = builder.getSQLCreate(table);
			db.execSQL(createSQL);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		for (String table : builder.getTables()) {
			String dropSQL = builder.getSQLDrop(table);
			db.execSQL(dropSQL);
		}
		onCreate(db);
	}

}
