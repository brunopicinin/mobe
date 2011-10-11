package br.com.mobe.orm.exception;

public class DatabaseException extends Exception {

	// TODO substituir por exceções mais específicas

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
