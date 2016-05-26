package com.jimcorp.tests;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SantaService {

	public static void main(String[] args) {
		
		ToyQueue toyQueue = new ToyQueue();
		
		Elf larry = new Elf("Larry", 10, 5, 20, toyQueue);
		Elf curly = new Elf("Curly", 6, 4, 6, toyQueue);
		Elf moe = new Elf("Moe", 5, 6, 10, toyQueue);
		SantaSleigh santaSleigh = new SantaSleigh(toyQueue);
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		// This section is unnecessary, but illustrates how a Callable can be used instead of a Runnable
		Future<Toy> larryFuture = executor.submit((Callable<Toy>)larry);
		try {
			Toy toy = larryFuture.get();
			System.out.println("\tCreated toy " + toy);
		} catch (InterruptedException | ExecutionException e1) {
			e1.printStackTrace();
		}
		
		executor.execute(curly);
		executor.execute(moe);
		executor.execute(santaSleigh);
		
		larryFuture.cancel(true);
		if(larryFuture.isDone())
			executor.execute(larry);
		
		
		executor.shutdown();
	}

}
