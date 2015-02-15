package com.jimcorp.tests.multithreading_23;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingBufferTest {

	public static void main(String[] args) {
		
		ExecutorService app = Executors.newCachedThreadPool();

		Buffer blockingBuffer = new BlockingBuffer();
		
		app.execute(new Producer(blockingBuffer));
		app.execute(new Consumer(blockingBuffer));
		
		app.shutdown();
	}

}
