package com.jimcorp.tests.multithreading_23;

public class UnsynchronizedBuffer implements Buffer {

	private int buffer = -1;
	
	@Override
	public void set(int value) throws InterruptedException {
		System.out.printf("Producer writes\t%2d", value);
		buffer = value;
	}

	@Override
	public int get() throws InterruptedException {
		System.out.printf("Consumer reads\t%2d", buffer);
		return buffer;
	}

}
