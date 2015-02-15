package com.jimcorp.tests.multithreading_23;

import java.util.Arrays;
import java.util.Random;

public class SimpleArray { // CAUTION: NOT THREAD SAFE!

	private final int[] array;
	private int writeIndex = 0;
	private final static Random generator = new Random();
	
	public SimpleArray(int size) {
		array = new int[size];
	}
	
	
	synchronized public void add(int value) {
		int position = writeIndex;
		
		try {
			Thread.sleep(generator.nextInt(500));
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		array[position] = value;
		System.out.printf("%s wrote %2d to element %d.%n", Thread.currentThread().getName(), value, position);
		++writeIndex;
		System.out.printf("Next write to index: %d%n", writeIndex);
	}
	
	
	@Override
	public String toString() {
		return "\nContents of SimpleArray:\n" + Arrays.toString(array);
	}
}
