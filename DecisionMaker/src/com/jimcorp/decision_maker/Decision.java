package com.jimcorp.decision_maker;

import java.util.Random;

public class Decision {

	private boolean decision = false;
	
	public Decision() {
		
		setDecision();
		
	}
	
	
	public boolean getDecision() {
		return this.decision;
	}
	
	
	public String getStringDecision() {
		if(decision == true)
			return "YES";
		else
			return "NO";
	}
	
	
	private void setDecision() {
		Random rand = new Random();
		int num = rand.nextInt(2);
		if(num == 1) {
			decision = true;
		}
	}
}
