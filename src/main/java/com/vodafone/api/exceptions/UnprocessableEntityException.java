package com.vodafone.api.exceptions;

public class UnprocessableEntityException extends RuntimeException {

	private static final long serialVersionUID = 8984604329298885586L;

	public UnprocessableEntityException(String errorMessage) {
		super(errorMessage);
	}
}
