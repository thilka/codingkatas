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
        if (checksum % 11 == 0) {
            m_validChecksum = true;
        }
        
        m_allValidDigits = !m_accountNumber.contains("?");
    }
    
    private boolean m_validChecksum;
    public boolean hasValidChecksum() { return m_validChecksum; }

    private boolean m_allValidDigits;
    public boolean hasAllValidDigits() { return m_allValidDigits; }
    
    
    private int calculateCheckSum() {
        if (m_accountNumber.contains("?")) {
            return -1;
        }
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
