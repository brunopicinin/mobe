package br.com.mobe.core.processor.primitive;

import java.util.Date;

import android.content.ContentValues;
import android.database.Cursor;
import br.com.mobe.core.processor.TimeProcessor;

public class DateProcessor extends TimeProcessor {

	@Override
	public void putIn(ContentValues values, String key, Object value) {
		values.put(key, ((Date) value).getTime());
	}

	@Override
	public Object getFrom(Cursor cursor, int columnIndex) {
		long l = cursor.getLong(columnIndex);
		Date date = null;
		if (l != 0) {
			date = new Date(l);
		}
		return date;
	}

	@Override
	public String valueToString(Object value) {
		return String.valueOf(((Date) value).getTime());
	}

}
