package com.jimcorp.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Deadlock {

	private static List<String> list;
//	private static boolean write = false;
	private static final Random gen = new Random();
	
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		list = new ArrayList<String>();
		list.add("Apple");
		list.add("Pear");
		list.add("Banana");
		
		executor.execute(new Reader());
		executor.execute(new Writer());
		executor.shutdown();
	}
	
	
	public Deadlock() {
		list = new ArrayList<String>();
	}
	

	private static class Reader implements Runnable {

		@Override
		public void run() {
			try {
				for(int i=0; i<5; i++) {
					getStringFromList();
					displayList("READ");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private static class Writer implements Runnable {

		@Override
		public void run() {
			
			String[] strings = {"Grapes", "Watermelon", "Panda", "Hat", "Mango"};

			try{ 
				for(int i=0; i<5; i++) {
					addStringToList(strings[i]);
					displayList("WRITE");
				} 
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
				
		}
		
	}
	
	
	public synchronized static String getStringFromList() throws InterruptedException {
		Thread.sleep(2000);
//		while(!write) {}
//		write = false;
		return list.get(gen.nextInt(list.size()-1));
	}
	
	public synchronized static void addStringToList(String string) throws InterruptedException {
		Thread.sleep(2000);
		list.add(string);
//		write = true;
	}
	
	public static void displayList(String prefix) {
		System.out.println(prefix + ": " + list);
	}
}
