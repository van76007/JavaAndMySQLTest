package com.algo.twoSum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.algo.twoSum.Pair;

/**
 * A class to implement Two Sum algorithm
 * Time complexity O(n)
 * Space complexity O(n)
 * Source: E-book "Coding interview - Program Creek" 
 * @author dvanvu
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	int[] numbers = {3,1,3,3,4,5,6};
		
		List<Pair> result = findPairs(numbers, 6);
		for (Pair pair : result) {
			System.out.println(String.format("[%d, %d]", pair.getFirstIndex(), pair.getSecondIndex()));
		}
    }
    
    /**
     * A method to implement Two Sum algorithm
     * @param numbers Array of numbers
     * @param sum Expected sum
     * @return All pairs of array indexes which sum of the elements is equal to the expected sum
     *         Note: The index starts from 1 instead of 0
     */
    public static List<Pair> findPairs(int[] numbers, int sum) {
		List<Pair> result = new ArrayList<Pair>();
		
		/*
		 * The original idea is to use 1 hashmap for fast lookup the remaining element of the pair
		 * However, I use 2 hashmaps to cover the case the array contains duplicated elements who sum is equal to the expected result
		 * It would give us more pairs
		 * Because I was wondering what if we insert value that the map already contains the key mapping
		 */
	    HashMap<Integer, Integer> indexes = new HashMap<Integer, Integer>();
	    HashMap<Integer, Integer> anotherIndexes = new HashMap<Integer, Integer>();
	         
	    for(int i = 0; i < numbers.length; ++i) {
	    	if (!indexes.containsKey(numbers[i]))
	    		indexes.put(numbers[i], i);
	    	else
	    		anotherIndexes.put(numbers[i], i);
	    }
	             
	    for(int i = 0; i < numbers.length; ++i) {
	    	
	    	/**
	    	 * I add the check the first index should be less than the second index to filter the duplicated pairs
	    	 * e.g. if we found the pair [1,3] we no need to return the pair [3,1]
	    	 */
	    	if(indexes.containsKey(sum - numbers[i]) && i < indexes.get(sum - numbers[i])) {
	            result.add(new Pair(i + 1, indexes.get(sum - numbers[i]) + 1));
	        }
	    	
	    	if(anotherIndexes.containsKey(sum - numbers[i]) && i < anotherIndexes.get(sum - numbers[i])) {
	            result.add(new Pair(i + 1, anotherIndexes.get(sum - numbers[i]) + 1));
	        }
	     }
	    
	    return result;
	}
}
