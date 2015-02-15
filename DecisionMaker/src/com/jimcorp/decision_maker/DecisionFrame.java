package com.jimcorp.decision_maker;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	private JLabel answerLabel;
	private JLabel questionLabel;
	private static final String questionTip = "Type question here";
	
	
	public DecisionFrame() {
		super("Decision Maker 1.0");
		GridBagLayout layout = new GridBagLayout();
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
		questionLabel.setHorizontalAlignment(JTextField.LEFT);
		questionLabel.setVisible(true);
		GridBagConstraints qLblC = new GridBagConstraints();
		qLblC.gridx = 0;
		qLblC.gridy = 0;
		qLblC.gridwidth = 6;
		qLblC.gridheight = 1;
		qLblC.weightx = 1;
		qLblC.insets = new Insets(0, 5, 10, 0);
		qLblC.anchor = GridBagConstraints.LINE_START;
		qLblC.fill = GridBagConstraints.HORIZONTAL;
		add(questionLabel, qLblC);
		
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
		GridBagConstraints qTxtC = new GridBagConstraints();
		qTxtC.gridx = 1;
		qTxtC.gridy = 1;
		qTxtC.gridwidth = 8;
		qTxtC.gridheight = 2;
		qTxtC.weightx = .75;
		qTxtC.fill = GridBagConstraints.HORIZONTAL;
		add(questionTextField, qTxtC);
		
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
					answerLabel.setText(strDecision);
					
					if(boolDecision == true)
						answerLabel.setBackground(Color.GREEN);
					else
						answerLabel.setBackground(Color.RED);
				
					System.out.println(strDecision);
				}
			}
			
		});
		GridBagConstraints dBtnC = new GridBagConstraints();
		dBtnC.gridx = 10;
		dBtnC.gridy = 1;
		dBtnC.gridheight = 2;
		dBtnC.gridwidth = 2;
		dBtnC.weightx = .25;
		dBtnC.fill = GridBagConstraints.HORIZONTAL;
		add(decideButton, dBtnC);

		
		answerLabel = new JLabel("");
		answerLabel.setOpaque(true);
		answerLabel.setBackground(Color.WHITE);
		answerLabel.setFont(new Font("Sans Serif", Font.BOLD, 24));
		answerLabel.setHorizontalAlignment(JLabel.CENTER);
		GridBagConstraints ansTxtC = new GridBagConstraints();
		ansTxtC.gridx = 1;
		ansTxtC.gridy = 3;
		ansTxtC.gridwidth = 10;
		ansTxtC.gridheight = 2;
		ansTxtC.weightx = 1;
		ansTxtC.fill = GridBagConstraints.HORIZONTAL;
		ansTxtC.insets = new Insets(10, 10, 10, 10);
		add(answerLabel, ansTxtC);
		

	}
	
}
