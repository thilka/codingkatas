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

			String resultString = "";
			
			if (i % 3 == 0) {
				resultString += "Fizz";
			} 
			if (i % 5 == 0) {
				resultString += "Buzz";
			} 

			if (resultString.isEmpty()) {
				resultString = Integer.toString(i);
			}
			result.add(resultString);
		}
		
		return result;
	}

	
	
	
}
