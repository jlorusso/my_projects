package com.jimcorp.tests.multithreading_23;

public interface Buffer {

	public void set(int value) throws InterruptedException;
	public int get() throws InterruptedException;
	
}
