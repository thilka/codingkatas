package de.hilka.bankocr;

public class AccountNumberParseException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    
    private String m_firstLine = null;
    private String m_secondLine = null;
    private String m_thirdLine = null;
    
    public AccountNumberParseException(String firstLine, String secondLine, String thirdLine) {
        m_firstLine = firstLine;
        m_secondLine = secondLine;
        m_thirdLine = thirdLine;
    }
    
    @Override
    public String getMessage() {
        return "Problems parsing this account number: " + "\r\n"
                + m_firstLine + "\r\n"
                + m_secondLine + "\r\n"
                + m_thirdLine + "\r\n";
    }
}
