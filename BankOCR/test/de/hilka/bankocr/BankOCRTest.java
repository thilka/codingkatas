package de.hilka.bankocr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BankOCRTest {
    
    private static final String OUTPUT_FILE = "out.txt"; 
    
    @Before public void setUp() {
        cleanupOutputFile();
    }
    
    @After public void tearDown() {
        cleanupOutputFile();
        
    }

    private void cleanupOutputFile() {
        File outputFile = new File(OUTPUT_FILE);
        if (outputFile.exists()) {
            outputFile.delete();
        }
    }

    @Test
    public void emptyFileIsHandledProperly() throws Exception {
        List<String> accountList = new BankOCR().extractFromFile("/empty.txt");
        assertEquals(0, accountList.size());
    }

    @Test
    public void canReadFileWithOneLine() throws Exception {
        List<String> accountList = new BankOCR().extractFromFile("/oneline.txt");
        assertEquals(1, accountList.size());
        assertEquals("123456789", accountList.get(0));
    }
    
    @Test
    public void canReadFileWithOneLineWithParsingProblems() throws Exception {
        List<String> accountList = new BankOCR().extractFromFile("/linewithparseproblems.txt");
        assertEquals(1, accountList.size());
        assertEquals("1?3456789 ILL", accountList.get(0));
    }

    @Test
    public void canReadFileWithThreeLines() throws Exception {
        List<String> accountList = new BankOCR().extractFromFile("/threelines.txt");
        assertEquals(3, accountList.size());
        assertEquals("123456789", accountList.get(0));
        assertEquals("456789123 ERR", accountList.get(1));
        assertEquals("1?3456789 ILL", accountList.get(2));
    }
    
    @Test (expected = AccountFileReadingException.class)
    public void throwsParseExceptionIfLineNotProperlyFormatted() throws Exception {
        new BankOCR().extractFromFile("/unknownFile.txt");
    }
    
    @Test
    public void writesOutputFileProperly() throws Exception {
        List<String> accountList = new BankOCR().extractFromFile("/threelines.txt");
        new BankOCR().writeLinesToOutputFile(OUTPUT_FILE, accountList);
        File outputFile = new File(OUTPUT_FILE);
        assertTrue(outputFile.exists());
        List<String> allLines = Files.readAllLines(Paths.get(outputFile.toURI()));
        
        assertEquals("123456789", allLines.get(0));
        assertEquals("456789123 ERR", allLines.get(1));
        assertEquals("1?3456789 ILL", allLines.get(2));
    }
}
