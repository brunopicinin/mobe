package br.ita.mobe.exception;

public class UnsupportedTypeException extends Exception {

	private Class<?> type;

	public UnsupportedTypeException() {
		super();
	}

	public UnsupportedTypeException(String message) {
		super(message);
	}

	public UnsupportedTypeException(Class<?> type) {
		this.type = type;
	}

	public Class<?> getType() {
		return type;
	}

}
