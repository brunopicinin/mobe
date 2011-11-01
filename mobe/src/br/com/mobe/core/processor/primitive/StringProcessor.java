package br.com.mobe.core.processor.primitive;

import android.database.Cursor;
import br.com.mobe.core.processor.TextProcessor;

public class StringProcessor extends TextProcessor {

	@Override
	public Object getFrom(Cursor cursor, int columnIndex) {
		return cursor.getString(columnIndex);
	}

}
