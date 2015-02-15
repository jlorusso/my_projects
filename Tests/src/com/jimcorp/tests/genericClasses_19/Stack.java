package com.jimcorp.tests.genericClasses_19;

import java.util.ArrayList;

public class Stack<T> {

	private ArrayList<T> elements;
	
	public Stack() {
		this(10);
	}
	
	public Stack(int capacity) {
		int initCapacity = capacity > 0 ? capacity : 10;
		elements = new ArrayList<T>(initCapacity);
	}
	
	public void push(T pushValue) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
		elements.add(pushValue);
	}
	
	public T pop() {
		if(elements.isEmpty())
			throw new EmptyStackException("Stack is empty. Cannot pop.");
		return elements.remove(elements.size() - 1);
	}
}
