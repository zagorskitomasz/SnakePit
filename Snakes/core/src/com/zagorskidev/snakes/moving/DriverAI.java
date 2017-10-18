package com.zagorskidev.snakes.moving;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import com.zagorskidev.snakes.snake.Segment;
import com.zagorskidev.snakes.util.Direction;
import com.zagorskidev.snakes.util.Food;
import com.zagorskidev.snakes.util.Point;

public class DriverAI implements Movable {

	private Direction direction;
	private Food currTarget;
	
	private Deque<Food> lastTargets;
	
	public DriverAI(Direction direction) {
		
		this.direction = direction;
		this.currTarget = null;
		this.lastTargets = new LinkedList<>();
	}
	
	@Override
	public boolean move(Segment moved, int[][] field, List<Food> food) {
		
		Point newPosition;
		Direction targetDirection = null;;
		
		if(this.currTarget==null)
			currTarget = this.setTarget(moved, lastTargets, food);
		
		if(currTarget!=null && moved.getPosition().equals(currTarget.getPosition()))
			currTarget = null;
		
		if(currTarget!=null) {
			targetDirection = new Direction(currTarget.getX()-moved.getX(), currTarget.getY()-moved.getY());
			
			Point nextStep = new Point(moved.getX()+targetDirection.getXDir(), moved.getY()+targetDirection.getYDir());
			
			if(countMoves(moved.getPosition(), targetDirection, field)>0 &&
					countMoves(nextStep, Direction.turnLeft(targetDirection), field) +
					countMoves(nextStep, Direction.turnRight(targetDirection), field) +
					countMoves(nextStep, targetDirection, field)
					>10 ) {
				this.direction = targetDirection;
			}
			else {
				removeTarget();
				
				if(chooseDirection(moved, field))
					return true;
			}
		}
		else {
			if(chooseDirection(moved, field))
				return true;
		}
		
		newPosition = calculateNewPosition(moved);
		
		moved.setPosition(newPosition);
		this.moveStateOnField(moved, field);
		return false;
	}
	
	private void removeTarget() {
		
		lastTargets.addFirst(currTarget);
		currTarget.untarget();
		currTarget = null;
		if(lastTargets.size()>3)
			lastTargets.removeLast();
	}
	
	private boolean chooseDirection(Segment moved, int[][] field) {
		
		int movesStraight;
		int movesLeft;
		int movesRight;
		
		movesStraight = countMoves(moved.getPosition(), this.direction, field);
		movesLeft = countMoves(moved.getPosition(), Direction.turnLeft(this.direction), field);
		movesRight = countMoves(moved.getPosition(), Direction.turnRight(this.direction), field);
		
		if(movesLeft==0 && movesRight==0 && movesStraight==0)
			return true;
		
		if(movesLeft>=movesStraight && movesLeft>=movesRight)
			this.direction = Direction.turnLeft(this.direction);
		
		else if(movesRight>=movesStraight && movesRight>movesLeft)
			this.direction = Direction.turnRight(this.direction);
		
		return false;
	}
	
	private Food setTarget(Segment moved, Deque<Food> lastTargets, List<Food> food) {
		
		Food target = null;
		
		try {
			target = food
					.parallelStream()
					.filter(meal -> !lastTargets.contains(meal))
					.max((a, b) -> {
						return Double.compare(Point.distance(moved.getPosition(), b.getPosition())*(b.getTargeted()+1), 
								Point.distance(moved.getPosition(), a.getPosition())*(a.getTargeted()+1));
					})
					.get();
			
			target.target();
		}
		catch(NoSuchElementException e) {
			
			target = null;
		}
		return target;
	}
	
	private Point calculateNewPosition(Segment moved) {
		
		return new Point(moved.getPosition().getLat()+this.direction.getXDir(),
				moved.getPosition().getLon()+this.direction.getYDir());
	}
	
	private int countMoves(Point from, Direction direction, int[][] field) {
		
		int counter = 0;
		int iterX = from.getLat();
		int iterY = from.getLon();
		
		while(field[iterX+direction.getXDir()][iterY+direction.getYDir()]!=1) {
			counter++;
			iterX += direction.getXDir();
			iterY += direction.getYDir();
		}
		
		return counter;
	}
	
	public void foodEaten() {
		
		this.currTarget = null;
	}
}
