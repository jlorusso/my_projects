package com.jimcorp.tests;

public class FizzBuzz {

	public static void main(String[] args) {
		
		Integer nums = 100;
		
		for(Integer i=1; i<=nums; i++) {
			String message = "";
			if((i % 3) == 0) {
				message = "Fizz";
			}
			if((i%5) == 0) {
				message = message + "Buzz";
			}
			if(message.isEmpty()) {
				message = i.toString();
			}
			
			System.out.println(message);
		}

	}

}
