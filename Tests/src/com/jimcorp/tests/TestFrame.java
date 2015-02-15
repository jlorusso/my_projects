package com.jimcorp.tests;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public TestFrame() {
		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		
		TestPanel piece = new TestPanel();
		piece.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent event) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent event) {
				
				TestPanel panel = (TestPanel) event.getSource();
				Point point = panel.getParent().getMousePosition();
				Dimension panelSize = panel.getSize();
				Point newPoint = new Point(point.x - (panelSize.width/2), point.y - (panelSize.height/2));
				panel.setLocation(newPoint);
				
			}
		});
		panel.add(piece);
		
		add(panel);
		
		
	}
	
	public static void main(String args[]) {
		TestFrame frame = new TestFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 500);
		frame.setVisible(true);
	}
}
