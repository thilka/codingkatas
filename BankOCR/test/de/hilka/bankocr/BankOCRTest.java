package de.hilka.bankocr;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void canReadFileWithThreeLines() throws Exception {
        List<String> accountList = new BankOCR().extractFromFile("/threelines.txt");
        assertEquals(3, accountList.size());
        assertEquals("123456789", accountList.get(0));
        assertEquals("111233445", accountList.get(1));
        assertEquals("345882865", accountList.get(2));
    }
    
    @Test (expected = AccountNumberReadingException.class)
    public void throwsParseExceptionIfLineNotProperlyFormatted() throws Exception {
        new BankOCR().extractFromFile("/unknownFile.txt");
    }

}
