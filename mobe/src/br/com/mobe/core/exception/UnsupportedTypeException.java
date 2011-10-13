package br.com.mobe.core.exception;

public class UnsupportedTypeException extends RuntimeException {

	private Class<?> type;

	public UnsupportedTypeException(Class<?> type) {
		this.type = type;
	}

	public UnsupportedTypeException(Class<?> type, String detailMessage) {
		super(detailMessage);
		this.type = type;
	}

	public Class<?> getType() {
		return type;
	}

}
