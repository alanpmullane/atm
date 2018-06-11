package com.alanpmullane.atm.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ATMStore {
	@JsonProperty("amount")
	private Integer amount;
	@JsonProperty("denominations")
	private List<Denomination> denominations;
}
