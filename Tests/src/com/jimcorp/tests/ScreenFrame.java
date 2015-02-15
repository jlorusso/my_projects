package com.jimcorp.tests;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScreenFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private CardLayout layout;
	private JPanel screens;
	private JPanel contentPane;
	
	public ScreenFrame() {
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		screens = new JPanel();
		layout = new CardLayout();
		screens.setLayout(layout);
		contentPane.add(screens);
	}
	
	
	public void addScreen(JPanel screen, String name) {
		layout.addLayoutComponent(screen, name);
	}
	
	public void nextScreen() {
		layout.next(screens);
		doLayout();
	}
}
