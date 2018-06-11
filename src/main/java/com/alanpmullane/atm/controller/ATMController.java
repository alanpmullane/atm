package com.alanpmullane.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alanpmullane.atm.model.Balance;
import com.alanpmullane.atm.service.ATMService;

@RestController
@RequestMapping("/")
public class ATMController {
	
	@Autowired
	private ATMService atmService;

	@RequestMapping(value = "/accounts/{accountNumber}/pin/{pin}/balance", method = RequestMethod.GET)
	public ResponseEntity<Balance> checkBalance(@PathVariable Long accountNumber, @PathVariable Integer pin) {
		return ResponseEntity.ok(atmService.checkBalance(accountNumber, pin));
	}
	
	@RequestMapping(value = "/accounts/{accountNumber}/pin/{pin}/withdrawal", method = RequestMethod.POST)
	public ResponseEntity<String> makeWithdrawal(@PathVariable Long accountNumber, @PathVariable Integer pin,
			@RequestBody String withdrawal) {
		return null;
	}
}
