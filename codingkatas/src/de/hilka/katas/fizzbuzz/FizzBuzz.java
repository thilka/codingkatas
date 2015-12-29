package de.hilka.katas.fizzbuzz;

import java.util.ArrayList;
import java.util.List;

/**
 * See http://codingdojo.org/cgi-bin/index.pl?back=KataFizzBuzz
 * 
 * @author thilka
 *
 */
public class FizzBuzz {

	public List<String> getOutput() {
		List<String> result = new ArrayList<String>();
		
		for (int i = 1; i <= 100; i++) {
			
			boolean fizz = containsOrIsDividableBy(i, 3);
			boolean buzz = containsOrIsDividableBy(i, 5);

			String resultString = generateResultString(fizz, buzz,i);
			result.add(resultString);
		}
		
		return result;
	}

	private boolean containsOrIsDividableBy(int i, int number) {
		if (i % number == 0) {
			return true;
		} 
		if (contains(Integer.toString(i), Character.forDigit(number, 10))) {
			return true;
		}
		return false;
	}

	private String generateResultString(boolean fizz, boolean buzz, int index) {
		String numberString = Integer.toString(index);
		String resultString = "";
		if (fizz) {
			resultString += "Fizz";
		}
		if (buzz) {
			resultString += "Buzz";
		}
		if (!fizz && !buzz) {
			resultString = numberString;
		}
		return resultString;
	}

	private boolean contains(String numberString, char character) {
		for (int i = 0; i < numberString.length(); i++) {
			if (numberString.charAt(i) == character) {
				return true;
			}
		}
		return false;
	}
}
