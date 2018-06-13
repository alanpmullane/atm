package com.alanpmullane.atm.service;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alanpmullane.atm.model.ATMStore;
import com.alanpmullane.atm.model.Account;
import com.alanpmullane.atm.model.Balance;
import com.alanpmullane.atm.model.BankNotes;
import com.alanpmullane.atm.model.Denomination;
import com.alanpmullane.atm.model.Withdrawal;
import com.alanpmullane.atm.util.ATMUtil;
import com.alanpmullane.atm.validator.ATMValidator;
import com.alanpmullane.atm.validator.AccountValidator;

@Service
public class ATMService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ATMValidator atmValidator;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AccountValidator accountValidator;

	private ATMStore atmStore = null;

	@PostConstruct
	public void initialiseATM() throws Exception {
		atmStore = new ATMStore();
		BankNotes bankNotes = new BankNotes(ATMUtil.buildDenominationMap(
				new Integer[] {100, 300, 600, 1000})); // 20x€5, 30x€10, 30x€20, 20x€50
		atmStore.setBankNotes(bankNotes);
		
		logger.debug("initialiseATM: \n atmStore: " + atmStore.toString());
		
		// setup accounts
		accountService.initialiseAccounts();
	}
	
	public Balance checkBalance(Long accountNumber, Integer pin) throws Exception {
		return accountService.checkBalance(accountNumber, pin);
	}
	
	public synchronized Withdrawal makeWithdrawal(Long accountNumber, Integer pin, Integer amount) throws Exception {
		Withdrawal withdrawal = null;
		
		Account account = accountService.getAccount(accountNumber);
	
		accountValidator.validate(account, pin);

		atmValidator.validate(amount, account, atmStore.getBankNotes().calculateAmount());

		Map<Denomination, Integer> atmDenomMap = atmStore.getBankNotes().getDenominations();

		Map<Denomination, Integer> initialDenomMap = ATMUtil.buildDenominationMap(
				new Integer[] {0, 0, 0, 0});

		// build withdrawal denominations map
		Map<Denomination, Integer> withdrawalDenomMap = BankNotes.processDenomination(initialDenomMap, 
				atmDenomMap, Denomination.getLargest(), amount);

		// subtract withdrawal denominations map from atm denominations map
		Map<Denomination, Integer> updateATMDenomMap = BankNotes.subtractDenomination(withdrawalDenomMap, atmDenomMap);

		// update account 
		// TODO: query JPA repository for accounts instead of account map
		accountService.updateAccount(account.getAccountNumber(), account.getAmount() - amount);

		// update atm store bank notes
		// TODO: query JPA repository for atm instead of atmStore
		atmStore.getBankNotes().setDenominations(updateATMDenomMap);
	
		// create withdrawal response
		withdrawal = new Withdrawal(accountNumber, amount, ATMUtil.getDenominationNotes(withdrawalDenomMap), 
				account.getAmount());
		
		return withdrawal;
	}
	
	public ATMStore getATMStore() {
		return this.atmStore;
	}
}
