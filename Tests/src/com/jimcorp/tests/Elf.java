package com.jimcorp.tests;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class Elf implements Runnable, Callable<Toy> {

	private final String name;
	private final int toyWeight;
	private final int toyPrice;
	private final int rate;
	private final BlockingQueue<Toy> toyQueue;
	
	public Elf(String name, int toyWeight, int toyPrice, int rate, BlockingQueue<Toy> toyQueue) {
		
		this.name = name;
		this.toyWeight = toyWeight;
		this.toyPrice = toyPrice;
		this.rate = rate;
		this.toyQueue = toyQueue;
		
	}

	@Override
	public void run() {
		
		while(true) {
			makeToy();
		}
	}
	
	
	@Override
	public Toy call() throws Exception {
		return makeToy();
	}
	
	
	private Toy makeToy() {
		
		Toy toy = null;
		
		try {
			toy = new Toy(toyWeight, toyPrice);
			
			synchronized(toyQueue) {
				toyQueue.put(toy);
				int totalWeight = getWeightInQueue();
				System.out.printf("%s added a %s pound toy to the queue. Total[%s]%n", name, 
						toy.getWeight(), totalWeight);
			toyQueue.notifyAll();
			}
			Thread.sleep(rate*100);
		} catch(InterruptedException e) {
			System.err.println("ERROR!!! " + name + " the elf was interrupted!!!");
		}
		
		return toy;
	}
	
	
	private int getWeightInQueue() {
		
		int totalWeight = 0;
		synchronized(this) {
			for(Toy toy : toyQueue) {
				totalWeight += toy.getWeight();
			}
			notifyAll();
		}
		
		
		return totalWeight;
	}
	
	
	public String getName() {
		return name;
	}

	public int getToyWeight() {
		return toyWeight;
	}

	public int getToyPrice() {
		return toyPrice;
	}

	public int getRate() {
		return rate;
	}

	public BlockingQueue<Toy> getToyQueue() {
		return toyQueue;
	}


	
}
