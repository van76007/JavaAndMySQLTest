package com.algo.topK;

import java.util.PriorityQueue;

import com.algo.topK.dsa.TrieEntry;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class TopKFrequentWordsTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public TopKFrequentWordsTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(TopKFrequentWordsTest.class);
	}

	/**
	 * Test case when number of distinct words is greater than K
	 */
	public void testFindTopThreeWordsWhenThereAreAtLeastThreeDifferentWords() {
		TopKFrequentWords stream = new TopKFrequentWords(3);

		for (int i = 0; i < 5; i++) {
			stream.add("hello");
		}

		for (int i = 0; i < 2; i++) {
			stream.add("new");
		}

		for (int i = 0; i < 3; i++) {
			stream.add("world");
		}

		stream.add("I am here");

		PriorityQueue<TrieEntry> topWords = stream.getTopKWords();
		assertTrue(topWords.size() == 3);
		assertTrue(topWords.contains(new TrieEntry("hello", 5)));
		assertTrue(topWords.contains(new TrieEntry("new", 2)));
		assertTrue(topWords.contains(new TrieEntry("world", 3)));
	}

	/**
	 * Test case when number of distinct words is less than K
	 */
	public void testFindTopThreeWordsWhenThereAreTwoDistinctWordsOnly() {
		TopKFrequentWords stream = new TopKFrequentWords(3);

		for (int i = 0; i < 5; i++) {
			stream.add("hello");
		}

		for (int i = 0; i < 2; i++) {
			stream.add("new");
		}

		PriorityQueue<TrieEntry> topWords = stream.getTopKWords();
		assertTrue(topWords.size() == 2);
		assertTrue(topWords.contains(new TrieEntry("hello", 5)));
		assertTrue(topWords.contains(new TrieEntry("new", 2)));
	}

	/**
	 * Test case when all words having the same frequency
	 */
	public void testFindTopThreeWordsWhenAllWordsAreTheSame() {
		TopKFrequentWords stream = new TopKFrequentWords(3);

		for (int i = 0; i < 5; i++) {
			stream.add("hello");
		}

		PriorityQueue<TrieEntry> topWords = stream.getTopKWords();
		assertTrue(topWords.size() == 1);
		assertTrue(topWords.contains(new TrieEntry("hello", 5)));
	}

	/**
	 * Test case when some words having the same frequency
	 */
	public void testFindTopThreeWordsWhenThereAreTwoWordsHavingTheSameFrequency() {
		TopKFrequentWords stream = new TopKFrequentWords(3);

		for (int i = 0; i < 5; i++) {
			stream.add("hello");
		}

		for (int i = 0; i < 2; i++) {
			stream.add("new");
		}

		for (int i = 0; i < 2; i++) {
			stream.add("world");
		}

		PriorityQueue<TrieEntry> topWords = stream.getTopKWords();
		assertTrue(topWords.size() == 3);
		assertTrue(topWords.contains(new TrieEntry("hello", 5)));
		assertTrue(topWords.contains(new TrieEntry("new", 2)));
		assertTrue(topWords.contains(new TrieEntry("world", 2)));
	}
}
