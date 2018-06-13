package com.alanpmullane.atm.validator;

import org.springframework.stereotype.Component;

import com.alanpmullane.atm.exception.ATMErrorMessages;
import com.alanpmullane.atm.exception.InsufficientFundsException;
import com.alanpmullane.atm.exception.InvalidAmountException;
import com.alanpmullane.atm.model.Account;
import com.alanpmullane.atm.model.Denomination;

@Component
public class ATMValidator {

	public void validate(Integer amount, Account account, Integer atmAmount) 
			throws InsufficientFundsException, InvalidAmountException {

		boolean isValidDenomination = false;
		if (amount.intValue() > 0) { 
			for(Denomination denomination : Denomination.values()) {
				if (amount.intValue() % denomination.getValue().intValue() == 0) {
					isValidDenomination = true;
					break;
				}
			}
		}
		if (!isValidDenomination) {
			throw new InvalidAmountException(ATMErrorMessages.INVALID_AMOUNT_MSG.getMessage());
		}

		
		if (amount > atmAmount) {
			throw new InsufficientFundsException(ATMErrorMessages.INSUFFICIENT_FUNDS_MSG.getMessage());
		}
		if (amount > account.getAmount() + account.getOverdraft()) { 
			throw new InsufficientFundsException(ATMErrorMessages.INSUFFICIENT_FUNDS_MSG.getMessage());			
		}
	}
}
