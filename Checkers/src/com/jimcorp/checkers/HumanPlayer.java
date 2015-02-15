package com.jimcorp.checkers;

import java.awt.Image;

public class HumanPlayer extends Player {

	public HumanPlayer(Image image, String imageName) {
		super(image, imageName);
	}

/*	@Override
	public Move executeMove(Move move) throws IllegalMoveException {
	
		getGame().executeMove(move);
		
		return move;
	}*/
	
	
	@Override
	public Move getMove() {
		
		return null;
	}

	
	@Override
	public boolean isControllable() {
		return true;
	}
	
}
