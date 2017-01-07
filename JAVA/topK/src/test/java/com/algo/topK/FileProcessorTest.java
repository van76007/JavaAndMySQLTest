package com.algo.topK;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import com.algo.processor.FileProcessor;
import com.algo.topK.dsa.TrieEntry;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class FileProcessorTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public FileProcessorTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(FileProcessorTest.class);
	}
	
	/**
	 * Test case to find top 3 words in a text file
	 * Note that some words sharing prefix
	 */
	public void testFindTopThreeWords() {
		String testFile = "BigFile.txt";
		String delimiter = "|";
		String[] words = { "hello", "hell", "ello" };
		int[] frequency = { 30, 20, 10 };
		int numOfWord = 50;

		try {
			setupTestFile(testFile, delimiter, numOfWord, words, frequency);

			Random rn = new Random();
			int chunks = rn.nextInt(50 - 1 + 1) + 1;
			int k = 3;
			FileProcessor processor = new FileProcessor(testFile, chunks, k, delimiter);

			List<TrieEntry> topWords = processor.findTopKWords();

			removeTestFile(testFile);

			assertTrue(topWords.size() == 3);
			assertTrue(topWords.contains(new TrieEntry("hello", frequency[0] * numOfWord)));
			assertTrue(topWords.contains(new TrieEntry("hell", frequency[1] * numOfWord)));
			assertTrue(topWords.contains(new TrieEntry("ello", frequency[2] * numOfWord)));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setupTestFile(String fileName, String delimiter, int numOfWord, String[] words, int[] freq)
			throws IOException {

		File file = new File(fileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);

		for (int i = 0; i < freq[0]; i++) {
			String line = buildLine(delimiter, words[0], numOfWord);
			pw.println(line);
		}

		for (int i = 0; i < freq[1]; i++) {
			String line = buildLine(delimiter, words[1], numOfWord);
			pw.println(line);
		}

		for (int i = 0; i < freq[2]; i++) {
			String line = buildLine(delimiter, words[2], numOfWord);
			pw.println(line);
		}

		pw.close();
	}

	private String buildLine(String delimiter, String word, int numOfWord) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numOfWord; i++) {
			sb.append(word).append(delimiter);
		}

		return sb.toString().substring(0, sb.length() - 1);
	}

	private void removeTestFile(String fileName) {
		File file = new File(fileName);
		file.delete();
	}

}
