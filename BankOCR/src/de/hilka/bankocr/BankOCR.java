package de.hilka.bankocr;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * See http://codingdojo.org/cgi-bin/index.pl?KataBankOCR
 * @author thilka
 *
 */
public class BankOCR {

    public List<String> extractFromFile(String fileName) {

        List<String> inputLines = readFileIntoLines(fileName);

        List<String> result = parseInputLines(inputLines);

        return result;
    }

    private List<String> parseInputLines(List<String> inputLines) {
        List<String> result = new ArrayList<String>();

        String firstLine = null;
        String secondLine = null;
        String thirdLine = null;
        for (String line : inputLines) {
            if (line.trim().isEmpty()) {
                // ignore empty lines
                continue;
            }
            if (firstLine == null) {
                firstLine = line;
            } else if (secondLine == null) {
                secondLine = line;
            } else if (thirdLine == null) {
                thirdLine = line;
            }

            if (thirdLine != null) {
                String digits = new DigitParser(firstLine, secondLine, thirdLine).getDigits();
                AccountNumber accountNumber = new AccountNumber(digits);
                
                result.add(getResultLine(accountNumber));
                
                firstLine = null;
                secondLine = null;
                thirdLine = null;
            }
        }
        return result;
    }
    
    private String getResultLine(AccountNumber accountNumber) {
        String accountNumberString = accountNumber.getAccountNumber();
        if (!accountNumber.hasAllValidDigits()) {
            return accountNumberString + " ILL";
        }
        
        if (!accountNumber.hasValidChecksum()) {
            return accountNumberString + " ERR";
        }
        return accountNumberString;
    }

    private List<String> readFileIntoLines(String fileName) {
        List<String> inputLines = new ArrayList<String>();
        URL resource = getClass().getResource(fileName);
        if (resource == null) {
            throw new AccountFileReadingException(fileName, null);
        }
        try (Stream<String> stream = Files.lines(Paths.get(resource.toURI()))) {

            stream.forEach(inputLines::add);

        } catch (IOException | URISyntaxException e) {
            throw new AccountFileReadingException(fileName, e);
        }
        return inputLines;
    }

    public void writeLinesToOutputFile(String fileName, List<String> lines) {
        try {
            Files.write(Paths.get(fileName), lines);
        } catch (IOException e) {
            // TODO: handle exceptions properly
            e.printStackTrace();
        }
    }

}
