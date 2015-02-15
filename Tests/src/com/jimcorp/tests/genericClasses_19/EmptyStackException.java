package com.jimcorp.tests.genericClasses_19;

public class EmptyStackException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmptyStackException() {
		this("Stack is empty");
	}

	public EmptyStackException(String message) {
		super(message);
	}

}
