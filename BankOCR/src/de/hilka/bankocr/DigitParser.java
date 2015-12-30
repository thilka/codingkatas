package de.hilka.bankocr;


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
        String partFromFirstLine = "";
        String partFromSecondLine = "";
        String partFromThirdLine = "";
        for (int i = 0; i < m_firstLine.length(); i++) {
            partFromFirstLine += m_firstLine.subSequence(i, i + 1);
            partFromSecondLine += m_secondLine.subSequence(i, i + 1);
            partFromThirdLine += m_thirdLine.subSequence(i, i + 1);
            if (i > 0 && (i + 1) % 3 == 0) {
                m_digits += Digit.getDigit(partFromFirstLine, partFromSecondLine, partFromThirdLine);
                partFromFirstLine = "";
                partFromSecondLine = "";
                partFromThirdLine = "";
            }
        }

    }

    private String m_digits = "";
    public String getDigits() {
        return m_digits;
    }


    private enum Digit {
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

        private Digit(String value, String firstLine, String secondLine, String thirdLine) {
            m_value = value;
            m_firstLine = firstLine;
            m_secondLine = secondLine;
            m_thirdLine = thirdLine;
        }

        public static String getDigit(String firstLine, String secondLine, String thirdLine) {
            for (Digit digit : values()) {
                if (digit.getFirstLine().equals(firstLine)
                        && digit.getSecondLine().equals(secondLine) 
                        && digit.getThirdLine().endsWith(thirdLine)) {
                    return digit.getValue();
                }
            }
            return null;
        }

    }
}
