package com.jimcorp.tests.GUIPart2_22;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class OvalPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int diameter = 10;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.fillOval(10, 10, diameter, diameter);
	}
	
	public void setDiameter(int diameter) {
		this.diameter = (diameter >= 0 ? diameter : 10);
		repaint();
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}
	
	@Override
	public Dimension getMinimumSize() {
		return this.getPreferredSize();
	}
}
