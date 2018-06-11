package com.alanpmullane.atm.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
	@JsonProperty("accountNumber")
	private Long accountNumber;
	@JsonProperty("pin")
	private Integer pin;
	@JsonProperty("amount")
	private Integer amount;
	@JsonProperty("overdraft")
	private Integer overdraft;
}
