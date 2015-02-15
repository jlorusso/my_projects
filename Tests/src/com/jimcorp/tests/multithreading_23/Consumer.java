package com.jimcorp.tests.multithreading_23;

import java.util.Random;

public class Consumer implements Runnable {

	private final static Random generator = new Random();
	private final Buffer sharedLocation;
	
	public Consumer(Buffer shared) {
		sharedLocation = shared;
	}
	
	
	@Override
	public void run() {
		
		int sum = 0;
		
		for(int i=1; i<=10; i++) {
			try {
				Thread.sleep(generator.nextInt(3000));
				sum += sharedLocation.get();
//				System.out.printf("\t\t\t%2d%n", sum);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	
		System.out.printf("%nConsumer read values totaling %d%nTerminating Consumer%n", sum);
	}

}
