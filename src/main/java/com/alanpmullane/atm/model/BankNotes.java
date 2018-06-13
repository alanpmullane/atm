package com.alanpmullane.atm.model;

import java.util.Map;

import com.alanpmullane.atm.util.ATMUtil;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankNotes {
	@JsonProperty("denominations")
	private Map<Denomination, Integer> denominations;
	
	public Integer calculateAmount() {
		Integer amount = 0;
		for (Denomination denomination : Denomination.values()) {
			amount += denominations.get(denomination);
		}
		return amount;
	}
	
	public static Map<Denomination, Integer> processDenomination(Map<Denomination, Integer> denomMap,
			Map<Denomination, Integer> originalDenomMap, 
			Denomination currentDenom, Integer amount) {
		
		Integer denomValue = currentDenom.getValue();
		Integer denomAmount = originalDenomMap.get(currentDenom);
		
		Integer newDenomAmount = 0;
		Integer remainder = 0;
		
		if (amount <= denomAmount) {
			newDenomAmount = (amount / denomValue) * denomValue;
		} else {
			newDenomAmount = denomAmount;
		}
		remainder = amount - newDenomAmount;
		
		denomMap.put(currentDenom, newDenomAmount);

		Denomination nextDenom = Denomination.getNext(currentDenom);
		
		if (nextDenom != null) {
			processDenomination(denomMap, originalDenomMap, nextDenom, remainder);
		}
		return denomMap;
	}
	
	public static Map<Denomination, Integer> subtractDenomination(Map<Denomination, Integer> denomMap,
			Map<Denomination, Integer> originalDenomMap) {
		Map<Denomination, Integer> updatedDenomMap = ATMUtil.buildDenominationMap(new Integer[] {0, 0, 0, 0});
		
		for (Denomination denomination : Denomination.values()) {
			updatedDenomMap.put(denomination, originalDenomMap.get(denomination) - denomMap.get(denomination));
		}
		
		return updatedDenomMap;
	}
}
