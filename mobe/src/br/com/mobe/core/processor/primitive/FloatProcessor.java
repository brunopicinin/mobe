package br.com.mobe.core.processor.primitive;

import android.content.ContentValues;
import android.database.Cursor;
import br.com.mobe.core.processor.DecimalProcessor;

public class FloatProcessor extends DecimalProcessor {

	@Override
	public void putIn(ContentValues values, String key, Object value) {
		values.put(key, (Float) value);
	}

	@Override
	public Object getFrom(Cursor cursor, int columnIndex) {
		return cursor.getFloat(columnIndex);
	}

}
