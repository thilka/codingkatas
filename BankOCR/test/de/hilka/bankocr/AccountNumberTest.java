package de.hilka.bankocr;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;


public class AccountNumberTest {

    @Test (expected = AccountNumberValidationException.class)
    public void throwsExceptionIfAccountNumberIsNotValid() throws Exception {
        new AccountNumber("111111111");
    }
    
    @Test (expected = AccountNumberValidationException.class)
    public void throwsExceptionIfAccountNumberIsTooShort() throws Exception {
        new AccountNumber("111");
    }
    
    @Test
    public void createsAccountNumberProperlyIfValid() throws Exception {
        AccountNumber accountNumber = new AccountNumber("123456789");
        assertNotNull(accountNumber);
    }
}
