package com.jimcorp.tests;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Deadlock2 {

	private static String s1 = "Hello";
	private static String s2 = "World";
	
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(new Class1());
		executor.execute(new Class2());
		executor.shutdown();
	}

	
	private static class Class1 implements Runnable {
		
		@Override
		public void run() {
			try {
				while(true) {
					Thread.sleep(2000);
					synchronized(s1) {
						synchronized(s2) {
							System.out.println(s1+s2);
						}
					}
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	private static class Class2 implements Runnable {
		
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(2000);
					synchronized(s2) {
						synchronized(s1) {
							System.out.println(s1+s2);
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
