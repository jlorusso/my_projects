package com.jimcorp.checkers;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

public class GameController {

	private Game game;
	private GamePanel gamePanel;
//	private CheckerBoardController cbController;

	public enum State { P1_TURN, P2_TURN, GAME_OVER };
	private State gameState;
	
	
	public GameController(Game aGame, GamePanel aGamePanel) {
		
		game = aGame;
		gamePanel = aGamePanel;
		
		CheckerBoardModel board = game.getBoard();
		CheckerBoardPanel checkerBoardPanel = gamePanel.getCheckerBoard();
		
		Collection<Piece> pieces = board.getPieces().values();
		for(Piece piece : pieces) {
			Point location = piece.getLocation();
			CheckerSpacePanel checkerSpacePanel = checkerBoardPanel.getSpaceAtPoint(location);
			checkerSpacePanel.add(new CheckerPanel(piece.getImage()));
		}
		
		if(game.getCurrentPlayer().equals(game.getPlayer1())) {
			gameState = State.P1_TURN;
		}
		else {
			gameState = State.P2_TURN;
		}
		System.out.println("Starting player = " + game.getCurrentPlayer());
		
//		cbController = new CheckerBoardController(board, checkerBoardPanel, game);
		
//		for(Player player : game.getPlayers()) {
			//if(player.isControllable()) {
//				cbController.addCheckerMovedListeners(player);
			//}
//		}
		
	}
	
		
	public void endTurn() {
		System.out.printf("Current State: %s%n", gameState);

		if(gameState.equals(State.P1_TURN)) {
			gameState = State.P2_TURN;
			Player player = game.getPlayer2();
			Move move = player.getMove();
			try {
				player.executeMove(move);
				gamePanel.getCheckerBoard().moveChecker(move);
				if(! game.isMoveDoubleJump(move)) {
					game.switchTurn();
					gameState = State.P1_TURN;
				}
				else {
					System.out.println("Move is double jump????");
				}
			} catch (IllegalMoveException e) {
				e.printStackTrace();
			}
		}
		else {
			game.switchTurn();
			gameState = State.P1_TURN;
			
		}
//		else if(gameState.equals(State.P2_TURN) && newState.equals(State.P1_TURN)) {
			
//				game.switchTurn();
//			gameState = State.P2_TURN;
//		}
		
	}
	
	
	public void addCheckerMovedListeners(Player player) {

		Map<Point, CheckerSpacePanel> checkerSpaceMap = gamePanel.getCheckerBoard().getCheckerSpaceMap();

		List<Piece> playerPieces = player.getPieces();
		for(Piece piece : playerPieces) {
			Point location = piece.getLocation();
			CheckerPanel checkerPanel = checkerSpaceMap.get(location).getChecker();
			checkerPanel.addCheckerMovedListener(new CheckerMovedListener());
		}
		
	}

	
	private class CheckerMovedListener extends MouseInputAdapter {
		
		private CheckerPanel currentChecker;
		private JPanel glassPane;
		private CheckerSpacePanel originalSpace;
		private Player player;
		private Piece piece;
		private final CheckerBoardModel checkerBoardModel = game.getBoard();
		private final CheckerBoardPanel checkerBoardPanel = gamePanel.getCheckerBoard();
		
		
		@Override
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

		
		@Override
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

		
		@Override
		public void mouseReleased(MouseEvent event) {
		  	if(player.isTurn()) {
		  		glassPane.setVisible(false);
		  		Point boardMousePosition = checkerBoardPanel.getMousePosition();
		  	
		  		checkerBoardPanel.stopHighlight();
		  		
		  		CheckerSpacePanel space = originalSpace;
		  	
		  		Move move = null;
		  		boolean moveSuccessful = false;
		  	
		  		if(boardMousePosition != null) {
		  			Component component = checkerBoardPanel.getComponentAt(boardMousePosition);
		  			if(component instanceof CheckerSpacePanel) {
		  				CheckerSpacePanel newSpace = (CheckerSpacePanel) component;
		  				try {
		  					Point newLocation = newSpace.getLocationInGrid();
		  					move = checkerBoardModel.getMove(piece, newLocation);
		  					player.executeMove(move);
		  					checkerBoardPanel.moveChecker(move);
		  					moveSuccessful = true;
		  					endTurn();
		  				} catch(IllegalMoveException illegalMoveException) {
		  					space.add(currentChecker);
		  					System.err.println(illegalMoveException.getMessage());
		  					gamePanel.displayError(illegalMoveException.getMessage(), "Illegal Move!");
		  				}
		  			}
		  			else {
		  				System.err.println("NO CHECKER SPACE FOUND AT POSITION: " + boardMousePosition);
		  			}
		  		}
		  		else {
	  				System.err.println("BOARD MOUSE POSITION = null -> " + boardMousePosition);
	  			}
		  	
		  		if(!moveSuccessful) {
		  			space.add(currentChecker);
		  		}
		  	}
		}
	}
}
