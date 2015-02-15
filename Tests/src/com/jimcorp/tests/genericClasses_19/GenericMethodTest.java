package com.jimcorp.tests.genericClasses_19;

public class GenericMethodTest {

	public static void main(String[] args) {
		Integer[] intArray = {1, 2, 3, 4, 5};
		Double[] doubleArray = {1.1, 2.2, 3.3, 4.4, 5.5};
		Character[] charArray = {'H', 'E', 'L', 'L', 'O'};
		
		System.out.println("Array intArray contains:");
		printArray(intArray);
		System.out.println("Array doubleArray contains:");
		printArray(doubleArray);
		System.out.println("Array charArray contains:");
		printArray(charArray);
	}
	
	public static <T> void printArray(T[] array) {
		for(T elem : array) {
			System.out.print(elem + " ");
		}
		System.out.println();
	}

}
