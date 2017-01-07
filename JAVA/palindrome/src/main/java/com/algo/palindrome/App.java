package com.algo.palindrome;

/**
 * A class to implement logic to check if a string is a palindrome
 * Time complexity O(n), in which n = length of string
 * Space complexity O(1)
 * @author dvanvu
 *
 */
public class App {
	public static void main(String[] args) {
		String aString = "12321";
		System.out.println(String.format("%s is palindrome: %b", aString, isValidPalindrome(aString)));
	}
	
	/**
	 * A method to implement logic to check if a string is a palindrome
	 * @param s A string to be checked
	 * @return true if string is a palindrome, false otherwise
	 */
	public static boolean isValidPalindrome(String s) {
		boolean result = true;
		if (s == null || s.length() == 0) {
			result = false;
		} else {
			int len = s.length();
			for (int i = 0; i < len/2; i++) {
				if (s.charAt(i) != s.charAt( len - 1 - i)) {
					result = false;
					break;
				}
			}
		}
		return result;
	}
}
