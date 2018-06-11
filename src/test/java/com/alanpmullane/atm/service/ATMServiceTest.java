package com.alanpmullane.atm.service;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ATMServiceTest {

	@Autowired
	private ATMService atmService;
	
	@Test
	public void testInitialise() throws Exception {
		atmService.initialiseATM();
		
		assertTrue(atmService.getATMStore() != null);
	}
}
