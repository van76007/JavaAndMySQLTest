package com.algo.palindrome;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App
 */
public class AppTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}
	
	/**
	 * Test case for empty string
	 */
	public void testIfEmptyStringIsPalindromeShouldReturnFalse() {
		String emptyString = "";
		assertFalse(App.isValidPalindrome(emptyString));
	}
	
	/**
	 * Test case for null string
	 */
	public void testIfNullStringIsPalindromeShouldReturnFalse() {
		String nullString = null;
		assertFalse(App.isValidPalindrome(nullString));
	}
	
	/**
	 * Test case for palindrome all lower case
	 */
	public void testIfSymmetricStringIsPalindromeShouldReturnTrue() {
		String aPalindrome = "racecar";
		assertTrue(App.isValidPalindrome(aPalindrome));
	}	
	
	/**
	 * Test case for palindrome includes both upper and lower case
	 */
	public void testIfSymmetricStringIsPalindromeShouldReturnTrueIfCompareCaseSensitive() {
		String aPalindrome = "EyE";
		assertTrue(App.isValidPalindrome(aPalindrome));
	}
	
	/**
	 * Test case for non-palindrome all lower case
	 */
	public void testIfASymmetricStringIsPalindromeShouldReturnFalse() {
		String aPalindrome = "race car";
		assertFalse(App.isValidPalindrome(aPalindrome));
	}
	
	/**
	 * Test case for non-palindrome includes both upper and lower case
	 */
	public void testIfASymmetricStringIsPalindromeShouldReturnFalseIfCompareCaseSensitive() {
		String aPalindrome = "Racecar";
		assertFalse(App.isValidPalindrome(aPalindrome));
	}
}
