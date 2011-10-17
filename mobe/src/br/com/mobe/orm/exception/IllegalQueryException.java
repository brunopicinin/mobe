package br.com.mobe.orm.exception;

public class IllegalQueryException extends RuntimeException {

	public IllegalQueryException() {
		super();
	}

	public IllegalQueryException(String detailMessage) {
		super(detailMessage);
	}

}
