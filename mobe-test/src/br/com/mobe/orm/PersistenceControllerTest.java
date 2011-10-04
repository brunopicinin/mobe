package br.com.mobe.orm;

import pojo.full.ClassAnnotation;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.test.InstrumentationTestCase;
import br.com.mobe.core.exception.UnsupportedTypeException;

public class PersistenceControllerTest extends InstrumentationTestCase {

	public void testCreateTables() throws SQLiteException, UnsupportedTypeException {
		Context context = getInstrumentation().getContext();
		PersistenceController controller = new PersistenceController(context);
		controller.createTables(ClassAnnotation.class);

	}

	class OpenHelper extends SQLiteOpenHelper {
		public OpenHelper(Context context, String name, int version) {
			super(context, name, null, version);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		}
	}

}
