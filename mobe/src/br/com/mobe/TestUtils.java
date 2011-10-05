package br.com.mobe;

public class TestUtils {

	public static long generateRandomPk() {
		return (long) Math.floor(Math.random() * Long.MAX_VALUE);
	}

}
