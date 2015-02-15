package com.jimcorp.tests.multithreading_23;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedBufferTest2 {

	public static void main(String[] args) {

		Buffer buffer = new SynchronizedBuffer();
		
		System.out.printf("%-40s%s\t\t%s\n%-40s%s%n%n", "Operation", "Buffer", "Occupied", "---------", "------\t\t--------");
		
		ExecutorService app = Executors.newCachedThreadPool();
		app.execute(new Producer(buffer));
		app.execute(new Consumer(buffer));
		
		app.shutdown();

	}

}
