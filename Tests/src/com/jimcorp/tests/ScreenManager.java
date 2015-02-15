package com.jimcorp.tests;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ScreenManager {

	private ScreenFrame frame;
	
	public ScreenManager() {
		frame = new ScreenFrame();
		TestPanel screen1 = new TestPanel();
		JPanel screen2 = new JPanel();
		screen2.setBackground(Color.BLUE);
		screen2.add(new JTextField("Type some text"));
		
		frame.addScreen(screen1, "screen1");
		frame.addScreen(screen2, "screen2");
	}
	
	public void changeScreen() {
		
	}
	
	public void createAndShowGUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 600);
		frame.setVisible(true);
	}
	
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ScreenManager manager = new ScreenManager();
				manager.createAndShowGUI();
			}
			
		});
	}
}
