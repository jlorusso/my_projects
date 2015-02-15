package com.jimcorp.checkers;

import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GameMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	private JMenu fileMenu;
	private JMenu optionsMenu;
	private JMenu helpMenu;
	
	private JMenuItem newGameItem;
	private JMenuItem exitItem;
	private JMenuItem aboutItem;
	private JCheckBoxMenuItem showMovesItem;

	public GameMenuBar() {
		fileMenu = new JMenu("File");
		this.add(fileMenu);
		newGameItem = new JMenuItem("New game");
		fileMenu.add(newGameItem);
		exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);
		
		optionsMenu = new JMenu("Options");
		this.add(optionsMenu);
		showMovesItem = new JCheckBoxMenuItem("Show legal moves");
		optionsMenu.add(showMovesItem);
		
		helpMenu = new JMenu("Help");
		this.add(helpMenu);
		aboutItem = new JMenuItem("About");
		helpMenu.add(aboutItem);
	}
	
	public JMenu getFileMenu() {
		return this.fileMenu;
	}
	
	public JMenu getOptionsMenu() {
		return this.optionsMenu;
	}
	
	public JMenu getHelpMenu() {
		return this.helpMenu;
	}
	
	public JMenuItem getNewGameItem() {
		return this.newGameItem;
	}
	
	public JMenuItem getExitItem() {
		return this.exitItem;
	}
	
	public JMenuItem getAboutItem() {
		return this.aboutItem;
	}
	
	public JMenuItem getShowMovesItem() {
		return this.showMovesItem;
	}
	
	public void addNewGameListener(ActionListener listener) {
		newGameItem.addActionListener(listener);
	}
	
	public void addExitListener(ActionListener listener) {
		exitItem.addActionListener(listener);
	}
	
	public void addShowMovesListener(ActionListener listener) {
		showMovesItem.addActionListener(listener);
	}
	
	public void addAboutListener(ActionListener listener) {
		aboutItem.addActionListener(listener);
	}
}
