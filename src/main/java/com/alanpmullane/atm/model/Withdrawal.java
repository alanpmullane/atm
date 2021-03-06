package com.alanpmullane.atm.model;

import java.util.LinkedHashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Withdrawal {
	private Long accountNumber;
	private Integer amount;
	private LinkedHashMap<Denomination, Integer> denominations;
	private Integer balance;
}
