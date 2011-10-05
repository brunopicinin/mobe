package br.com.mobe.orm.exception;

public class DatabaseException extends Exception {

	public DatabaseException() {
		super();
	}

	public DatabaseException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public DatabaseException(String detailMessage) {
		super(detailMessage);
	}

	public DatabaseException(Throwable throwable) {
		super(throwable);
	}

}
