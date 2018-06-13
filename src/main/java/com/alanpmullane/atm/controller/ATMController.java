package com.alanpmullane.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alanpmullane.atm.exception.ErrorResponse;
import com.alanpmullane.atm.exception.InsufficientFundsException;
import com.alanpmullane.atm.exception.InvalidAccountException;
import com.alanpmullane.atm.exception.InvalidAmountException;
import com.alanpmullane.atm.model.Balance;
import com.alanpmullane.atm.model.Withdrawal;
import com.alanpmullane.atm.service.ATMService;

@RestController
@RequestMapping("/")
public class ATMController {
	
	@Autowired
	private ATMService atmService;

	@RequestMapping(value = "/accounts/{accountNumber}/pin/{pin}/balance", method = RequestMethod.GET)
	public Balance checkBalance(@PathVariable Long accountNumber, @PathVariable Integer pin) throws Exception {
		return atmService.checkBalance(accountNumber, pin);
	}
	
	@RequestMapping(value = "/accounts/{accountNumber}/pin/{pin}/withdrawal/{amount}", method = RequestMethod.GET)
	public Withdrawal makeWithdrawal(@PathVariable Long accountNumber, @PathVariable Integer pin,
			@PathVariable Integer amount) throws Exception {
		return atmService.makeWithdrawal(accountNumber, pin, amount);
	}

	@ExceptionHandler(value = { InvalidAccountException.class, InvalidAmountException.class, InsufficientFundsException.class})
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}

}
