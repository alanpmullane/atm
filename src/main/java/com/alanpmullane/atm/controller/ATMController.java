package com.alanpmullane.atm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ATMController {

	@RequestMapping(value = "/accounts/{accountId}/pin/{pin}/balance", method = RequestMethod.GET)
	public ResponseEntity<String> getBalance(@PathVariable String accountId, @PathVariable String pin) {
		return null;
	}
	
	@RequestMapping(value = "/accounts/{account}/pin/{pin}/withdrawal", method = RequestMethod.POST)
	public ResponseEntity<String> makeWithdrawal(@PathVariable String accountId, @PathVariable String pin,
			@RequestBody String withdrawal) {
		return null;
	}
}
