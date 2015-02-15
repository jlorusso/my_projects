package com.jimcorp.checkers;

import java.awt.CardLayout;

import javax.swing.JPanel;

public class ScreenPanels extends JPanel {

	private static final long serialVersionUID = 1L;
	private CardLayout layout;

	public ScreenPanels() {
		layout = new CardLayout();
		setLayout(layout);
	}
	
	public void nextPanel() {
		layout.next(this);
		doLayout();
	}
	
	public void showPanel(String panelName) {
		layout.show(this, panelName);
		doLayout();
	}
	
	public void addPanel(JPanel panel, String panelName) {
		add(panel);
		layout.addLayoutComponent(panel, panelName);
	}

}
