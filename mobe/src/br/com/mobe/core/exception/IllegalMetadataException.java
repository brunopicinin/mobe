package br.com.mobe.core.exception;

public class IllegalMetadataException extends RuntimeException {

	Class<?> entity;

	public IllegalMetadataException(Class<?> entity) {
		this.entity = entity;
	}

	public IllegalMetadataException(Class<?> entity, String detailMessage) {
		super(detailMessage);
		this.entity = entity;
	}

	public Class<?> getEntity() {
		return entity;
	}

}
