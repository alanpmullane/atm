package com.alanpmullane.atm.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alanpmullane.atm.model.ATMStore;
import com.alanpmullane.atm.model.Balance;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ATMService {
	
	@Autowired
	private AccountService accountService;

	// TODO: create ATMStore JPA repository and populate with this data
	private final String atmJsonString = "{ \"amount\": 2000, \"denominations\": [ { \"type\": \"Fifty\", \"count\": 20 }, { \"type\": \"Twenty\", \"count\": 30 }, { \"type\": \"Ten\", \"count\": 30 }, { \"type\": \"Five\", \"count\": 20 } ]}";

	private ATMStore atmStore = null;

	@PostConstruct
	public void initialiseATM() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		atmStore = mapper.readValue(atmJsonString, ATMStore.class);
		
		// setup accounts
		accountService.initialiseAccounts();
	}
	
	public Balance checkBalance(Long accountNumber, Integer pin) {
		return accountService.checkBalance(accountNumber, pin);
	}
	
	public ATMStore getATMStore() {
		return this.atmStore;
	}
}
