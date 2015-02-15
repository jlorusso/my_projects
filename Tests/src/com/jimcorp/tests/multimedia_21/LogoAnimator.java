package com.jimcorp.tests.multimedia_21;

import javax.swing.JApplet;
import javax.swing.JFrame;

public class LogoAnimator extends JApplet {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		LogoAnimatorJPanel animation = new LogoAnimatorJPanel();
		
		JFrame window = new JFrame("Animator Test");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(animation);
		
		window.pack();
		window.setVisible(true);
		
		animation.startAnimation();
	}

}
