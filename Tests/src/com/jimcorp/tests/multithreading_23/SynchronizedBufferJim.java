package com.jimcorp.tests.multithreading_23;

public class SynchronizedBufferJim implements Buffer {

	private int buffer = -1;
	private boolean occupied = false;
	private Object lock = new Object();
	
	@Override
	public void set(int value) throws InterruptedException {
		
		synchronized(lock) {
			while(occupied) {
				System.out.println("Producer tries to write.");
				displayState("Buffer full. Producer waits.");
				lock.wait();
			}
			
			buffer = value;
			occupied = true;
			displayState("Producer writes " +buffer);
			
			lock.notifyAll();
		}
	}

	
	@Override
	public int get() throws InterruptedException {
		
		int val = 0;
		
		synchronized(lock) {
			while(!occupied) {
				System.out.println("Consumer tries to read.");
				displayState("Buffer empty. Consumer waits.");
				lock.wait();
			}
			
			val = buffer;
			displayState("Consumer reads " +buffer);
			buffer = -1;
			occupied = false;
			
			lock.notifyAll();
		}
		
		return val;
	}
	
	
	private void displayState(String operation) {

		System.out.printf("%-40s%d\t\t%b%n%n", operation, buffer, occupied);
	}
	

}
