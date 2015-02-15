package com.jimcorp.checkers;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import com.jimcorp.checkers.Piece.AttackDirections;

public class Game implements Serializable {

	private static final long serialVersionUID = 5619463190776866480L;
	
	public static final int ROWS = 8;
	public static final int COLS = 8;
	private static final int initialPieces = ((ROWS/2)*((ROWS-2)/2));
	
	private CheckerBoardModel board;
	private Player player1;
	private Player player2;
	private boolean showMovesMode;
	
	private Player currentPlayer;
	private boolean player1Turn = false;
	
	private Queue<Move> moveHistory;
	private String errStr;
	
	public Game(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		
		board = new CheckerBoardModel(ROWS, COLS);
		moveHistory = Collections.asLifoQueue(new ArrayDeque<Move>());

		Map<Point, Piece> pieces = getStartingPieces();
		board.addPieces(pieces);
		
		Random rand = new Random();
		int num = rand.nextInt(2);
		if(num == 1) {
			getPlayer1().setTurn(true);
			setPlayer1Turn(true);
			setCurrentPlayer(getPlayer1());
		}
		else {
			getPlayer2().setTurn(true);
			setPlayer1Turn(false);
			setCurrentPlayer(getPlayer2());
		}
		
		setShowMovesMode(false);
		
		player1.setGame(this);
		player2.setGame(this);
	}
	
	
	private Map<Point, Piece> getStartingPieces() {
		
		Map<Point, Piece> pieces = new HashMap<Point, Piece>(initialPieces*2);
		
		int blackCurrX = 0;
		int blackCurrY = 0;
		int redCurrX = ROWS-1;
		int redCurrY = 1;
		
		for(int i=0; i<initialPieces; i++) {
			if(i != 0) {
				blackCurrY += 2;
				redCurrY += 2;
			}
			if(blackCurrY >= (COLS)) {
				blackCurrX++;
				blackCurrY = blackCurrX % 2;
				redCurrX--;
				redCurrY = redCurrX % 2;
			}
			
			Point blackLocation = new Point(blackCurrX, blackCurrY);
			Piece blackPiece = new Piece(getPlayer1(), blackLocation);
			pieces.put(blackLocation, blackPiece);
			
			Point redLocation = new Point(redCurrX, redCurrY);
			Piece redPiece = new Piece(getPlayer2(), redLocation);
			pieces.put(redLocation, redPiece);
		}
		
		
		return pieces;
	}
	
	
	public boolean isGameOver() {
		boolean isOver = false;
		
		if(player1.getPieces().isEmpty() || player2.getPieces().isEmpty()) {
			isOver = true;
		}
		
		return isOver;
	}
	

	protected void switchTurn() {
		if(currentPlayer.equals(player2)) {
			player1.setTurn(true);
			player2.setTurn(false);
			this.setPlayer1Turn(true);
			this.setCurrentPlayer(player1);
		}
		else {
			player2.setTurn(true);
			player1.setTurn(false);
			this.setPlayer1Turn(true);
			this.setCurrentPlayer(player2);
		}
	}
	
	
	private boolean isMoveValid(Move move) {
		boolean isValid = false;
		
		Point oldLocation = move.getOldLocation();
		Point newLocation = move.getNewLocation();
		Piece piece = move.getPiece();
		boolean isJumpAttempt = move.isJumpAttempt();
		
		if( ((0 > newLocation.x) || (newLocation.x >= ROWS) || (0 > newLocation.y) || (newLocation.y >= COLS)) ) {
			errStr = String.format("New location [%s,%s] is outside the bounds of the board.", 
					newLocation.x, newLocation.y);
			return false;
		}
		
		if(board.getSpaceAtPoint(newLocation).canHoldChecker() == false) {
			errStr = String.format("New location [%s,%s] is not a legal space for a checker.", 
					newLocation.x, newLocation.y);
			return false;
		}
		
		
		int yDiff = Math.abs(newLocation.y - oldLocation.y);
		int xDiff = newLocation.x - oldLocation.x;
		
		// Ensure space is free
		CheckerSpaceModel space = board.getSpaceAtPoint(newLocation);
		if(space.hasChecker() == false) {
			if(yDiff == 1 || (isJumpAttempt && yDiff == 2)) {
				ArrayList<Integer> legalDistances = getLegalDistances(piece, isJumpAttempt);
				for(int legalDistance : legalDistances) {
					if(xDiff == legalDistance) {
						if(isJumpAttempt) {
							Point jumpedPoint = CheckerBoardModel.getMidPoint(oldLocation, newLocation);
							CheckerSpaceModel jumpedSpace = board.getSpaceAtPoint(jumpedPoint);
							if(jumpedSpace.hasChecker() && jumpedSpace.getChecker().isTargetEnemy(piece)) {
								isValid = true;
							}
							else {
								errStr = String.format("No enemy piece to jump at [%s,%s].",
										jumpedPoint.x, jumpedPoint.y);
							}
						}
						else if(isMoveDoubleJump(move) == false) {
							isValid = true;
						}
						else {
							errStr = String.format("Must perform double jump.", "");
						}
					}
					else {
						errStr = String.format("Piece cannot move from [%s,%s] to [%s,%s]. "
								+ "Only +/- %s square is allowed for a single turn.",
								oldLocation.x, oldLocation.y, newLocation.x, newLocation.y, legalDistance);
					}
				}
			}
			else {
				errStr = String.format("Piece cannot move from [%s,%s] to [%s,%s]. Only +/- 1 square(s) is allowed for a single turn.",
						oldLocation.x, oldLocation.y, newLocation.x, newLocation.y);
			}
		}
		else {
			errStr = String.format("New Location [%s,%s] is already occupied by another piece.", 
					newLocation.x, newLocation.y);
		}
		
		return isValid;
	}
	
	
	private Move getPreviousMove() {
		return (moveHistory.isEmpty() ? null : moveHistory.peek());
	}
	
	
	public boolean isMoveDoubleJump(Move move) {
		Move previousMove = getPreviousMove();
		
		if(previousMove != null && previousMove.isJumpAttempt() && previousMove.getPiece().equals(move.getPiece())) {
			if(move.isJumpAttempt())
				return true;
		}
		
		return false;
	}
	
	
	public List<Move> getLegalMoves(Piece piece) {
		List<Move> legalMoves = new ArrayList<Move>(4);
		
		Move previousMove = getPreviousMove();
		
		Point currentLocation = piece.getLocation();
		int iterations = (piece.isKing() ? 4 : 2);
		int rowDirection = (piece.getStartingSide().equals(AttackDirections.SOUTH) ? -1 : 1);

		int newRow = currentLocation.x + rowDirection;

		for(int i=0; i<iterations; i++) {
			if(piece.isKing() && (i == (iterations/2))) {
				rowDirection *= -1;
			}
			newRow = currentLocation.x + rowDirection;

			int colDirection = (i%2 == 1 ? 1 : -1);

			int newCol = currentLocation.y + colDirection;

			if(board.isLocationOnBoard(new Point(newRow, newCol)) ) {
				CheckerSpaceModel checkerSpaceModel = board.getSpaceAtPoint(new Point(newRow, newCol));
				if(checkerSpaceModel.hasChecker()) {
					if(checkerSpaceModel.getChecker().isTargetEnemy(piece)) {
						//check for jump
						Point jumpedPoint = new Point(newRow, newCol);
						newRow = newRow + rowDirection;
						newCol = newCol + colDirection;
						if(board.isLocationOnBoard(new Point(newRow, newCol)) ) {
							checkerSpaceModel = board.getSpaceAtPoint(new Point(newRow, newCol));
							if(checkerSpaceModel.hasChecker() == false) {
								Point newLocation = new Point(newRow, newCol);
								legalMoves.add(new Move(piece, currentLocation, newLocation, 
										board.isMoveKingMe(piece, newLocation), true, jumpedPoint/*getPieceAtPoint(jumpedPoint)*/));
							}
						}
					}
				}
				else {
					Point newLocation = new Point(newRow, newCol);
					legalMoves.add(new Move(piece, currentLocation, newLocation, board.isMoveKingMe(piece, newLocation)));
				}
			}
		}
		
		
		if(previousMove != null && previousMove.isJumpAttempt() && previousMove.getPiece().equals(piece)) {
			return getLegalJumps(legalMoves);
		}
		
	
		return legalMoves;
	}
	
	
	public List<Move> getLegalJumps(Piece piece) {
		
		List<Move> legalMoves = getLegalMoves(piece);
		
		return getLegalJumps(legalMoves);
	}

	
	private List<Move> getLegalJumps(List<Move> legalMoves) {
		List<Move> legalJumps = new ArrayList<Move>(4);
		
		for(Move move : legalMoves) {
			if(move.isJumpAttempt()) {
				legalJumps.add(move);
			}
		}
		
		return legalJumps;
	}
	
	
	private static ArrayList<Integer> getLegalDistances(Piece piece, boolean isJumpAttempt) {
		Enum<AttackDirections> attackDirection = piece.getAttackDirection();
		ArrayList<Integer> legalDirections = new ArrayList<Integer>(2);
		int base_distance = 1;
		if(isJumpAttempt) {
			base_distance++;
		}
		
		if(attackDirection == AttackDirections.NORTH) {
			legalDirections.add(base_distance * -1);
		}
		else if(attackDirection == AttackDirections.SOUTH) {
			legalDirections.add(base_distance);
		}
		else if(attackDirection == AttackDirections.BOTH){
			legalDirections.add(base_distance);
			legalDirections.add(base_distance * -1);
		}
		
		return legalDirections;
	}
	
	
	public void executeMove(Move move) throws IllegalMoveException {
		
		if(isMoveValid(move)) {
			board.movePiece(move);
			moveHistory.add(move);
//			if(move.isJumpAttempt() && getLegalJumps(move.getPiece()).isEmpty() == false) {
			
			
//			}
//			else {
//				switchTurn();
//				System.out.printf("%s player's turn%n", currentPlayer);
//			}
			
//			if(isGameOver()) {
//				System.out.printf("Game Over! %s loses.", currentPlayer);
//			}
		}
		else {
			throw new IllegalMoveException(errStr);
		}
		
	}
	

	protected CheckerBoardModel getBoard() {
		return this.board;
	}
	
	public Player getPlayer1() {
		return this.player1;
	}
	
	public Player getPlayer2() {
		return this.player2;
	}
	
	public boolean isPlayer1Turn() {
		return player1Turn;
	}

	public void setPlayer1Turn(boolean player1Turn) {
		this.player1Turn = player1Turn;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentTurn) {
		this.currentPlayer = currentTurn;
	}

	public boolean getShowMovesMode() {
		return showMovesMode;
	}

	public void setShowMovesMode(boolean showMovesMode) {
		this.showMovesMode = showMovesMode;
	}


	public List<Piece> getPlayerPieces(int playerID) {
		
		List<Piece> pieces = new ArrayList<Piece>(initialPieces);
		
		Collection<Piece> totalPieces = getBoard().getPieces().values();
		
		for(Piece piece : totalPieces) {
			if(piece.getPlayer().getPlayerID() == playerID) {
				pieces.add(piece);
			}
		}
		
		return pieces;
	}


	public List<Player> getPlayers() {
		
		List<Player> players = new ArrayList<Player>(2);
		players.add(getPlayer1());
		players.add(getPlayer2());
		
		return players;
	}



}
