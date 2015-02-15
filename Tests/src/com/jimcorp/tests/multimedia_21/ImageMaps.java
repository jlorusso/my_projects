package com.jimcorp.tests.multimedia_21;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JApplet;

public class ImageMaps extends JApplet {

	private static final long serialVersionUID = 1L;
	
	private ImageIcon mapImage;
	private static final String[] captions = {"Common programming error", "Good Programming Practice",
		"Look-and-Feel Observation", "Performance Tip", "Portability Tip", "Software Engineering Observation",
		"Error-Prevention Tip"};

	public void init() {
		addMouseListener( new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent event) {
				showStatus("Pointer Outside Applet");
			}
		});
		
		addMouseMotionListener( new MouseMotionAdapter() {
			
			@Override
			public void mouseMoved(MouseEvent event) {
				showStatus(translateLocation(event.getX(), event.getY()));
			}
		});
		
		mapImage = new ImageIcon("icons.png");
	}

	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		mapImage.paintIcon(this, g, 0, 0);
	}
	
	
	public String translateLocation(int x, int y) {
		if(x >= mapImage.getIconWidth() || y >= mapImage.getIconHeight()) {
			return "";
		}
		
		double iconWidth = (double) mapImage.getIconWidth() / 7.0;
		int iconNumber = (int) ((double) x / iconWidth);
		
		return captions[iconNumber];
	}
}
