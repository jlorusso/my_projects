package com.jimcorp.tests.GUIPart2_22;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class GridLayoutFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JButton applyButton;
	private JComboBox<Integer> hGap;
	private JComboBox<Integer> vGap;
	private JLabel hGapLabel;
	private JLabel vGapLabel;
	
	
	public GridLayoutFrame() {
		super("GridLayout Test");
		
		final JPanel northPanel = new JPanel();
		
		GridLayout northLayout = new GridLayout(0,2);
		northPanel.setLayout(northLayout);
		
		JButton[] buttons = new JButton[5];
		
		for(int i=0; i<buttons.length; i++) {
			buttons[i] = new JButton("Button " + i);
			northPanel.add(buttons[i]);
		}
		
		JPanel southPanel = new JPanel();
		GridLayout southLayout = new GridLayout(2,3);
		southPanel.setLayout(southLayout);
		
		hGapLabel = new JLabel("Horizontal Gap:");
		southPanel.add(hGapLabel);
		vGapLabel = new JLabel("Vertical Gap:");
		southPanel.add(vGapLabel);
		southPanel.add(new JLabel(""));
		
		hGap = new JComboBox<Integer>(new Integer[]{5, 10, 15, 20});
		southPanel.add(hGap);
		vGap = new JComboBox<Integer>(new Integer[]{5, 10, 15, 20});
		southPanel.add(vGap);
		
		applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				GridLayout nLayout = (GridLayout) northPanel.getLayout();
				int hGapVal = (int) hGap.getSelectedItem();
				nLayout.setHgap(hGapVal);
				
				int vGapVal = (int) vGap.getSelectedItem();
				nLayout.setVgap(vGapVal);
				
				nLayout.layoutContainer(northPanel);
				System.out.printf("hGapVal = %s%nvGapVal = %s%n", hGapVal, vGapVal);
			}
			
		});
		southPanel.add(applyButton);
		
		this.add(northPanel, BorderLayout.NORTH);
		this.add(new JSeparator(), BorderLayout.CENTER);
		this.add(southPanel, BorderLayout.SOUTH);
	}

}
