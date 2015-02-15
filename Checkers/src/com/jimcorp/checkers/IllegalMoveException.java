package com.jimcorp.checkers;

public class IllegalMoveException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public IllegalMoveException() {
		this("");
	}
	
	public IllegalMoveException(String message) {
		super(message);
	}

}
