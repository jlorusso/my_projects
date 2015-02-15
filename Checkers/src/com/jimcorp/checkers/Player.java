package com.jimcorp.checkers;

import java.awt.Image;
import java.util.List;

public abstract class Player {

	private boolean isTurn;
	private static int totalPlayers = 0;
	private final Image image;
	private final String imageName;
	private final int playerID;
//	private List<Piece> pieces;
	private Game game;

	public Player(Image image, String imageName) {
		this.image = image;
		this.imageName = imageName;
		Player.totalPlayers++;
		playerID = Player.totalPlayers;
//		pieces = new ArrayList<Piece>();
	}
	
	private Game getGame() {
		return game;
	}
	
	protected void setGame(Game game) {
		this.game = game;
	}
	
	public Image getImage() {
		return image;
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public List<Piece> getPieces() {
		List<Piece> pieces = game.getPlayerPieces(playerID);
		
		return pieces;
	}
	
	public boolean isTurn() {
		return isTurn;
	}

	protected void setTurn(boolean isTurn) {
		this.isTurn = isTurn;
	}

	public int getPlayerID() {
		return playerID;
	}
	
	public static int getTotalPlayers() {
		return totalPlayers;
	}

//	public void addPiece(Piece piece) {
//		pieces.add(piece);
//	}
	
	
	public Move executeMove(Move move) throws IllegalMoveException {
		
		getGame().executeMove(move);
		
		return move;
	}
	
	
	public List<Move> getLegalMoves(Piece piece) {
		
		return getGame().getLegalMoves(piece);
	}
	
	public abstract Move getMove();
	public abstract boolean isControllable();
	
	
	@Override
	public String toString() {
		return getImageName();
	}
}
