package br.com.mobe.core.processor.primitive;

import java.util.Calendar;

import android.content.ContentValues;
import android.database.Cursor;
import br.com.mobe.core.processor.TimeProcessor;

public class CalendarProcessor extends TimeProcessor {

	@Override
	public void putIn(ContentValues values, String key, Object value) {
		values.put(key, ((Calendar) value).getTimeInMillis());
	}

	@Override
	public Object getFrom(Cursor cursor, int columnIndex) {
		long l = cursor.getLong(columnIndex);
		Calendar calendar = null;
		if (l != 0) {
			calendar = Calendar.getInstance();
			calendar.setTimeInMillis(l);
		}
		return calendar;
	}

	@Override
	public String valueToString(Object value) {
		return String.valueOf(((Calendar) value).getTimeInMillis());
	}

}
