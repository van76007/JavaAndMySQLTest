package com.algo.topK;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.algo.processor.FileProcessor;
import com.algo.topK.dsa.TrieEntry;

/**
 * A test driver for the algorithm to find top k frequent words in a big text file
 * @author dvanvu
 * Usage: java -jar topK-0.0.1-SNAPSHOT.jar
 */
public class App {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// prompt for the file name
		System.out.print("Enter full path of the big file: ");
		String fileName = scanner.nextLine();

		// prompt for the number of chunks we want to split this file to fit in
		// RAM, e.g. 10
		System.out.print("Enter number of chunks this file to be splitted, e.g. 10: ");
		int chunks = scanner.nextInt();

		// prompt for the number of most frequent words, e.g. 10000
		System.out.print("Enter number of most frequent words, e.g. 10000: ");
		int k = scanner.nextInt();

		// prompt for the file name
		System.out.print("Enter delimiter to be used, e.g. | : ");
		String delimiter = scanner.next();

		System.out.println(
				String.format("File %s to be splitted into %d chunks to find top %d words. Each line is splitted by %s",
						fileName, chunks, k, delimiter));

		FileProcessor processor = new FileProcessor(fileName, chunks, k, delimiter);

		try {
			List<TrieEntry> topWords = processor.findTopKWords();
			System.out.println(String.format("Top %d words are:", k));
			for (TrieEntry e : topWords) {
				System.out.println(e.toString());
			}
		} catch (IOException e) {
			System.out.println("Exception process file" + e.getMessage());
		}

		scanner.close();
	}
}
