package utils;

public class StringUtils {

	public static boolean isEmpty(String string) {
		if (string == null) {
			return true;
		}
		String trim = string.trim();
		if (trim.length() == 0) {
			return true;
		}
		return false;
	}

}
