package com.jimcorp.tests;

import java.util.concurrent.LinkedBlockingDeque;

public class ToyQueue extends LinkedBlockingDeque<Toy> {

	private static final long serialVersionUID = 1L;

	public int getWeightInQueue() {
		
		int totalWeight = 0;
		synchronized(this) {
			for(Toy toy : this) {
				totalWeight += toy.getWeight();
			}
			notifyAll();
		}
		
		return totalWeight;
	}
}
