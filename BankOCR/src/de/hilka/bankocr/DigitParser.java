package de.hilka.bankocr;

/**
 * A digit parser parses three lines that represent the bank account number
 * consisting of 9 digits.
 * 
 * @author thilka
 *
 */
public class DigitParser {

    private String m_firstLine;
    public String getFirstLine() { return m_firstLine; }

    private String m_secondLine;
    public String getSecondLine() { return m_secondLine; }

    private String m_thirdLine;
    public String getThirdLine() { return m_thirdLine; }

    public DigitParser(String firstLine, String secondLine, String thirdLine) {
        m_firstLine = firstLine;
        m_secondLine = secondLine;
        m_thirdLine = thirdLine;

        parseLines();
    }

    private void parseLines() {
        Digit currentDigit = null;
        for (int i = 0; i < m_firstLine.length(); i++) {
            
            if (currentDigit == null) {
                currentDigit = new Digit();
            }
            
            currentDigit.addChars(
                    nextCharacter(m_firstLine, i), 
                    nextCharacter(m_secondLine, i),
                    nextCharacter(m_thirdLine, i));
            
            if (currentDigit.isComplete()) {
                m_digits += currentDigit.getDigit();
                currentDigit = null;
            }
        }
    }
    
    private String nextCharacter(String line, int index) {
        return line.substring(index, index + 1);
    }

    private String m_digits = "";
    public String getDigits() { return m_digits; }

    private class Digit {
        String digitCharsInFirstLine = "";
        String digitCharsSecondLine = "";
        String digitCharsThirdLine = "";

        private void addChars(CharSequence firstLineChar, CharSequence secondLineChar, CharSequence thirdLineChar) {
            digitCharsInFirstLine += firstLineChar;
            digitCharsSecondLine += secondLineChar;
            digitCharsThirdLine += thirdLineChar;
        }
        
        private String getDigit() {
            return DigitEnum.getDigit(digitCharsInFirstLine, digitCharsSecondLine, digitCharsThirdLine);
        }
        
        private boolean isComplete() {
            return digitCharsInFirstLine != null && digitCharsInFirstLine.length() == 3
                    && digitCharsSecondLine != null && digitCharsSecondLine.length() == 3
                    && digitCharsThirdLine != null && digitCharsThirdLine.length() == 3;
        }
    }

    private enum DigitEnum {
        UNKNOWN("?", 
                "", 
                "", 
                ""),
        ZERO   ("0", 
                " _ ", 
                "| |", 
                "|_|"),
        ONE    ("1", 
                "   ",
                "  |", 
                "  |"),
        TWO    ("2", 
                " _ ", 
                " _|", 
                "|_ "),
        THREE  ("3", 
                " _ ", 
                " _|", 
                " _|"),
        FOUR   ("4", 
                "   ", 
                "|_|", 
                "  |"),
        FIVE   ("5", 
                " _ ", 
                "|_ ", 
                " _|"),
        SIX    ("6", 
                " _ ", 
                "|_ ", 
                "|_|"),
        SEVEN  ("7", 
                " _ ", 
                "  |", 
                "  |"),
        EIGHT  ("8", 
                " _ ", 
                "|_|", 
                "|_|"),
        NINE   ("9", 
                " _ ", 
                "|_|", 
                " _|");

        private String m_value;
        public String getValue() { return m_value; }

        private String m_firstLine;
        public String getFirstLine() { return m_firstLine; }

        private String m_secondLine;
        public String getSecondLine() { return m_secondLine; }

        private String m_thirdLine;
        public String getThirdLine() { return m_thirdLine; }

        private DigitEnum(String value, String firstLine, String secondLine, String thirdLine) {
            m_value = value;
            m_firstLine = firstLine;
            m_secondLine = secondLine;
            m_thirdLine = thirdLine;
        }

        public static String getDigit(String firstLine, String secondLine, String thirdLine) {
            for (DigitEnum digit : values()) {
                if (digit.getFirstLine().equals(firstLine)
                        && digit.getSecondLine().equals(secondLine) 
                        && digit.getThirdLine().endsWith(thirdLine)) {
                    return digit.getValue();
                }
            }
            return UNKNOWN.getValue();
        }

    }
}
