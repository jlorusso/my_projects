package com.jimcorp.decision_maker;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		
		DecisionFrame application = new DecisionFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.setSize(500, 250);
		application.setVisible(true);
	}

}
