package com.jimcorp.checkers;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class ComputerPlayer extends Player {
	
	public ComputerPlayer(Image image, String imageName) {
		super(image, imageName);
	}

/*	@Override
	public Move executeMove(Move move) throws IllegalMoveException {
		
		getGame().executeMove(move);
		
		return getBestMove();
		
	}*/

	
	@Override
	public Move getMove() {
	
		List<Piece> pieces = getPieces();

		TreeMap<Integer, List<Move>> weightedMoves = new TreeMap<Integer, List<Move>>();
		
		for(Piece piece : pieces) {
			List<Move> legalMoves = getLegalMoves(piece);
			
			for(Move move : legalMoves) {
				int moveWeight = 0;
				if(move.isKingMe()) {
					moveWeight++;
				}
				if(move.isJumpAttempt()) {
					moveWeight++;
				}
				
				List<Move> moves = (weightedMoves.containsKey(moveWeight) ? weightedMoves.get(moveWeight) : new ArrayList<Move>());
				moves.add(move);
				weightedMoves.put(moveWeight, moves);
			}
		}
		
		List<Move> legalMoves = weightedMoves.get(weightedMoves.lastKey());
		Random gen = new Random();
		int num = gen.nextInt(legalMoves.size());
		
		Move move = legalMoves.get(num);
		System.out.println("Move = " + move);
		
		return move;
	}
	
	
	@Override
	public boolean isControllable() {
		return false;
	}

}
