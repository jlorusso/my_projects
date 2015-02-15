package com.jimcorp.checkers;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CheckerBoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final Map<Point, CheckerSpacePanel> spaceMap;
	private Timer timer;
	
	
	public CheckerBoardPanel(Color color1, Color color2) {
		this(color1, color2, 8, 8);
	}
	
	public CheckerBoardPanel(Color color1, Color color2, int rows, int cols) {

		spaceMap = new HashMap<Point, CheckerSpacePanel>(rows*cols);
		setLayout(new GridLayout(rows, cols, 2, 2));
		
		List<Color> colors = new ArrayList<Color>(2);
		colors.add(color1);
		colors.add(color2);
		
		int colorIndex = 0;
		for(int i=0; i<rows; i++) {
			for(int j=0; j<cols; j++) {
				Point locationInGrid = new Point(i, j);
				CheckerSpacePanel checkerSpacePanel = new CheckerSpacePanel(colors.get(colorIndex), locationInGrid);
				spaceMap.put(new Point(i, j), checkerSpacePanel);
				add(checkerSpacePanel);
				colorIndex = (colorIndex >= 1 ? 0 : 1);
			}
			colorIndex = (colorIndex >= 1 ? 0 : 1);
		}
		
		setBorder(new LineBorder(Color.BLACK, 5));
	}
	
	
	public void moveChecker(Move move) {
		Point newLocation = move.getNewLocation();
		Point oldLocation = move.getOldLocation();
		
		CheckerSpacePanel oldSpace = getSpaceAtPoint(oldLocation);
		CheckerSpacePanel newSpace = getSpaceAtPoint(newLocation);
		CheckerPanel checker = oldSpace.getChecker();
		
		if(move.isJumpAttempt()) {
			CheckerSpacePanel jumpedSpace = getSpaceAtPoint(move.getJumpedPoint());
			jumpedSpace.removeChecker();
		}
			
		if(move.isKingMe()) {
			checker.setKinged(true);
		}
		
		newSpace.add(checker);
	}
	
	

	
	public void highlightLegalMoves(List<Move> legalMoves, boolean multiJump) {
		
		timer = new Timer(500, new TimerListener(legalMoves));
		timer.start();
	}
	
	
	public void stopHighlight() {
		if(timer != null) {
			for(ActionListener listener : timer.getActionListeners()) {
				if(listener instanceof TimerListener) {
					TimerListener timerListener = (TimerListener) listener;
					timerListener.stopTimer();
				}
			}
		}
	}
	
	
	public CheckerSpacePanel getSpaceAtPoint(Point point) {
		CheckerSpacePanel checkerSpacePanel = null;
		
		if(spaceMap.containsKey(point)) {
			checkerSpacePanel = spaceMap.get(point);
		}
		
		return checkerSpacePanel;
	}
	
	
	public Map<Point, CheckerSpacePanel> getCheckerSpaceMap() {
		return spaceMap;
	}
	
	public Collection<CheckerSpacePanel> getCheckerSpacePanels() {
		return spaceMap.values();
	}
	
	
	public List<CheckerPanel> getCheckerPanels() {
		List<CheckerPanel> checkers = new ArrayList<CheckerPanel>();
		
		for(CheckerSpacePanel checkerSpacePanel : spaceMap.values()) {
			CheckerPanel checker = checkerSpacePanel.getChecker();
			if(checker != null) {
				checkers.add(checker);
			}
		}
		
		return checkers;
	}
	
	
	private class TimerListener implements ActionListener {

		private int count = 0;
		private Border originalBorder;
		private List<Move> moves;
		
		public TimerListener(List<Move> aMoves) {
			moves = aMoves;
		}
		
		public void stopTimer() {
			if(timer != null) {
				timer.stop();
				for(Move move : moves) {
					Point newLocation = move.getNewLocation();
					CheckerSpacePanel space = getSpaceAtPoint(newLocation);
					space.setBorder(originalBorder);
				}
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			count++;
			
			for(Move move : moves) {
				Point newLocation = move.getNewLocation();
				CheckerSpacePanel space = getSpaceAtPoint(newLocation);
				originalBorder = getSpaceAtPoint(move.getOldLocation()).getBorder();
				
				if(count%2 == 1) {
					Border newBorder = BorderFactory.createLineBorder(Color.RED,5);
					if(move.isKingMe())
						newBorder = BorderFactory.createLineBorder(Color.MAGENTA,5);
					if(move.isJumpAttempt()) 
						newBorder = BorderFactory.createLineBorder(Color.BLUE,5);
					
					space.setBorder(newBorder);
				}
				else
					space.setBorder(originalBorder);
			}
			
		}
		
	}
}
