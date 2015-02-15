package com.jimcorp.tests.applets_20;

import java.awt.Graphics;
import javax.swing.JApplet;

public class WelcomeApplet extends JApplet {

	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawString("Welcome to Java Programming!", 25, 25);
	}
	
}
