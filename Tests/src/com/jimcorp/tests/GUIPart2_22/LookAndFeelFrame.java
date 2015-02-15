package com.jimcorp.tests.GUIPart2_22;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class LookAndFeelFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private UIManager.LookAndFeelInfo[] looks;
	private String[] lookNames;
	private JRadioButton[] radio;
	private ButtonGroup group;
	private JButton button;
	private JLabel label;
	private JComboBox<String> comboBox;
	
	public LookAndFeelFrame() {
		super("Look and Feel Demo");
		
		looks = UIManager.getInstalledLookAndFeels();
		lookNames = new String[looks.length];
		
		for(int i=0; i<looks.length; i++) {
			lookNames[i] = looks[i].getName();
		}
		
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(3, 1, 0, 5));
		
		label = new JLabel("This is a " + lookNames[0] + " look and feel.", SwingConstants.CENTER);
		northPanel.add(label);
		
		button = new JButton("JButton");
		northPanel.add(button);
		
		comboBox = new JComboBox<String>(lookNames);
		northPanel.add(comboBox);
		
		radio = new JRadioButton[looks.length];
		
		JPanel southPanel = new JPanel();
		int rows = (int) Math.ceil(radio.length / 3.0);
		southPanel.setLayout(new GridLayout(rows, 3));

		group = new ButtonGroup();
		ItemHandler handler = new ItemHandler();
		
		for(int i=0; i<radio.length; i++) {
			radio[i] = new JRadioButton(lookNames[i]);
			radio[i].addItemListener(handler);
			group.add(radio[i]);
			southPanel.add(radio[i]);
		}
		
		this.add(northPanel, BorderLayout.NORTH);
		this.add(southPanel, BorderLayout.SOUTH);
		
		radio[0].setSelected(true);
	}
	
	
	private void changeTheLookAndFeel(int value) {
		
		try {
			UIManager.setLookAndFeel(looks[value].getClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception exception){
			exception.printStackTrace();
		}
	}

	private class ItemHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent event) {
			
			for(int i=0; i<radio.length; i++) {
				if(radio[i].isSelected()) {
					label.setText(String.format("This is a %s look-and-feel.", lookNames[i]));
					comboBox.setSelectedIndex(i);
					changeTheLookAndFeel(i);
				}
			}
			
		}
		
	}
}
