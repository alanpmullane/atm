package com.alanpmullane.atm.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alanpmullane.atm.model.Account;
import com.alanpmullane.atm.model.Denomination;

public class ATMUtil {
	
	public static boolean hasSufficientATMFunds(Integer atmAmount, Integer amount) {
		return atmAmount > amount;
	}
	
	public static boolean hasSufficentAccountFunds(Account account, Integer amount) {
		return account.getAmount() + account.getOverdraft() > amount;
	}
	
	public static Map<Denomination, Integer> buildDenominationMap(Integer... denomAmounts) {
		Map<Denomination, Integer> denominations = new HashMap<>();
		int i = 0;
		for (Integer amount : denomAmounts) {
			denominations.put(Denomination.values()[i], amount);
			i++;
		}
		return denominations;
	}
	
	public static LinkedHashMap<Denomination, Integer> getDenominationNotes(
			Map<Denomination, Integer> withdrawalDenomMap) {
		LinkedHashMap<Denomination, Integer> orderedWithdrawalNotesMap = new LinkedHashMap<>();
		for(Denomination denom : Denomination.values()) {
			orderedWithdrawalNotesMap.put(denom, withdrawalDenomMap.get(denom) / denom.getValue());
		}
		return orderedWithdrawalNotesMap;
	}
}
