package com.jimcorp.checkers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

public class ScreenManager {

	private ScreenPanels screenPanels;
	private ImageSelectionPanel selectScreen;
	private GamePanel gameScreen;
	private Game game;
	
	enum State { TURN_P1, TURN_P2, MAIN, SELECT};
	public State gameState;
	
	public ScreenManager(ScreenPanels screenPanels, ImageSelectionPanel selectScreen, GamePanel gameScreen) {
		
		this.screenPanels = screenPanels; 
		this.selectScreen = selectScreen;
		this.gameScreen = gameScreen;
	}
	
	
	public void addScreenListeners(final GameMenuBar menuBar) {
		
		selectScreen.addOkClickedListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				
				String player1Name = selectScreen.getList().getSelectedValue();
				
				Map<String, ImageIcon> imageMap = selectScreen.getImageMap();
				ImageIcon player1Image= imageMap.get(player1Name);
				
				Player player1 = new HumanPlayer(player1Image.getImage(), player1Name);
				Set<String> characters = imageMap.keySet();
				characters.remove(player1Name);
				
				Random gen = new Random();
				int characterLookup = gen.nextInt(characters.size());
				String[] characterArray = characters.toArray(new String[characters.size()]);
				String player2Name = characterArray[characterLookup];
				ImageIcon player2Image = imageMap.get(player2Name);
				Player player2 = new ComputerPlayer(player2Image.getImage(), player2Name);
				
				game = new Game(player1, player2);
				game.setShowMovesMode(true);
				gameScreen = new GamePanel();
				GameController gameController = new GameController(game, gameScreen);
				gameController.addCheckerMovedListeners(player1);
				gameController.addCheckerMovedListeners(player2);
				
		//		CheckerBoardModel checkerBoardModel = game.getBoard();
		//		CheckerBoardPanel checkerBoardPanel = new CheckerBoardPanel(Color.GREEN, Color.GRAY,
		//				checkerBoardModel.getRows(), checkerBoardModel.getCols());
/*				Collection<Piece> pieces = checkerBoardModel.getPieces().values();
				for(Piece piece : pieces) {
					Point location = piece.getLocation();
					CheckerSpacePanel checkerSpacePanel = checkerBoardPanel.getSpaceAtPoint(location);
					checkerSpacePanel.add(new CheckerPanel(piece.getImage()));
				}*/
				
			/*	CheckerBoardController checkerBoardController = 
						new CheckerBoardController(checkerBoardModel, checkerBoardPanel, game);
				checkerBoardController.addCheckerMovedListeners(player1);
				checkerBoardController.addCheckerMovedListeners(player2);
			*/	
				screenPanels.addPanel(gameScreen, "Checkers");
				menuBar.getShowMovesItem().setEnabled(true);
				menuBar.getShowMovesItem().setSelected(true);
				
				screenPanels.showPanel("Checkers");
				
			}
			
		});
		
		
		menuBar.addShowMovesListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JMenuItem mnItem = (JMenuItem) event.getSource();
				if(game != null) {
					game.setShowMovesMode(mnItem.isSelected());
					
				}
			}
			
		});
	}
}
