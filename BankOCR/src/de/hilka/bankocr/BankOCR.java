package de.hilka.bankocr;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * See http://codingdojo.org/cgi-bin/index.pl?back=KataBankOCR
 * @author thilka
 *
 */
public class BankOCR {

	public List<String> extractFromFile(String fileName) {
		
		List<String> inputLines = new ArrayList<String>();
		try (Stream<String> stream = Files.lines(Paths.get(getClass().getResource(fileName).toURI()))) {
			
			stream.forEach(inputLines::add);

		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}

		List<String> result = new ArrayList<String>();

		String firstLine = null;
		String secondLine = null;
		String thirdLine = null;
		for (String line : inputLines) {
			if (firstLine == null) {
				firstLine = line;
			} else if (secondLine == null) {
				secondLine = line;
			} else if (thirdLine == null) {
				thirdLine = line;
			}
			
			if (thirdLine != null) {
				result.add(new DigitParser(firstLine, secondLine, thirdLine).getDigits());
				firstLine = null;
				secondLine = null;
				thirdLine = null;
			}
		}
		
		
		return result;
	}
	
	
	
	
	
}
