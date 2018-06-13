package com.alanpmullane.atm.exception;

public enum ATMErrorMessages {

	INVALID_ACCOUNT_MSG("Invalid Account number or pin entered."),
	INVALID_AMOUNT_MSG("Invalid amount provided. Amount must be a positive non-zero value and divisible by €5, €10, €20 or €50"), 
	INSUFFICIENT_FUNDS_MSG("Insufficient funds available.");
	
	private String message;
	
	ATMErrorMessages(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
