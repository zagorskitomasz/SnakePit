package com.zagorskidev.snakes.snake;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.zagorskidev.snakes.moving.DriverAI;
import com.zagorskidev.snakes.moving.Movable;
import com.zagorskidev.snakes.util.Food;
import com.zagorskidev.snakes.util.Point;

public class Segment {

	private Point currentPosition;
	private Point previousPosition;
	
	private Movable mover;
	
	public Segment(Point position, Movable mover) {
		
		this.currentPosition = position;
		this.previousPosition = position;
		
		this.mover = mover;
	}
	
	public boolean move(int[][] field, List<Food> food) {
		
		return this.mover.move(this, field, food);
	}
	
	public void setPosition(Point newPosition) {
		
		this.previousPosition = this.currentPosition;
		this.currentPosition = newPosition;
	}

	public Point getPreviousPosition() {
		
		return this.previousPosition;
	}
	
	public Point getPosition() {
		
		return this.currentPosition;
	}
	
	public int getX() {
		
		return this.getPosition().getLat();
	}
	
	public int getY() {
		
		return this.getPosition().getLon();
	}
	
	public void kill(int[][] field) {
		
		field[this.getX()][this.getY()] = 0;
	}
	
	public void show(ShapeRenderer renderer, Color color) {
		
		renderer.setColor(color);
		renderer.begin(ShapeType.Filled);
		renderer.circle(this.getX()*5+2, this.getY()*5+2, 3);
		renderer.end();
	}
	
	public void foodEaten() {
		
		if(this.mover instanceof DriverAI)
			((DriverAI)this.mover).foodEaten();
	}
}
