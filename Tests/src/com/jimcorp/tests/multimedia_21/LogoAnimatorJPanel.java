package com.jimcorp.tests.multimedia_21;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jnlp.FileContents;
import javax.jnlp.FileOpenService;
import javax.jnlp.ServiceManager;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LogoAnimatorJPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected ImageIcon images[];
	private int currentImage = 0;
	private final int ANIMATION_DELAY = 50;
	private int width;
	private int height;
	private Timer animationTimer;
	
	public LogoAnimatorJPanel() {
		try {
			FileOpenService fileOpenService = (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");
			FileContents[] contents = fileOpenService.openMultiFileDialog(null, null);
			images = new ImageIcon[contents.length];
			
			for(int i=0; i<images.length; i++) {
				byte[] imageData = new byte[(int) contents[i].getLength()];
				contents[i].getInputStream().read(imageData);
				images[i] = new ImageIcon(imageData);
			}
			
			width = images[0].getIconWidth();
			height = images[0].getIconHeight();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "ERROR!!!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		images[currentImage].paintIcon(this, g, 0, 0);
		if(animationTimer.isRunning()) {
			currentImage = (currentImage +1) % images.length;
		}
	}
	
	
	public void startAnimation() {
		if(animationTimer == null) {
			currentImage = 0;
			animationTimer = new Timer(ANIMATION_DELAY, new TimerHandler());
			animationTimer.start();
		}
		else {
			if(! animationTimer.isRunning()) {
				animationTimer.restart();
			}
		}
	}
	
	
	public void stopAnimation() {
		animationTimer.stop();
	}
	
	
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}
	
	
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
	
	
	private class TimerHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			repaint();
		}
		
	}
}
