package com.jimcorp.checkers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import javax.swing.JPanel;

public class CheckerSpacePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private CheckerPanel checker;
	private Color color;
	private Point locationInGrid;

	public CheckerSpacePanel(Color aColor, Point locationInGrid/*CheckerSpaceModel checkerSpaceModel*/) {
		this.locationInGrid = locationInGrid;
		this.color = aColor;
		this.setBackground(color);
		setLayout(new BorderLayout());
	}
	
	
	public Point getLocationInGrid() {
		return locationInGrid;
	}
	

	@Override
	public Component add(Component component) throws NullPointerException {
		if(component instanceof CheckerPanel) {
			super.add(component, BorderLayout.CENTER);
			setChecker((CheckerPanel) component);
			repaint();
		}
		else {
			throw new NullPointerException(String.format("Component '%s' is not an instance of 'CheckerPanel'.", 
					component.getClass().getName()));
		}
		
		return component;
	}
	
	
	public void removeChecker() {
		remove(checker);
		setChecker(null);
		repaint();
	}
	

	/**
	 * @return the checker
	 */
	public CheckerPanel getChecker() {
		return checker;
	}

	/**
	 * @param checker the checker to set
	 */
	private void setChecker(CheckerPanel checker) {
		this.checker = checker;
	}
	
	
//	protected CheckerSpaceModel getModel() {
//		return this.checkerSpaceModel;
//	}
	
	
	protected Color getColor() {
		return color;
	}
}
