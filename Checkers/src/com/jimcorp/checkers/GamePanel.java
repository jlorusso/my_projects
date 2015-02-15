package com.jimcorp.checkers;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -3285806923624052193L;
	
	private CheckerBoardPanel checkerBoardPanel;
	private JPanel statsPanel;
	private JLabel currentTurnLabel;
	private JPanel turnPanel;
	
	public GamePanel() {
		
		setLayout(new GridBagLayout());
		setBackground(Color.BLUE);
		
		currentTurnLabel = new JLabel("Welcome!");
		currentTurnLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		currentTurnLabel.setForeground(Color.GREEN);
		
		turnPanel = new JPanel();
		turnPanel.setLayout(new BorderLayout());
		turnPanel.add(currentTurnLabel, BorderLayout.CENTER);
		GridBagConstraints currentTurnC = new GridBagConstraints();
		currentTurnC.gridx = 0;
		currentTurnC.gridy = 0;
		currentTurnC.insets = new Insets(20, 10, 20, 10);
		currentTurnC.gridwidth = 8;
		currentTurnC.gridheight = 2;
//		currentTurnC.fill = GridBagConstraints.HORIZONTAL;
		
		add(turnPanel, currentTurnC);
		
		currentTurnLabel.getParent().setBackground(Color.RED);
		
		
		checkerBoardPanel = new CheckerBoardPanel(Color.GREEN, Color.GRAY, Game.ROWS, Game.COLS);
		GridBagConstraints checkerBoardC = new GridBagConstraints();
		checkerBoardC.gridx = 0;
		checkerBoardC.gridy = 3;
		checkerBoardC.gridwidth = 8;
		checkerBoardC.gridheight = 8;
		checkerBoardC.weightx = .75;
		checkerBoardC.weighty = 1;
		checkerBoardC.fill = GridBagConstraints.BOTH;
		add(checkerBoardPanel, checkerBoardC);
		
		
		statsPanel = new GameStatisticsPanel();
		statsPanel.setBackground(Color.red);
		GridBagConstraints statsC = new GridBagConstraints();
		statsC.gridx = 9;
		statsC.gridy = 3;
		statsC.gridwidth = 2;
		statsC.gridheight = 8;
		statsC.insets = new Insets(0, 20, 0, 20);
		statsC.weightx = .25;
		statsC.weighty = .75;
//		statsC.fill = GridBagConstraints.VERTICAL;
//		add(statsPanel, statsC);
		
		statsPanel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent event) {
				String player1 = "Hippo";
				if(getCurrentPlayer().startsWith(player1)) {
					setCurrentPlayer("Giraffe");
				}
				else {
					setCurrentPlayer(player1);
				}
			}
			
		});
	}
	
	
	public String getCurrentPlayer() {
		return currentTurnLabel.getText();
	}
	
	public void setCurrentPlayer(String player) {
		currentTurnLabel.setText(player + "'s turn");
	}

	public CheckerBoardPanel getCheckerBoard() {
		return checkerBoardPanel;
	}
	
	public void displayMessage(String message, String header) {
		JOptionPane.showMessageDialog(this, message, header, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void displayError(String message, String header) {
		JOptionPane.showMessageDialog(this, message, header, JOptionPane.ERROR_MESSAGE);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		GamePanel panel = new GamePanel();
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 700);
		frame.setVisible(true);
	}
}
