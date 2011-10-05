package br.com.mobe;

public class PkGenerator {

	/**
	 * @return Random long value between 0 (inclusive) and Long MAX_VALUE (exclusive).
	 */
	public static long randomLong() {
		return (long) Math.floor(Math.random() * Long.MAX_VALUE);
	}

	/**
	 * @return Random int value between 0 (inclusive) and Integer MAX_VALUE (exclusive).
	 */
	public static int randomInt() {
		return (int) Math.floor(Math.random() * Integer.MAX_VALUE);
	}

	/**
	 * @return Random String with fixed length of 64, containing characters [a-z] [A-Z] [0-9].
	 */
	public static String randomString() {
		String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		int len = chars.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			int idx = (int) Math.floor(Math.random() * len);
			sb.append(chars.charAt(idx));
		}
		return sb.toString();
	}

}
