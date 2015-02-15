package com.jimcorp.checkers;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class CheckerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Image image;
	private boolean isKinged;
	
	public CheckerPanel(Image aImage) {
		this.setOpaque(false);
		this.image = aImage;
	}
	
	
	public void addCheckerMovedListener(MouseInputAdapter handler) {
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);
	}

	
	public boolean isKinged() {
		return isKinged;
	}
	
	public void setKinged(boolean isKinged) {
		this.isKinged = isKinged;
	}
	
	
	public Point getLocationInGrid() {
		
		CheckerSpacePanel space  = (CheckerSpacePanel) getParent();
		return space.getLocationInGrid();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this.getParent());
		
		if(isKinged()) {
			Font font = new Font("SansSerif,", Font.BOLD, 24);
			g.setColor(Color.WHITE);
			g.setFont(font);
			FontMetrics fontMetrics = g.getFontMetrics();
			String kingStr = "King";
			int x = (getWidth() - fontMetrics.stringWidth(kingStr)) / 2;
			int y = (fontMetrics.getAscent() + (getHeight() - (fontMetrics.getAscent() + fontMetrics.getDescent())) / 2);
			g.drawString(kingStr, x, y);
		}
	}

}
