package com.alanpmullane.atm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alanpmullane.atm.model.Account;
import com.alanpmullane.atm.model.Balance;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AccountService {

	private final String accountOneJsonString = "{ \"accountNumber\": 123456789, \"pin\": 1234, \"amount\": 800, \"overdraft\": 150}"; 
	private final String accountTwoJsonString = "{ \"accountNumber\": 987654321, \"pin\": 4321, \"amount\": 1250, \"overdraft\": 150}"; 
	
	private List<Account> accounts = null;
	
	public void initialiseAccounts() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Account accountOne = mapper.readValue(accountOneJsonString, Account.class);
		Account accountTwo = mapper.readValue(accountTwoJsonString, Account.class);
		accounts = new ArrayList<Account>();
		accounts.add(accountOne);
		accounts.add(accountTwo);
	}
	
	public Balance checkBalance(Long accountNumber, Integer pin) {
		Balance balance = new Balance();
		
		Account account = getAccount(accountNumber);
		
		balance.setAccountNumber(accountNumber);
		balance.setAmount(account.getAmount());
		
		return balance;
	}
	
	private Account getAccount(Long accountNumber) {
		Account account = accounts.stream()
			.filter(a -> a.getAccountNumber().longValue() == accountNumber.longValue())
			.findFirst()
			.get();
		return account;
	}
}
