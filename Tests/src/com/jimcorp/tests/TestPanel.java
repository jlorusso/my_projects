package com.jimcorp.tests;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TestPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton btn;
	public TestPanel() {
		
		setBackground(Color.RED);
		btn = new JButton("Ok");
		add(btn);
	}
	
}
