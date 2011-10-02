package br.ita.mobe;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringUtils {

	public static String formatted(Date date, String format) {
		if (format == null) {
			format = "dd/MM/yyyy HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	public static String formatted(Date date) {
		return formatted(date, null);
	}

	public static String formatted(Calendar calendar, String format) {
		return formatted(calendar.getTime(), format);
	}

	public static String formatted(Calendar calendar) {
		return formatted(calendar, null);
	}

}
