package com.algo.topK.dsa;

import java.util.List;

/**
 * The interface of Trie Data structure
 * https://github.com/mnadeem/data-structures-algorithms
 * 
 * @author Nadeem Mohammad
 *
 */
public interface Trie {
	/**
	 * Check if the word is present in the Trie representation
	 * 
	 * @param word
	 *            The word to be checked
	 * @return true if there is path in the Trie represents this word
	 */
	boolean contains(String word);

	/**
	 * Insert a new word to the Trie representation
	 * 
	 * @param word
	 *            The word to be inserted
	 */
	void insert(String word);

	/**
	 * Return frequency of the word in the Trie representation
	 * 
	 * @param word
	 *            The word to be querried
	 * @return Number of the same word has been added to the Trie
	 */
	int frequency(String word);

	/**
	 * Get all words have been added to the Trie representation
	 * 
	 * @return List of words and their frequencies
	 */
	List<TrieEntry> getAll();
}
