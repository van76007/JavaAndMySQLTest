package com.algo.twoSum;

import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
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
	 * Test case for sorted list
	 */
	public void testFindPairsInSortedList() {
		int target = 6;
		int[] numbers = { 1, 2, 3, 3, 4, 5, 6 };

		List<Pair> result = App.findPairs(numbers, target);
		List<Pair> expectedResult = Arrays.asList(new Pair(1, 6), new Pair(2, 5), new Pair(3, 4));
		assertTrue(result.equals(expectedResult));
	}
	
	/**
	 * Test case for unsorted list
	 */
	public void testFindPairsInUnSortedList() {
		int target = 6;
		int[] numbers = { 4, 1, 2, 3, 3, 0, 6 };

		List<Pair> result = App.findPairs(numbers, target);
		List<Pair> expectedResult = Arrays.asList(new Pair(1, 3), new Pair(4, 5), new Pair(6, 7));
		assertTrue(result.equals(expectedResult));
	}
	
	/**
	 * Test case for list containing duplicated elements
	 */
	public void testFindPairsInListContainingDuplicatedElements() {
		int target = 6;
		int[] numbers = { 3, 3, 3, 2, 2, 3, 3 };

		List<Pair> result = App.findPairs(numbers, target);
		List<Pair> expectedResult = Arrays.asList(new Pair(1, 7), new Pair(1, 2), new Pair(2, 3), new Pair(2, 7),
				new Pair(3, 7), new Pair(6, 7));
		assertTrue(expectedResult.containsAll(result));
	}
}
