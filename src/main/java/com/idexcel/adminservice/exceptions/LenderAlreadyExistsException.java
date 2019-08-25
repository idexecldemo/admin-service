package com.idexcel.adminservice.exceptions;

public class LenderAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public LenderAlreadyExistsException(String message) {
		super(message);
	}
}
