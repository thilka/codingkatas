package de.hilka.bankocr;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


public class BankOCRTest {

	@Test
	public void emptyFileIsHandledProperly() throws Exception {
		List<String> accountList = new BankOCR().extractFromFile("/empty.txt");
		assertEquals(0, accountList.size());
	}
	@Test
	public void canReadFileWithOneLine() throws Exception {
		List<String> accountList = new BankOCR().extractFromFile("/oneline.txt");
		assertEquals(1, accountList.size());
		assertEquals("123456789", accountList.get(0));
	}
	
}
