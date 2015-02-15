package com.jimcorp.tests.applets_20;

import java.awt.Graphics;

import javax.swing.JApplet;
import javax.swing.JOptionPane;

public class AdditionApplet extends JApplet {

	private static final long serialVersionUID = 1L;
	private Double sum;
	private Double num1;
	private Double num2;
	
	@Override
	public void start() {
		System.out.println("Document base = " + this.getDocumentBase());
		System.out.println("Code base = " + this.getCodeBase());
		
		num1 = getNumber();
		num2 = getNumber();
		
		sum = num1 + num2;
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawRect(15, 10, 270, 20);
		g.drawString(num1 + " + " + num2 + " = " + sum, 25, 25);
	}
	
	
	public Double getNumber() {
		String numberString = null;
		Double num = null;
		while(num == null) {
			try {
				numberString = JOptionPane.showInputDialog("Enter a number:");
				if(numberString == null) { numberString = "0"; }
				num = Double.parseDouble(numberString);
				System.out.println(num);
			} catch (NumberFormatException numFormatException) {
				JOptionPane.showMessageDialog(this, "'" + numberString + "' is not a valid number!"
						+ "\nPlease enter a valid number and try again.", "ERROR!!!", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		return num;
	}
}
