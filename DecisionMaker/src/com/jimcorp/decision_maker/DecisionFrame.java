package com.jimcorp.decision_maker;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class DecisionFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton decideButton;
	private JTextField questionTextField;
	private JTextField answerTextField;
	private JLabel questionLabel;
	private static final String questionTip = "Type question here";
	
	
	public DecisionFrame() {
		super("Decision Maker 1.0");
		GridLayout layout = new GridLayout(2, 2);
		this.setLayout(layout);
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Windows".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
			// Leave default look and feel
		}
		
		questionLabel = new JLabel("What are you having trouble deciding on this time?");
		questionLabel.setBackground(Color.WHITE);
		questionLabel.setHorizontalAlignment(JTextField.CENTER);
		questionLabel.setVisible(true);
		this.add(questionLabel, "1");
		
		questionTextField = new JTextField();
		questionTextField.setFont(new Font("Sans Serif", Font.ITALIC, 12));
		questionTextField.setForeground(Color.LIGHT_GRAY);
		questionTextField.setText(questionTip);
		questionTextField.setVisible(true);
		questionTextField.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent event) {
				questionTextField.setText("");
				questionTextField.setFont(new Font("Sans Serif", Font.PLAIN, 12));
				questionTextField.setForeground(Color.BLACK);
			}
			
		});
		this.add(questionTextField, "2");
		
		decideButton = new JButton("DECIDE");
		decideButton.setVisible(true);
		decideButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				String questionText = questionTextField.getText();
				
				if(questionText.equalsIgnoreCase("") || questionText.equalsIgnoreCase(questionTip)) {
					JOptionPane.showMessageDialog(null, "YOU DIDN'T TYPE A QUESTION YOU SILLY GOOSE!!!", "ERROR!",
							JOptionPane.ERROR_MESSAGE);
				}
				else if(questionText.endsWith("?") == false) {
					JOptionPane.showMessageDialog(DecisionFrame.this, "THAT WASN'T A QUESTION! IT DIDN'T END WITH A " +
								"QUESTION MARK YOU SILLY GOOSE!!!", "ERROR!",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					Decision decision = new Decision();
					String strDecision = decision.getStringDecision();
					boolean boolDecision = decision.getDecision();
					answerTextField.setText(strDecision);
					
					if(boolDecision == true)
						answerTextField.setBackground(Color.GREEN);
					else
						answerTextField.setBackground(Color.RED);
				
					System.out.println(strDecision);
				}
			}
			
		});
		this.add(decideButton, "3");

		
		answerTextField = new JTextField("");
		answerTextField.setBackground(Color.WHITE);
		answerTextField.setFont(new Font("Sans Serif", Font.BOLD, 24));
		answerTextField.setHorizontalAlignment(JTextField.CENTER);
		answerTextField.setEditable(false);
		answerTextField.setVisible(true);
		this.add(answerTextField, "4");
		

	}
	
}
