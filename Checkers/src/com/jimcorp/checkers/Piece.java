package com.jimcorp.checkers;

import java.awt.Image;
import java.awt.Point;

public class Piece implements Comparable<Piece> {

	protected static enum AttackDirections {NORTH, SOUTH, BOTH};
	
	private final Image image;
	private final String imageName;
	private final Player player;
	private boolean isKing;
	private Point location;
	private Enum<AttackDirections> attackDirection;
	private Enum<AttackDirections> startingSide;
	
	public Piece (Player player, Point location) {
		this(player, location, false);
	}
	
	public Piece (Player player, Point location, boolean isKing) {
		this.player = player;
		this.image = player.getImage();
		this.imageName = player.getImageName();
		this.setLocation(location);
		this.setKing(isKing);
		
		if(this.player.getPlayerID() == 1) {
			setStartingSide(AttackDirections.NORTH);
			setAttackDirection(AttackDirections.SOUTH);
		}
		else {
			setStartingSide(AttackDirections.SOUTH);
			setAttackDirection(AttackDirections.NORTH);
		}
	}

	public boolean isTargetEnemy(Piece targetPiece) {
		if(this.getStartingSide().equals(targetPiece.getStartingSide())) {
			return false;
		}
		else {
			return true;
		}
	}

	public Image getImage() {
		return image;
	}
	
	public String getImageName() {
		return imageName;
	}

	public Player getPlayer() {
		return this.player;
	}
	
	
	public boolean isKing() {
		return isKing;
	}

	public void setKing(boolean isKing) {
		this.isKing = isKing;
		if(isKing) {
			this.setAttackDirection(AttackDirections.BOTH);
		}
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}


	public Enum<AttackDirections> getAttackDirection() {
		return attackDirection;
	}

	private void setAttackDirection(Enum<AttackDirections> attackDirection) {
		this.attackDirection = attackDirection;
	}

	public Enum<AttackDirections> getStartingSide() {
		return startingSide;
	}

	private void setStartingSide(Enum<AttackDirections> startingSide) {
		this.startingSide = startingSide;
	}

	@Override
	public int compareTo(Piece otherPiece) {
		int diffX = location.x - otherPiece.getLocation().x;
		if(diffX == 0) {
			return location.y - otherPiece.getLocation().y;
		}
		else {
			return diffX;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((attackDirection == null) ? 0 : attackDirection.hashCode());
		result = prime * result + ((image == null) ? 0 : image.hashCode());
		result = prime * result
				+ ((imageName == null) ? 0 : imageName.hashCode());
		result = prime * result + (isKing ? 1231 : 1237);
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result
				+ ((startingSide == null) ? 0 : startingSide.hashCode());
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
		Piece other = (Piece) obj;
		if (attackDirection == null) {
			if (other.attackDirection != null)
				return false;
		} else if (!attackDirection.equals(other.attackDirection))
			return false;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		if (imageName == null) {
			if (other.imageName != null)
				return false;
		} else if (!imageName.equals(other.imageName))
			return false;
		if (isKing != other.isKing)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (player == null) {
			if (other.player != null)
				return false;
		} else if (!player.equals(other.player))
			return false;
		if (startingSide == null) {
			if (other.startingSide != null)
				return false;
		} else if (!startingSide.equals(other.startingSide))
			return false;
		return true;
	}
	
}
