package com.alanpmullane.atm.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ATMUtilTest {

	@Test
	public void hasAtmFunds() {
		assertTrue(ATMUtil.hasSufficientATMFunds(10, 5));
	}
}
