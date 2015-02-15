package com.jimcorp.tests;

public class Toy {

	private final int weight;
	private final int price;
	
	public Toy(int toyWeight, int toyPrice) {
		
		this.weight = toyWeight;
		this.price = toyPrice;
		
	}

	public int getWeight() {
		return weight;
	}

	public int getPrice() {
		return price;
	}
	
	
	@Override
	public String toString() {
		return String.format("Toy[%s lbs, $%s]", weight, price);
	}
}
