package br.com.mobe.core.processor.primitive;

import android.content.ContentValues;
import android.database.Cursor;
import br.com.mobe.core.processor.NumberProcessor;

public class IntProcessor extends NumberProcessor {

	@Override
	public void putIn(ContentValues values, String key, Object value) {
		values.put(key, (Integer) value);
	}

	@Override
	public Object getFrom(Cursor cursor, int columnIndex) {
		return cursor.getInt(columnIndex);
	}

}
