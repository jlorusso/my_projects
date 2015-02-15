package com.jimcorp.checkers;

import java.awt.Point;
import java.io.Serializable;

public class CheckerSpaceModel implements Serializable {

	private static final long serialVersionUID = -2932046529008531723L;

//	private final Color color;
	private final boolean canHoldChecker;
	private final Point locationInGrid;
	
	private boolean hasChecker;
	private Piece checker;
	
	public CheckerSpaceModel(/*Color color,*/ Point locationInGrid, boolean canHoldChecker) {
//		this.color = color;
		this.locationInGrid = locationInGrid;
		this.canHoldChecker = canHoldChecker;
	}

	
	public boolean hasChecker() {
		return hasChecker;
	}

	protected Piece getChecker() {
		return checker;
	}
	
	protected void addChecker(Piece checker) {
		this.hasChecker = true;
		this.checker = checker;
		checker.setLocation(this.locationInGrid);
//		checker.setTargetLocation(null);
	}
	
	protected Piece removeChecker() {
		Piece removedChecker = this.checker;
		this.hasChecker = false;
		this.checker = null;
		
		return removedChecker;
	}
	
//	public Color getColor() {
//		return this.color;
//	}

	public Point getLocationInGrid() {
		return locationInGrid;
	}

	public boolean canHoldChecker() {
		return canHoldChecker;
	}

}
