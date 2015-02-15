package com.jimcorp.checkers;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.jimcorp.checkers.Piece.AttackDirections;

public class CheckerBoardModel implements Serializable {

	private static final long serialVersionUID = -2369200901761510454L;
	private final int ROWS;
	private final int COLS;

	
	private CheckerSpaceModel[][] board;
	
	private String errStr = "";
	
	public CheckerBoardModel(int rows, int cols) {
		ROWS = rows;
		COLS = cols;
		
		board = new CheckerSpaceModel[ROWS][COLS];
		
		int colorIndex = 0;
		boolean canHoldChecker = true;
		for(int row=0; row<ROWS; row++) {
			for(int col=0; col<COLS; col++) {
				Point location = new Point(row, col);
				CheckerSpaceModel checkerSpaceModel = new CheckerSpaceModel(location, canHoldChecker);
				board[row][col] = checkerSpaceModel;
				
				if(col < (COLS - 1)) {
					if(colorIndex == 1) {
						colorIndex = 0;
						canHoldChecker = true;
					}
					else {
						colorIndex = 1;
						canHoldChecker = false;
					}
				}
			}
		}
	}
	
	
	public void addPieces(Map<Point, Piece> pieces) {
		
		Set<Entry<Point, Piece>> pieceSet = pieces.entrySet();
		Iterator<Entry<Point, Piece>> iter = pieceSet.iterator();

		while(iter.hasNext()) {
			Entry<Point, Piece> pair = iter.next();
			addPiece(pair.getValue(), pair.getKey());
		}
		
	}
	
	private void addPiece(Piece piece, Point newLocation) {
		board[newLocation.x][newLocation.y].addChecker(piece);
	}

	private Piece removePieceAtPoint(Point point) {
		return board[point.x][point.y].removeChecker();
	}
	
	
	protected Piece getPieceAtPoint(Point point) {
		return board[point.x][point.y].getChecker();
	}

	
	protected CheckerSpaceModel getSpaceAtPoint(Point point) {
		return board[point.x][point.y];
	}
	
	private static int getSpacesTraveled(Point oldLocation, Point newLocation) {
		int xTraveled = newLocation.x - oldLocation.x;
		int yTraveled = newLocation.y - oldLocation.y;
		
		if(Math.abs(xTraveled) == Math.abs(yTraveled)) {
			return Math.abs(xTraveled);
		}
		else {
			return 0;
		}
	}
	
	
	private static boolean isJumpAttempt(Point oldLocation, Point newLocation) {
		boolean isJumpAttempt = false;
		
		int spacesTraveled = getSpacesTraveled(oldLocation, newLocation);
		
		if(spacesTraveled == 2) {
			isJumpAttempt = true;
		}
		
		return isJumpAttempt;
	}
	
	
	protected boolean isMoveKingMe(Piece piece, Point newLocation) {
		boolean kingMe = false;
		
		if(newLocation.x == 0 && piece.getAttackDirection().equals(AttackDirections.NORTH)) {
			kingMe = true;
		}
		else if(newLocation.x == ROWS-1 && piece.getAttackDirection().equals(AttackDirections.SOUTH)) {
			kingMe = true;
		}
		
		return kingMe;
	}
	
	
	public Move getMove(Piece piece, Point newLocation) {
		
		Point oldLocation = piece.getLocation();
		boolean isJumpAttempt = isJumpAttempt(oldLocation, newLocation);
		boolean isKingMe = isMoveKingMe(piece, newLocation);
		Move move = null;
		
		if(isJumpAttempt) {
			Point jumpedPoint = getMidPoint(oldLocation, newLocation);
			move = new Move(piece, oldLocation, newLocation, isKingMe, isJumpAttempt, jumpedPoint);
		}
		else {
			move = new Move(piece, oldLocation, newLocation, isKingMe);
		}
			
		return move;
	}
	
	
	public void movePiece(Move move) throws IllegalMoveException {

		Piece piece = move.getPiece();
		Point oldLocation = move.getOldLocation();
		Point newLocation = move.getNewLocation();
		boolean isJumpAttempt = move.isJumpAttempt();
		
		if(isSpaceOpen(newLocation)) {
			removePieceAtPoint(oldLocation);
			addPiece(piece, newLocation);

			if(isJumpAttempt) {
				Point jumpedPoint = getMidPoint(oldLocation, newLocation);
				removePieceAtPoint(jumpedPoint);
			}
			
			if(move.isKingMe()) {
				piece.setKing(true);
			}
		}
		else {
			throw new IllegalMoveException(errStr);
		}
		
	}

	
	private boolean isSpaceOpen(Point location) {
		
		boolean isOpen = false;
		
		if(isLocationOnBoard(location)) {
			CheckerSpaceModel space = getSpaceAtPoint(location);
			if(space.canHoldChecker() && space.hasChecker() == false) {
				isOpen = true;
			}
		}
		
		return isOpen;
	}
	
	public static Point getMidPoint(Point oldLocation, Point newLocation) {

		int midX = (newLocation.x + oldLocation.x) / 2;
		int midY = (newLocation.y + oldLocation.y) / 2;
		
		return new Point(midX, midY);
	}

	
/*	private ArrayList<Integer> getLegalDistances(Piece piece, boolean isJumpAttempt) {
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
	
	
	public List<Move> getLegalMoves(Piece piece) {
		List<Move> legalMoves = new ArrayList<Move>(4);
		
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
			
			if(isLocationOnBoard(new Point(newRow, newCol)) ) {
				CheckerSpaceModel checkerSpaceModel = board[newRow][newCol];
				if(checkerSpaceModel.hasChecker()) {
					if(checkerSpaceModel.getChecker().isTargetEnemy(piece)) {
						//check for jump
						Point jumpedPoint = new Point(newRow, newCol);
						newRow = newRow + rowDirection;
						newCol = newCol + colDirection;
						if(isLocationOnBoard(new Point(newRow, newCol)) ) {
							checkerSpaceModel = board[newRow][newCol];
							if(checkerSpaceModel.hasChecker() == false) {
								Point newLocation = new Point(newRow, newCol);
								legalMoves.add(new Move(piece, currentLocation, newLocation, 
										isMoveKingMe(piece, newLocation), true, jumpedPoint/*getPieceAtPoint(jumpedPoint)));
							}
						}
					}
				}
				else {
					Point newLocation = new Point(newRow, newCol);
					legalMoves.add(new Move(piece, currentLocation, newLocation, isMoveKingMe(piece, newLocation)));
				}
			}
		}
		
		return legalMoves;
	}
	
	
	public List<Move> getLegalJumps(Piece piece) {
		List<Move> legalJumps = new ArrayList<Move>();
		
		for(Move move : getLegalMoves(piece)) {
			if(move.isJumpAttempt()) {
				legalJumps.add(move);
			}
		}
		
		return legalJumps;
	}
*/	
	
	public boolean isLocationOnBoard(Point location) {
		
		if( ((0 > location.x) || (location.x >= ROWS) || (0 > location.y) || (location.y >= COLS)) ) {
			errStr = String.format("New location [%s,%s] is outside the bounds of the board.", 
					location.x, location.y);
			return false;
		}
		else {
			return true;
		}
	}
	
	
/*	private boolean isMoveValid(Piece piece, Point newLocation, boolean isJumpAttempt) {
		boolean isValid = false;
		
		if( ((0 > newLocation.x) || (newLocation.x >= ROWS) || (0 > newLocation.y) || (newLocation.y >= COLS)) ) {
			errStr = String.format("New location [%s,%s] is outside the bounds of the board.", 
					newLocation.x, newLocation.y);
			return false;
		}
		
		if(board[newLocation.x][newLocation.y].canHoldChecker() == false) {
			errStr = String.format("New location [%s,%s] is not a legal space for a checker.", 
					newLocation.x, newLocation.y);
			return false;
		}
		
		Point oldLocation = piece.getLocation();
		int yDiff = Math.abs(newLocation.y - oldLocation.y);
		int xDiff = newLocation.x - oldLocation.x;
		
		// Ensure space is free
		CheckerSpaceModel space = board[newLocation.x][newLocation.y];
		if(space.hasChecker() == false) {
			if(yDiff == 1 || (isJumpAttempt && yDiff == 2)) {
				ArrayList<Integer> legalDistances = getLegalDistances(piece, isJumpAttempt);
				for(int legalDistance : legalDistances) {
					if(xDiff == legalDistance) {
						if(isJumpAttempt) {
							Point jumpedPoint = getMidPoint(oldLocation, newLocation);
							CheckerSpaceModel jumpedSpace = board[jumpedPoint.x][jumpedPoint.y];
							if(jumpedSpace.hasChecker() && jumpedSpace.getChecker().isTargetEnemy(piece)) {
								isValid = true;
							}
							else {
								errStr = String.format("No enemy piece to jump at [%s,%s].",
										jumpedPoint.x, jumpedPoint.y);
							}
						}
						else {
							isValid = true;
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
*/	
	
	public List<CheckerSpaceModel> getSpaces() {
		List<CheckerSpaceModel> spaceModels = new ArrayList<CheckerSpaceModel>();
		for(int row=0; row<ROWS; row++) {
			for(int col=0; col<COLS; col++) {
				spaceModels.add(board[row][col]);
			}
		}
		
		return spaceModels;
	}
	
	
	public Map<Point, Piece> getPieces() {
		
		Map<Point, Piece> pieces = new HashMap<Point, Piece>();
		
		for(int row=0; row<ROWS; row++) {
			for(int col=0; col<COLS; col++) {
				CheckerSpaceModel space = board[row][col];
				if(space.hasChecker()) {
					pieces.put(space.getLocationInGrid(), space.getChecker());
				}
			}
		}
		
		return pieces;
	}
	
	
	public int getRows() {
		return ROWS;
	}


	public int getCols() {
		return COLS;
	}


	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(int row=0; row<ROWS; row++) {
			for(int col=0; col<COLS; col++) {
				Piece piece = board[row][col].getChecker();
				String formattedStr = "";
				if(piece != null) {
					formattedStr = String.format("%s[%s,%s]", piece.getImageName(), piece.getLocation().x, piece.getLocation().y);
				}
				
				str.append(String.format("%"+15+"s", formattedStr));
			}
			str.append("\n");
		}
		
		return str.toString();
	}

}
