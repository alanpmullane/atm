package com.alanpmullane.atm.exception;

public class InvalidAccountException extends Exception {
	
	private static final long serialVersionUID = 1l;

	public InvalidAccountException(String message) {
		super(message);
	}
	
	public InvalidAccountException(Throwable cause) {
		super(cause);
	}

	public InvalidAccountException(String message, Throwable cause) {
		super(message, cause);
	}
}
