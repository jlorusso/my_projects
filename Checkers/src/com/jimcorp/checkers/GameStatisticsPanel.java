package com.jimcorp.checkers;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GameStatisticsPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JLabel statsLabel;
	private JLabel player1Label;
	private JLabel player2Label;

	
	public GameStatisticsPanel() {
		setLayout(new GridBagLayout());
		
		Font headerFont = new Font("Helvetica", Font.BOLD, 20);
		Font rowFont = new Font("SansSerif", Font.BOLD, 12);
		
		statsLabel = new JLabel("Game Statistics:");
		statsLabel.setFont(headerFont);
		GridBagConstraints statsLabelC = new GridBagConstraints();
		statsLabelC.gridx = 0;
		statsLabelC.gridy = 0;
		statsLabelC.gridwidth = 2;
		statsLabelC.gridheight = 1;
		statsLabelC.insets = new Insets(0, 20, 0, 20);
		add(statsLabel, statsLabelC);
		
		player1Label = new JLabel("Player 1:");
		GridBagConstraints player1LabelC = new GridBagConstraints();
		player1LabelC.gridx = 0;
		player1LabelC.gridy = 1;
		player1LabelC.gridwidth = 2;
		player1LabelC.gridheight = 1;
		add(player1Label, player1LabelC);
		
		player2Label = new JLabel("Player 2:");
		GridBagConstraints player2LabelC = new GridBagConstraints();
		player2LabelC.gridx = 0;
		player2LabelC.gridy = 2;
		player2LabelC.gridwidth = 2;
		player2LabelC.gridheight = 1;
		add(player2Label, player2LabelC);
		
		JLabel piecesRemainingLabel = new JLabel("Pieces Remaining");
		piecesRemainingLabel.setFont(rowFont);
		GridBagConstraints piecesRemainingLabelC = new GridBagConstraints();
		piecesRemainingLabelC.gridx = 0;
		piecesRemainingLabelC.gridy = 4;
		piecesRemainingLabelC.gridwidth = 1;
		piecesRemainingLabelC.gridheight = 1;
		add(piecesRemainingLabel, piecesRemainingLabelC);
		
		String[] columns = {"Player 1", "Player 2"};
		Object[][] data = {
				{"John", "Smith"},
				{"Betty", "Ann"}
		};
		
		JTable table = new JTable(data, columns);
		JScrollPane scrollPane = new JScrollPane(table);		
		GridBagConstraints tableC = new GridBagConstraints();
		tableC.gridx = 1;
		tableC.gridy = 3;
		tableC.gridwidth = 2;
		tableC.gridheight = 2;
		tableC.weightx = .5;
		tableC.fill = GridBagConstraints.HORIZONTAL;
		add(scrollPane, tableC);
		
	}
	
	
/*	@Override
	public Dimension getPreferredSize() {
		return new Dimension(300, 300);
	}
	
	
	@Override
	public Dimension getMinimumSize() {
		return new Dimension(500, 500);
	}
	*/
}
