package com.alanpmullane.atm.exception;

public class InvalidAmountException extends Exception {

	private static final long serialVersionUID = 1l;

	public InvalidAmountException(String message) {
		super(message);
	}
	
	public InvalidAmountException(Throwable cause) {
		super(cause);
	}

	public InvalidAmountException(String message, Throwable cause) {
		super(message, cause);
	}
}
