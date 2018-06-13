package com.alanpmullane.atm.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alanpmullane.atm.exception.ATMErrorMessages;
import com.alanpmullane.atm.exception.InvalidAccountException;
import com.alanpmullane.atm.exception.InvalidAmountException;
import com.alanpmullane.atm.model.Withdrawal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ATMServiceTest {

	@Autowired
	private ATMService atmService;
	
	@Test
	public void initialise() throws Exception {
		assertTrue(atmService.getATMStore() != null);
	}

	@Test
	public void withdrawInvalidAccount() throws Exception {
		try {
			atmService.makeWithdrawal(123456788l, 1234, 5);
			fail("Invalid account exception expected but not thrown");
		} catch(InvalidAccountException ex) {
			assertTrue(ex.getMessage().equals(ATMErrorMessages.INVALID_ACCOUNT_MSG.getMessage()));
		}
	}

	@Test
	public void withdrawInvalidPin() throws Exception {
		try {
			atmService.makeWithdrawal(123456789l, 1233, 5);
			fail("Invalid account exception expected but not thrown");
		} catch(InvalidAccountException ex) {
			assertTrue(ex.getMessage().equals(ATMErrorMessages.INVALID_ACCOUNT_MSG.getMessage()));
		}
	}

	@Test
	public void withdrawalMultipleAmounts() throws Exception {
		// { "accountNumber": 123456789, "pin": 1234, "amount": 800, "overdraft": 200}
		Withdrawal withdrawal = atmService.makeWithdrawal(123456789l, 1234, 10);
		assertTrue(withdrawal.getAmount().equals(10)); 
		assertTrue(withdrawal.getBalance().equals(790)); 

		withdrawal = atmService.makeWithdrawal(123456789l, 1234, 215);
		assertTrue(withdrawal.getAmount().equals(215));
		assertTrue(withdrawal.getBalance().equals(575)); 		
	}
	
	@Test
	public void withdrawZero() throws Exception {
		try {
			atmService.makeWithdrawal(123456789l, 1234, 0);
			fail("Invalid amount exception expected but not thrown");
		} catch(InvalidAmountException ex) {
			assertTrue(ex.getMessage().equals(ATMErrorMessages.INVALID_AMOUNT_MSG.getMessage()));
		}
	}

	@Test
	public void withdrawNegative() throws Exception {
		try {
			atmService.makeWithdrawal(123456789l, 1234, -5);
			fail("Invalid amount exception expected but not thrown");
		} catch(InvalidAmountException ex) {
			assertTrue(ex.getMessage().equals(ATMErrorMessages.INVALID_AMOUNT_MSG.getMessage()));
		}
	}	
}
