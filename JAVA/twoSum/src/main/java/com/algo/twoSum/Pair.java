package com.algo.twoSum;

/**
 * An object model for pair of indices
 * @author dvanvu
 *
 */
public class Pair {
	private final int firstIndex;
	private final int secondIndex;

	public Pair(int firstIndex, int secondIndex) {
		this.firstIndex = firstIndex;
		this.secondIndex = secondIndex;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public int getSecondIndex() {
		return secondIndex;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		return (secondIndex == other.secondIndex && firstIndex == other.firstIndex);
	}
}
