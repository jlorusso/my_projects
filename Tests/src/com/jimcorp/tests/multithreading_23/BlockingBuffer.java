package com.jimcorp.tests.multithreading_23;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingBuffer implements Buffer {

	private final ArrayBlockingQueue<Integer> buffer;
	
	public BlockingBuffer() {
		buffer = new ArrayBlockingQueue<Integer>(5);
	}
	
	@Override
	public void set(int value) throws InterruptedException {
		buffer.put(value);
		System.out.printf("Producer writes %2d\tBuffer cells occupied: %d%n", value, buffer.size());
	}

	@Override
	public int get() throws InterruptedException {
		int value = buffer.take();
		System.out.printf("Consumer reads %2d\tBuffer cells occupied: %d%n", value, buffer.size());
		return value;
	}

}
