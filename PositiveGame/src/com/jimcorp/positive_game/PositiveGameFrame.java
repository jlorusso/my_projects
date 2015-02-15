package com.jimcorp.positive_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;

public class PositiveGameFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private int num1;
	private int num2;
	private JLabel resultLbl;
	private JButton submitBtn;
	private JLabel num1Lbl;
	private JLabel num2Lbl;
	
	public PositiveGameFrame() {
		
		super("Easy math game");
		setLayout(new GridLayout(2,1));
		
		JPanel topPanel = new JPanel();
		FlowLayout layout = new FlowLayout();
		topPanel.setLayout(layout);
		
		num1 = getNumber();
		num1Lbl = new JLabel(String.valueOf(num1));
		
		num2 = getNumber();
		num2Lbl = new JLabel(String.valueOf(num2));
		
		JLabel plusLbl = new JLabel("+");
		JLabel equalsLbl = new JLabel("=");
		
		JTextField answerField = new JTextField();
		answerField.setPreferredSize(new Dimension(40, 20));
		answerField.addMouseListener(new MouseInputAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				resultLbl.setText("");
				num1Lbl.setText(String.valueOf(getNumber()));
				num2Lbl.setText(String.valueOf(getNumber()));
			}
			
		});
		
		resultLbl = new JLabel();
		submitBtn = new JButton("Check Answer");
		submitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				resultLbl.setText("CORRECT!!!! You're so good at math!!!!!");
				resultLbl.setForeground(Color.PINK);
				resultLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
				resultLbl.getParent().doLayout();
				resultLbl.repaint();
			}
			
		});
		
		topPanel.add(num1Lbl);
		topPanel.add(plusLbl);
		topPanel.add(num2Lbl);
		topPanel.add(equalsLbl);
		topPanel.add(answerField);
		topPanel.add(submitBtn);
		add(topPanel);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(layout);

		bottomPanel.add(resultLbl);
		add(bottomPanel);
	}
	
	public int getNumber() {
		Random gen = new Random();
		return gen.nextInt(99);
	}
	
	
	public static void main(String[] args) {
		PositiveGameFrame frame = new PositiveGameFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 300);
		frame.setVisible(true);
	}
}
