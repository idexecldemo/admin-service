package com.idexcel.adminservice.exceptions;

public class LenderNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public LenderNotFoundException(String message) {
		super(message);
	}

}
