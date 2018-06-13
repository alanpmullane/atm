package com.alanpmullane.atm.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alanpmullane.atm.exception.InvalidAccountException;
import com.alanpmullane.atm.model.Account;
import com.alanpmullane.atm.model.Balance;
import com.alanpmullane.atm.validator.AccountValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AccountService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final String accountOneJsonString = "{ \"accountNumber\": 123456789, \"pin\": 1234, \"amount\": 800, \"overdraft\": 200}"; 
	private final String accountTwoJsonString = "{ \"accountNumber\": 987654321, \"pin\": 4321, \"amount\": 1250, \"overdraft\": 150}"; 
	
    private final Map<Long, Account> accounts = new HashMap<>();

    @Autowired
    private AccountValidator accountValidator;
    
	public void initialiseAccounts() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Account accountOne = mapper.readValue(accountOneJsonString, Account.class);
		Account accountTwo = mapper.readValue(accountTwoJsonString, Account.class);
		accounts.put(accountOne.getAccountNumber(), accountOne);
		accounts.put(accountTwo.getAccountNumber(), accountTwo);
		
		logger.debug("initialiseAccounts: \n accountOne: " + accountOne.toString());
		logger.debug("initialiseAccounts: \n accountTwo: " + accountTwo.toString());
	}
	
	public Balance checkBalance(Long accountNumber, Integer pin) throws Exception {
		Balance balance = new Balance();
		
		// TODO: query JPA repository for accounts instead of account map
		Account account = getAccount(accountNumber);
		
		accountValidator.validate(account, pin);
		
		balance.setAccountNumber(accountNumber);
		balance.setAmount(account.getAmount());
		
		return balance;
	}
	
	public Account getAccount(Long accountNumber) throws InvalidAccountException {
		// TODO: query JPA repository for accounts instead of account map
		return accounts.get(accountNumber);
	}
	
	public void updateAccount(Long accountNumber, Integer amount) {
		// TODO: query JPA repository for accounts instead of account map
		accounts.get(accountNumber).setAmount(amount);
	}
}
