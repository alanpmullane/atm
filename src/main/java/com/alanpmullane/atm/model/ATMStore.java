package com.alanpmullane.atm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ATMStore {
	@JsonProperty("bankNotes")
	private BankNotes bankNotes;
	
	
}
