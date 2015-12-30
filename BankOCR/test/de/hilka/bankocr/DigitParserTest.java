package de.hilka.bankocr;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class DigitParserTest {

    @Test
    public void parsesSingleOneCorrect() throws Exception {
        String lineOne =   "   ";
        String lineTwo =   "  |";
        String lineThree = "  |";

        String digits = new DigitParser(lineOne, lineTwo, lineThree).getDigits();
        assertEquals(1, digits.length());
        assertEquals("1", digits);
    }

    @Test
    public void parsesSingleZeroCorrect() throws Exception {
        String lineOne =   " _ ";
        String lineTwo =   "| |";
        String lineThree = "|_|";

        String digits = new DigitParser(lineOne, lineTwo, lineThree).getDigits();
        assertEquals(1, digits.length());
        assertEquals("0", digits);
    }

    @Test
    public void parsesAll10DigitsProperly() throws Exception {
        String lineOne =   "    _  _     _  _  _  _  _  _ ";
        String lineTwo =   "  | _| _||_||_ |_   ||_||_|| |";
        String lineThree = "  ||_  _|  | _||_|  ||_| _||_|";
        String digits =  new DigitParser(lineOne, lineTwo, lineThree).getDigits();
        assertEquals(10, digits.length());
        assertEquals("1234567890", digits);
    }

}
