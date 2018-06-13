package com.alanpmullane.atm.model;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.alanpmullane.atm.util.ATMUtil;

public class BankNotesTest {

	private BankNotes atmBankNotes = null;
	private BankNotes bankNotes = null;
	Denomination currentDenom = null;
	
	@Before
	public void setup() {
		Map<Denomination, Integer> initialDenomMap = ATMUtil.buildDenominationMap(
				new Integer[] {0, 0, 0, 0});
		bankNotes = new BankNotes(initialDenomMap);
		
		Map<Denomination, Integer> originalDenomMap = ATMUtil.buildDenominationMap(
				new Integer[] {100, 300, 600, 1000});
		atmBankNotes = new BankNotes(originalDenomMap);
		currentDenom = Denomination.getLargest();
	}
	
	@Test
	public void processLargeAmount() {
		Integer amount = 1245;
		Map<Denomination, Integer> updatedDenomMap = BankNotes
				.processDenomination(bankNotes.getDenominations(), atmBankNotes.getDenominations(), 
						currentDenom, amount);
		BankNotes updatedBankNotes = new BankNotes(updatedDenomMap);

		assertTrue(updatedBankNotes.calculateAmount().equals(amount));

	}
	
	@Test
	public void processMediumAmount() {
		Integer amount = 215;
		Map<Denomination, Integer> updatedDenomMap = BankNotes
				.processDenomination(bankNotes.getDenominations(), atmBankNotes.getDenominations(), 
						currentDenom, amount);
		BankNotes updatedBankNotes = new BankNotes(updatedDenomMap);

		assertTrue(updatedBankNotes.calculateAmount().equals(amount));
	}
	
	@Test
	public void processSmallAmount() {
		Integer amount = 5;
		Map<Denomination, Integer> updatedDenomMap = BankNotes
				.processDenomination(bankNotes.getDenominations(), atmBankNotes.getDenominations(), 
						currentDenom, amount);
		BankNotes updatedBankNotes = new BankNotes(updatedDenomMap);

		assertTrue(updatedBankNotes.calculateAmount().equals(amount));
	}
	
	@Test
	public void subtractBankNotes() {
		Integer amount = 215;
		Map<Denomination, Integer> updatedDenomMap = BankNotes
				.processDenomination(bankNotes.getDenominations(), atmBankNotes.getDenominations(), 
						currentDenom, amount);
		BankNotes updatedBankNotes = new BankNotes(updatedDenomMap);

		assertTrue(updatedBankNotes.calculateAmount().equals(amount));

		Map<Denomination, Integer> subtractedDemoninations = BankNotes.subtractDenomination(updatedBankNotes.getDenominations(), 
				atmBankNotes.getDenominations());
		BankNotes subtractedBankNotes = new BankNotes(subtractedDemoninations);
		
		assertTrue(subtractedBankNotes.calculateAmount().equals(atmBankNotes.calculateAmount() - amount));
	}
}
