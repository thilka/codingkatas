package de.hilka.bankocr;

public class AccountNumber {

    private String m_accountNumber;

    public AccountNumber(String accountNumber) {
        m_accountNumber = accountNumber;
        validate();
    }

    private void validate() {
        if (m_accountNumber.length() != 9) {
            throw new AccountNumberValidationException(m_accountNumber);
        }
        
        int checksum = calculateCheckSum();
        if (checksum % 11 != 0) {
            throw new AccountNumberValidationException(m_accountNumber);
        }
    }

    private int calculateCheckSum() {
        int checksum = 0;
        for (int i = 9; i > 0; i--) {
            int digit = Integer.parseInt("" + m_accountNumber.charAt(i - 1));
            checksum += digit * (10 - i);
        }
        return checksum;
    }
    
    public String getAccountNumber() {
        return m_accountNumber;
    }

}
