package br.com.tarefas.mobe;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.com.mobe.core.processor.Processor;
import br.com.mobe.view.widget.FormWidget;

public class ListProcessor extends Processor {

	@Override
	public FormWidget createWidget(Context context, String name) {
		return new ListWidget(context, name, capitalize(name));
	}

	@Override
	public String getSQLType() {
		return " TEXT ";
	}

	@Override
	public void putIn(ContentValues values, String key, Object value) {
		List list = (List) value;
		for (Object item : list) {
			values.put(key, item.toString());
		}
	}

	@Override
	public Object getFrom(Cursor cursor, int columnIndex) {
		List list = new ArrayList();
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			String item = cursor.getString(columnIndex);
			list.add(item);
			cursor.moveToNext();
		}
		return list;
	}
}

class ListWidget extends FormWidget {

	public ListWidget(Context context, String name, String displayName) {
		super(context, name, displayName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void populateWg(Object value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getValue(Class<?> type) {
		// TODO Auto-generated method stub
		return null;
	}

}
