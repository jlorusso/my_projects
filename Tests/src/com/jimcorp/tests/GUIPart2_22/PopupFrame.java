package com.jimcorp.tests.GUIPart2_22;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

public class PopupFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JRadioButtonMenuItem[] items;
	private final Color[] colorValues = {Color.blue, Color.yellow, Color.red};
	private JPopupMenu popupMenu;
	
	public PopupFrame() {
		super("Using JPopupMenus");
		
		ItemHandler handler = new ItemHandler();
		String[] colors = {"Blue", "Yellow", "Red"};
		
		ButtonGroup colorGroup = new ButtonGroup();
		popupMenu = new JPopupMenu();
		items = new JRadioButtonMenuItem[colors.length];
		
		for(int i=0; i<items.length; i++) {
			items[i] = new JRadioButtonMenuItem(colors[i]);
			popupMenu.add(items[i]);
			colorGroup.add(items[i]);
			items[i].addActionListener(handler);
		}
		
		this.setBackground(Color.white);
		
		this.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent event) {
				checkForTriggerEvent(event);
			}
			
			@Override
			public void mouseReleased(MouseEvent event) {
				checkForTriggerEvent(event);
			}
			
			private void checkForTriggerEvent(MouseEvent event) {
				if(event.isPopupTrigger()) {
					popupMenu.show(event.getComponent(), event.getX(), event.getY());
				}
			}
		});
	}
	
	
	private class ItemHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			for(int i=0; i<items.length; i++) {
				if(event.getSource() == items[i]) {
					getContentPane().setBackground(colorValues[i]);
					return;
				}
			}
		}
		
	}

}
