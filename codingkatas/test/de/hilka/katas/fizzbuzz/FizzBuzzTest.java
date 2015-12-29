package de.hilka.katas.fizzbuzz;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class FizzBuzzTest {

	private FizzBuzz m_fizzBuzz;

	private List<String> m_result;
	
	@Before
	public void setUp() {
		m_fizzBuzz = new FizzBuzz();
		m_result = m_fizzBuzz.getOutput();
	}
	
	@Test
	public void countIs100() throws Exception {
		assertEquals(100, m_result.size());
	}
	
	@Test
	public void returnsNumbersForOneAndTwo() throws Exception {
		assertEquals("1", m_result.get(0));
		assertEquals("2", m_result.get(1));
	}
	
	@Test
	public void returnsFizzForMultipleOfThrees() throws Exception {
		assertEquals("Fizz", m_result.get(2));
		assertEquals("Fizz", m_result.get(5));
		assertEquals("Fizz", m_result.get(8));
		assertEquals("Fizz", m_result.get(11));
	}
	
	@Test
	public void returnsBuzzForMultipleOfFives() throws Exception {
		assertEquals("Buzz", m_result.get(4));
		assertEquals("Buzz", m_result.get(9));
	}
	
	@Test
	public void returnsFizzBuzzForMultipleOfFiveteen() throws Exception {
		assertEquals("FizzBuzz", m_result.get(14));
		assertEquals("FizzBuzz", m_result.get(29));
		assertEquals("FizzBuzz", m_result.get(50));
	}
	
	@Test
	public void returnsFizzIfThreeIsPartOfTheNumber() throws Exception {
		assertEquals("Fizz", m_result.get(12));
		assertEquals("Fizz", m_result.get(22));
		assertEquals("Fizz", m_result.get(30));
	}
	
	@Test
	public void returnsBuzzIfNumberContainsAFive() throws Exception {
		assertEquals("Buzz", m_result.get(51));
	}
}
