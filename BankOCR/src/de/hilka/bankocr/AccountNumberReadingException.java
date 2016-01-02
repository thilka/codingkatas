package de.hilka.bankocr;

public class AccountNumberReadingException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String m_fileName;
    
    public AccountNumberReadingException(String fileName, Throwable cause) {
        super(cause);
        m_fileName = fileName;
    }
    
    @Override
    public String getMessage() {
        return String.format("Cannot read account numbers from file: '$s'", m_fileName);
    }
}
