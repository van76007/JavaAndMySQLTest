package com.algo.topK;

import java.util.Comparator;
import java.util.PriorityQueue;

import com.algo.topK.dsa.Trie;
import com.algo.topK.dsa.TrieEntry;
import com.algo.topK.dsa.impl.TrieImpl;

/*
 * A class to extract top K words from a stream
 * Source: https://github.com/mnadeem/data-structures-algorithms
 * @author Nadeem Mohammad
 */
public class TopKFrequentWords {
	/** The K value of top K frequent words **/
	private final int maxSize;
	/**
	 * A Trie to storage all words in an efficient way if some of them share the
	 * same prefix
	 **/
	private Trie trie;
	/**
	 * A heap to keep track of top K frequent words when reading from the Trie
	 **/
	private PriorityQueue<TrieEntry> maxHeap;

	public TopKFrequentWords(int k) {
		this.trie = new TrieImpl();
		this.maxSize = k;
		this.maxHeap = new PriorityQueue<TrieEntry>(k, maxHeapComparator());
	}

	/**
	 * A method to add new word to the Trie
	 * 
	 * @param word
	 *            The string to be added
	 */
	public void add(String word) {
		this.trie.insert(word);
	}

	/**
	 * A method to extract top K frequent words from the Trie
	 * 
	 * @return top K frequent words
	 */
	public PriorityQueue<TrieEntry> getTopKWords() {

		/**
		 * Reading all words stored in the Trie. Two cases: 1. If the heap is
		 * not full yet: Adding the word fo the heap 2. If the heap is full:
		 * Replace the top element by the word if the frequency of the word is
		 * greater than that of the top element
		 */
		for (TrieEntry trieEntry : this.trie.getAll()) {
			if (this.maxHeap.size() < this.maxSize) {
				this.maxHeap.add(trieEntry);
			} else if (this.maxHeap.peek().getFrequency() < trieEntry.getFrequency()) {
				this.maxHeap.remove();
				this.maxHeap.add(trieEntry);
			}
		}

		return this.maxHeap;
	}

	/**
	 * A comparator to order element in the priority queue
	 * 
	 * @return
	 */
	private Comparator<TrieEntry> maxHeapComparator() {
		return new Comparator<TrieEntry>() {
			public int compare(TrieEntry o1, TrieEntry o2) {
				return o1.getFrequency() - o2.getFrequency();
			}
		};
	}
}
