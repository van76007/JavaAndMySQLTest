package com.algo.topK.dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An object model of Trie node Source:
 * https://github.com/mnadeem/data-structures-algorithms
 * 
 * @author Nadeem Mohammad
 *
 */
public class Node {
	/** Character is stored in this node **/
	private Character value;
	private Node parent;
	private Map<Character, Node> children;
	/**
	 * If the path from root to this node represents a word to be stored in the
	 * Trie
	 **/
	private boolean isWord;
	/**
	 * If the node represents a word, this is the frequency of how many times
	 * the same word is added to the Trie
	 **/
	private int frequency;

	public Node() {
		this.children = new HashMap<Character, Node>();
		this.isWord = false;
	}

	public Node(Character chr, Node parent) {
		this();
		this.value = chr;
		this.parent = parent;
	}

	Character getValue() {
		return value;
	}

	Node get(char key) {
		return this.children.get(key);
	}

	/**
	 * A method to add a word to the Trie Increase the frequency of word if need
	 * 
	 * @param word
	 *            The word to be added
	 */
	public void add(String word) {
		char charAt = word.charAt(0);
		if (!this.children.containsKey(charAt)) {
			Node temp = new Node(charAt, this);
			this.children.put(charAt, temp);
		}

		if (word.length() > 1) {
			this.get(charAt).add(word.substring(1));
		} else {
			this.get(charAt).setWord(true);
			this.get(charAt).frequency++;
		}
	}

	/**
	 * A method to return this node or one of its child node if the associated
	 * character is part of the given word
	 * 
	 * @param word
	 * @return null if not found
	 */
	public Node search(String word) {
		Node node = this;
		for (int i = 0; i < word.length(); i++) {
			if (node.children.containsKey(word.charAt(i))) {
				node = node.get(word.charAt(i));
			}
		}
		return node == this ? null : node;
	}

	/**
	 * A method to return the words containing the character associated with
	 * this node and their frequencies
	 * 
	 * @return
	 */
	public List<TrieEntry> getWords() {
		List<TrieEntry> list = new ArrayList<TrieEntry>();

		if (isWord) {
			list.add(new TrieEntry(toString(), this.frequency));
		}

		if (!this.children.isEmpty()) {
			for (Node node : this.children.values()) {
				list.addAll(node.getWords());
			}
		}
		return list;
	}

	public boolean isWord() {
		return isWord;
	}

	public void setWord(boolean isWord) {
		this.isWord = isWord;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	@Override
	public String toString() {
		return this.parent == null ? "" : parent.toString() + String.valueOf(getValue());
	}
}
