package com.algo.topK.dsa;

import java.util.Objects;

/**
 * An object model to represent a word and its frequency in the Trie storage
 * https://github.com/mnadeem/data-structures-algorithms
 * 
 * @author Nadeem Mohammad
 *
 */
public class TrieEntry {

	private final String word;
	/** Number of the same words added to the Trie **/
	private int frequency;

	public TrieEntry(String word, int frequency) {
		this.word = word;
		this.frequency = frequency;
	}

	public String getWord() {
		return word;
	}

	public int getFrequency() {
		return frequency;
	}

	@Override
	public String toString() {
		return String.format("Top word [word=%s, frequency=%d]", word, frequency);
	}
	
	/**
	 * Field-by-field comparision of 2 TrieEntry
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrieEntry other = (TrieEntry) obj;
		return (Objects.equals(word, other.word) && frequency == other.frequency);
	}
}
