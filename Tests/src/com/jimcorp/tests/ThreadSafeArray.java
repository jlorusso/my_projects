package com.jimcorp.tests;

public class ThreadSafeArray {

	private String[] array;
	private boolean lock;
	
	public ThreadSafeArray(int size) {
		
		array = new String[size];
		lock = false;
	}
	
	
	public String get(int index) throws InterruptedException {
		
		String string = null;
		
		if(index >= 0 && index <= array.length) {
			while(lock) {
				wait();
				lock = true;
				string = array[index];
				lock = false;
				
			}
		}
		
		return string;
	}
	
	public void set(String val, int index) throws ArrayIndexOutOfBoundsException {
		
		if(index >= 0 && index <= array.length) {
			array[index] = val;
		}
		else {
			throw new ArrayIndexOutOfBoundsException("Illegal index " + index + ".");
		}
		
	}
	
	
	public int getSize() {
		return array.length;
	}
}
