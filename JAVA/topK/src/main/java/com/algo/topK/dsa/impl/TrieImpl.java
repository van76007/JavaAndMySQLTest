package com.algo.topK.dsa.impl;

import java.util.List;

import com.algo.topK.dsa.Node;
import com.algo.topK.dsa.Trie;
import com.algo.topK.dsa.TrieEntry;

/**
 * An implementation of Trie Data structure
 * https://github.com/mnadeem/data-structures-algorithms
 * 
 * @author Nadeem Mohammad
 *
 */
public class TrieImpl implements Trie {

	private Node root;

	public TrieImpl() {
		this.root = new Node();
	}

	public boolean contains(String word) {
		Node node = root.search(word);
		return node != null ? node.isWord() : false;
	}

	public void insert(String word) {
		this.root.add(word);
	}

	public int frequency(String word) {
		Node node = root.search(word);
		return node != null ? node.getFrequency() : 0;
	}

	public List<TrieEntry> getAll() {
		return this.root.getWords();
	}
}
