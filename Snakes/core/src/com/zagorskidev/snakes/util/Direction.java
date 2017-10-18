package com.zagorskidev.snakes.util;

public class Direction {

	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	
	public static Direction turnLeft(Direction oldDirection) {
		
		if(oldDirection.getDirection()==LEFT)
			return new Direction(0, -1);
		else if(oldDirection.getDirection()==DOWN)
			return new Direction(1, 0);
		else if(oldDirection.getDirection()==RIGHT)
			return new Direction(0, 1);
		else //if(oldDirection.getDirection()==UP)
			return new Direction(-1, 0);
	}
	
	public static Direction turnRight(Direction oldDirection) {
		
		if(oldDirection.getDirection()==LEFT)
			return new Direction(0, 1);
		else if(oldDirection.getDirection()==DOWN)
			return new Direction(-1, 0);
		else if(oldDirection.getDirection()==RIGHT)
			return new Direction(0, -1);
		else //if(oldDirection.getDirection()==UP)
			return new Direction(1, 0);
	}
	
	private int xDir;
	private int yDir;
	
	public Direction(int x, int y) {
		this.xDir = x;
		this.yDir = y;
		
		this.reduce();
	}
	
	public int getXDir() {
		return this.xDir;
	}
	
	public int getYDir() {
		return this.yDir;
	}
	
	public int getDirection() {
		
		if(this.xDir==-1)
			return LEFT;
		else if(this.xDir==1)
			return RIGHT;
		else if(this.yDir==1)
			return UP;
		else
			return DOWN;
	}
	
	private void reduce() {
		
		if(Math.abs(this.xDir)>=Math.abs(this.yDir)) {
			this.xDir = Math.round(this.xDir/Math.abs(this.xDir));
			this.yDir = 0;
		}
		else {
			this.yDir = Math.round(this.yDir/Math.abs(this.yDir));
			this.xDir = 0;
		}
	}
}