package com.jimcorp.checkers;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class CheckersFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GameMenuBar menuBar;
	private ScreenPanels screenPanels;
	private ScreenManager screenManager;

	/**
	 * Create the frame.
	 */
	public CheckersFrame() {
		super("Safari Adventure Checkers");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		
		menuBar = new GameMenuBar();
		setJMenuBar(menuBar);
		
		menuBar.addNewGameListener(new NewGameListener());
		menuBar.getShowMovesItem().setEnabled(false);
		
		menuBar.addExitListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				int selection = JOptionPane.showConfirmDialog(CheckersFrame.this, "Are you sure you want to exit?", 
						"Are you sure?", JOptionPane.YES_NO_OPTION);
				if(selection == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		
		menuBar.addAboutListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JOptionPane.showMessageDialog(CheckersFrame.this, String.format("Checkers Game 1.0"), 
						"About", JOptionPane.INFORMATION_MESSAGE);				
			}
		});
		

		contentPane = new JPanel();
		contentPane.setLayout(new GridBagLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		screenPanels = new ScreenPanels();
		GridBagConstraints gamePanelsC = new GridBagConstraints(); 
		gamePanelsC.gridx = 0;
		gamePanelsC.gridy = 0;
		gamePanelsC.gridwidth = 8;
		gamePanelsC.gridheight = 8;
		gamePanelsC.weightx = 1;
		gamePanelsC.weighty = 1;
		gamePanelsC.fill = GridBagConstraints.BOTH;
		
		contentPane.add(screenPanels, gamePanelsC);
		screenPanels.addPanel(new JPanel(), "Home");
		
		loadPanels();
	}
	
	
	public void loadPanels() {
		ImageSelectionModel imageSelectionModel = new ImageSelectionModel();
		ImageSelectionPanel imageSelectionPanel = new ImageSelectionPanel(imageSelectionModel.getImageMap());
		ImageSelectionController imageSelectionController = new ImageSelectionController(imageSelectionPanel, imageSelectionModel);
		imageSelectionController.toString();
		screenPanels.addPanel(imageSelectionPanel, "ImageSelection");
		screenManager = new ScreenManager(screenPanels, imageSelectionPanel, new GamePanel());
		screenManager.addScreenListeners(menuBar);
	}
	
	
	private class NewGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			screenPanels.showPanel("ImageSelection");
			
						
		}
	}
	
}
