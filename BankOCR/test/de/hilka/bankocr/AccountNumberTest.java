package de.hilka.bankocr;

import static org.junit.Assert.*;

import org.junit.Test;


public class AccountNumberTest {

    @Test
    public void throwsExceptionIfAccountNumberIsNotValid() throws Exception {
        AccountNumber accountNumber = new AccountNumber("111111111");
        assertFalse(accountNumber.hasValidChecksum());
    }
    
    @Test (expected = AccountNumberValidationException.class)
    public void throwsExceptionIfAccountNumberIsTooShort() throws Exception {
        new AccountNumber("111");
    }
    
    @Test
    public void createsAccountNumberProperlyIfValid() throws Exception {
        AccountNumber accountNumber = new AccountNumber("123456789");
        assertNotNull(accountNumber);
        assertTrue(accountNumber.hasValidChecksum());
    }
    
    @Test
    public void flagProperlySetIfNumberContainsInvalidDigit() throws Exception {
        AccountNumber accountNumber = new AccountNumber("1?3456789");
        assertNotNull(accountNumber);
        assertFalse(accountNumber.hasAllValidDigits());
    }
    
    
}
