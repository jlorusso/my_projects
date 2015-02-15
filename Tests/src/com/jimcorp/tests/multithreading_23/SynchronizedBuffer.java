package com.jimcorp.tests.multithreading_23;

public class SynchronizedBuffer implements Buffer {

	private int buffer = -1;
	private boolean occupied = false;
	
	@Override
	public synchronized void set(int value) throws InterruptedException {
		
		while(occupied) {
			System.out.println("Producer tries to write.");
			displayState("Buffer full. Producer waits.");
			wait();
		}
		
		buffer = value;
		occupied = true;
		displayState("Producer writes " +buffer);
		
		notifyAll();
	}

	
	@Override
	public synchronized int get() throws InterruptedException {
		
		while(!occupied) {
			System.out.println("Consumer tries to read.");
			displayState("Buffer empty. Consumer waits.");
			wait();
		}
		
		int val = buffer;
		displayState("Consumer reads " +buffer);
		buffer = -1;
		occupied = false;
		
		notifyAll();
		
		return val;
	}
	
	
	private void displayState(String operation) {

		System.out.printf("%-40s%d\t\t%b%n%n", operation, buffer, occupied);
	}
	

}
