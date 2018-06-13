package com.alanpmullane.atm.validator;

import org.springframework.stereotype.Component;

import com.alanpmullane.atm.exception.ATMErrorMessages;
import com.alanpmullane.atm.exception.InvalidAccountException;
import com.alanpmullane.atm.model.Account;

@Component
public class AccountValidator {

	public void validate(Account account, Integer pin) throws InvalidAccountException {
		if (account == null) {
			throw new InvalidAccountException(ATMErrorMessages.INVALID_ACCOUNT_MSG.getMessage());
		}
		if (!pin.equals(account.getPin())) {
			throw new InvalidAccountException(ATMErrorMessages.INVALID_ACCOUNT_MSG.getMessage());			
		}
	}
}
