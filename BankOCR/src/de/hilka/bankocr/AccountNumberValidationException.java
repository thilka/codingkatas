package de.hilka.bankocr;

public class AccountNumberValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String m_accountNumber;

    public AccountNumberValidationException(String accountNumber) {
        m_accountNumber = accountNumber;
    }

    @Override
    public String getMessage() {
        return String.format("Account number '$s' not valid.", m_accountNumber);
    }
    
}
