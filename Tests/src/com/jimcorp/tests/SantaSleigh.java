package com.jimcorp.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class SantaSleigh implements Runnable {

	private static final int RECOUP_TIME = 300;
	private static final int MAX_WEIGHT = 500;
	private final BlockingQueue<Toy> toyQueue;
	private int currentWeight = 0;
	private List<Toy> sleighLoad;
	private List<List<Toy>> toyDeliveries; 
	
	public SantaSleigh(BlockingQueue<Toy> toyQueue) {
		this.toyQueue = toyQueue;
		sleighLoad = new ArrayList<Toy>(50);
		toyDeliveries = new ArrayList<List<Toy>>();
	}
	
	@Override
	public void run() {
		
		while(true) {
			try {
				while(currentWeight < MAX_WEIGHT) {
					Toy toy = toyQueue.peek();
					if(toy != null) {
						if((toy.getWeight() + currentWeight) < MAX_WEIGHT) {
							synchronized (toyQueue) {
								toy = toyQueue.take();
								System.out.printf("Santa takes a %s lb toy.%n", toy.getWeight());
								sleighLoad.add(toy);
								currentWeight += toy.getWeight();
								toyQueue.notifyAll();
							}
						}
						else {
							break;
						}
					}
				}
				
				toyDeliveries.add(sleighLoad);
				System.out.printf("<DELIVERY> Santa loaded %s toys totaling %s pounds.%n", sleighLoad.size(), currentWeight);
				currentWeight = 0;
				sleighLoad.clear();
				Thread.sleep(RECOUP_TIME*100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
