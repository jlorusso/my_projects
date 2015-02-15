package com.jimcorp.checkers;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class CheckerBoardController {

	private CheckerBoardModel checkerBoardModel;
    private CheckerBoardPanel  checkerBoardPanel;
	private final Game game;
    
	public CheckerBoardController(CheckerBoardModel checkerBoardModel, CheckerBoardPanel checkerBoardPanel, Game game) {
		
		this.checkerBoardModel = checkerBoardModel;
		this.checkerBoardPanel = checkerBoardPanel;
		this.game = game;
	}
	
	
/*	public void addCheckerMovedListeners(Player player) {

		Map<Point, CheckerSpacePanel> checkerSpaceMap = checkerBoardPanel.getCheckerSpaceMap();

		List<Piece> playerPieces = player.getPieces();
		for(Piece piece : playerPieces) {
			Point location = piece.getLocation();
			CheckerPanel checkerPanel = checkerSpaceMap.get(location).getChecker();
			checkerPanel.addCheckerMovedListener(new CheckerMovedListener());
		}
		
	}*/
	
/*	private class CheckerMovedListener extends MouseInputAdapter {
		
		private CheckerPanel currentChecker;
		private JPanel glassPane;
		private CheckerSpacePanel originalSpace;
		private Player player;
		private Piece piece;
		
		public void mousePressed(MouseEvent event) {
			currentChecker = (CheckerPanel) event.getSource();
//			CheckerSpacePanel spacePanel = (CheckerSpacePanel) currentChecker.getParent();
			Point locationInGrid = currentChecker.getLocationInGrid();//spacePanel.getLocationInGrid();
			
			piece = checkerBoardModel.getPieceAtPoint(locationInGrid);
			player = piece.getPlayer();
			
			if(player.isTurn()) {
				if(game.getShowMovesMode()) {
					List<Move> legalMoves = game.getLegalMoves(piece);
					checkerBoardPanel.highlightLegalMoves(legalMoves, false);
				}
				originalSpace = (CheckerSpacePanel) currentChecker.getParent();
			
				glassPane = (JPanel) checkerBoardPanel.getRootPane().getGlassPane();
				glassPane.setVisible(true);
				glassPane.setLayout(null);
				glassPane.add(currentChecker);
			
				Point mousePoint = glassPane.getMousePosition();
			
				Point point = new Point(mousePoint.x - (currentChecker.getWidth() / 2), 
						mousePoint.y - (currentChecker.getHeight() / 2));
			
				currentChecker.setLocation(point);
			}
		}

		public void mouseDragged(MouseEvent event) {
			
			if(player.isTurn()) {
				
				Point mousePoint = glassPane.getMousePosition();
				if(mousePoint != null) {
					Point point = new Point(mousePoint.x - (currentChecker.getWidth() / 2), 
							mousePoint.y - (currentChecker.getHeight() / 2));
					currentChecker.setLocation(point);
				}
			}
		}

		public void mouseReleased(MouseEvent event) {
		  	if(player.isTurn()) {
		  		glassPane.setVisible(false);
		  		Point boardMousePosition = checkerBoardPanel.getMousePosition();
		  	
		  		checkerBoardPanel.stopHighlight();
		  		
		  		CheckerSpacePanel space = originalSpace;
		  	
		  		Move move = null;
		  	
		  		if(boardMousePosition != null) {
		  			Component component = checkerBoardPanel.getComponentAt(boardMousePosition);
		  			if(component instanceof CheckerSpacePanel) {
		  				CheckerSpacePanel newSpace = (CheckerSpacePanel) component;
		  				try {
		  					Point newLocation = newSpace.getLocationInGrid();
		  					move = checkerBoardModel.getMove(piece, newLocation);
		  					player.executeMove(move);
		  					checkerBoardPanel.moveChecker(move);
	
		  			//		if(game.isGameOver()) {
		  			//			checkerBoardPanel.displayMessage(String.format("%s player wins!", player.getImageName()), "GAME OVER!!!");
		  			//		}
		  				} catch(IllegalMoveException illegalMoveException) {
		  					space.add(currentChecker);
		  					System.err.println(illegalMoveException.getMessage());
		  					checkerBoardPanel.displayError(illegalMoveException.getMessage(), "Illegal Move!");
		  				}
		  			}
		  		}
		  	
		  	}
		}
	}*/
}
