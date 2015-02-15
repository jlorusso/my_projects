package com.jimcorp.checkers;

import java.awt.Point;

public final class Move {

	private final Piece piece;
	private final Point oldLocation;
	private final Point newLocation;
	private final boolean jumpAttempt;
	private final Point jumpedPoint;
//	private final Piece jumpedPiece;
	private final boolean kingMe;

	public Move(Piece piece, Point oldLocation, Point newLocation) {
		this(piece, oldLocation, newLocation, false, false, null);
	}
	
	public Move(Piece piece, Point oldLocation, Point newLocation, boolean kingMe) {
		this(piece, oldLocation, newLocation, kingMe, false, null);
	}
	
/*	public Move(Piece piece, Point oldLocation, Point newLocation, boolean jumpAttempt, Point jumpedPoint) {
		this(piece, oldLocation, newLocation, false, jumpAttempt, jumpedPoint);
	}*/
	
	public Move(Piece piece, Point oldLocation, Point newLocation, boolean kingMe, boolean jumpAttempt, Point jumpedPoint) {
		this.piece = piece;
		this.oldLocation = oldLocation;
		this.newLocation = newLocation;
		this.kingMe = kingMe;
		this.jumpAttempt = jumpAttempt;		
		this.jumpedPoint = jumpedPoint;
//		this.jumpedPiece = jumpedPiece;
	}
	
	public Piece getPiece() {
		return piece;
	}

	public Point getOldLocation() {
		return oldLocation;
	}

	public Point getNewLocation() {
		return newLocation;
	}

	public boolean isJumpAttempt() {
		return jumpAttempt;
	}


	public Point getJumpedPoint() {
		return jumpedPoint;
	}


//	public Piece getJumpedPiece() {
//		return jumpedPiece;
//	}


	public boolean isKingMe() {
		return kingMe;
	}
	
	
/*	protected void setKinged(boolean kingMe) {
		this.kingMe = kingMe;
	}*/
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (jumpAttempt ? 1231 : 1237);
		result = prime * result
				+ ((jumpedPoint == null) ? 0 : jumpedPoint.hashCode());
		result = prime * result + (kingMe ? 1231 : 1237);
		result = prime * result
				+ ((newLocation == null) ? 0 : newLocation.hashCode());
		result = prime * result
				+ ((oldLocation == null) ? 0 : oldLocation.hashCode());
		result = prime * result + ((piece == null) ? 0 : piece.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Move other = (Move) obj;
		if (jumpAttempt != other.jumpAttempt)
			return false;
		if (jumpedPoint == null) {
			if (other.jumpedPoint != null)
				return false;
		} else if (!jumpedPoint.equals(other.jumpedPoint))
			return false;
		if (kingMe != other.kingMe)
			return false;
		if (newLocation == null) {
			if (other.newLocation != null)
				return false;
		} else if (!newLocation.equals(other.newLocation))
			return false;
		if (oldLocation == null) {
			if (other.oldLocation != null)
				return false;
		} else if (!oldLocation.equals(other.oldLocation))
			return false;
		if (piece == null) {
			if (other.piece != null)
				return false;
		} else if (!piece.equals(other.piece))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String str = "";
		
		str = String.format("%s piece moved From[%s,%s] To[%s,%s]%s", piece.getImageName(), oldLocation.x, 
				oldLocation.y, newLocation.x, newLocation.y, (jumpedPoint != null ? " and jumped enemy at [" +
				jumpedPoint.x + "," + jumpedPoint.y + "]" : ""));
		
		return str;
	}
}
