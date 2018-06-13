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

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest {

	@Autowired
	private AccountService accountService;
	
	@Test
	public void initialise() throws Exception {
		assertTrue(accountService.getAccount(123456789l) != null);
	}

	@Test
	public void checkBalanceInvalidAccount() throws Exception {
		try {
			accountService.checkBalance(123456788l, 1234);
			fail("Invalid account exception expected but not thrown");
		} catch(InvalidAccountException ex) {
			assertTrue(ex.getMessage().equals(ATMErrorMessages.INVALID_ACCOUNT_MSG.getMessage()));
		}
	}

	@Test
	public void checkBalanceInvalidPin() throws Exception {
		try {
			accountService.checkBalance(123456789l, 1233);
			fail("Invalid account exception expected but not thrown");
		} catch(InvalidAccountException ex) {
			assertTrue(ex.getMessage().equals(ATMErrorMessages.INVALID_ACCOUNT_MSG.getMessage()));
		}
	}
	
	@Test
	public void checkBalance() throws Exception {
		assertTrue(accountService.checkBalance(123456789l, 1234) != null);		
	}
}
