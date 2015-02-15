package com.jimcorp.tests.genericClasses_19;

public class MaximumTest {

	public static void main(String[] args) {
		
		System.out.printf("Maximum of %d, %d and %d is %d%n%n", 3, 4, 5, maximum(3, 4, 5));
		System.out.printf("Maximum of %.1f, %.1f and %.1f is %.1f%n%n", 6.6, 8.8, 7.7, maximum(6.6, 8.8, 7.7));
		System.out.printf("Maximum of %s, %s and %s is %s%n%n", 
				"pear", "apple", "orange", maximum("pear", "apple", "orange"));

	}
	
	
	@SafeVarargs
	public static <T extends Comparable<T>> T maximum(T ...ts) {
		T max = null;
		for(T elem : ts ) {
			if(max == null)
				max = elem;
			else if(elem.compareTo(max) > 0)
				max = elem;
		}
		
		return max;
	}

}
