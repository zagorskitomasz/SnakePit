package com.zagorskidev.snakes.snake;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zagorskidev.snakes.moving.DriverAI;
import com.zagorskidev.snakes.moving.Follower;
import com.zagorskidev.snakes.moving.Interactive;
import com.zagorskidev.snakes.moving.Movable;
import com.zagorskidev.snakes.util.Direction;
import com.zagorskidev.snakes.util.Food;
import com.zagorskidev.snakes.util.Point;

public class Snake {

	private Deque<Segment> snakeBody;
	private int length;
	private Color color;
	
	private boolean toKill;
	
	public Snake(int length, Color color, Point headPosition, Direction direction, Input input) {
		
		this.snakeBody = new LinkedList<>();
		this.length = length;
		this.color = color;
		
		this.toKill = false;
	
		Movable headDriver;
		if(input==null)
			headDriver = new DriverAI(direction);
		else
			headDriver = new Interactive(direction, input);
		
		Segment head = new Segment(headPosition, headDriver);
		this.snakeBody.addFirst(head);
		
		this.grow(this.length-1);
	}
	
	public boolean move(int[][] field, List<Food> food) {
		
		boolean collision = false;
		
		for(Segment s : this.snakeBody) {
			collision = s.move(field, food);
			if(collision)
				return collision;
		}
		
		return collision;
	}
	
	public void show(ShapeRenderer renderer) {
		
		for(Segment s : this.snakeBody) {
			s.show(renderer, this.color);
		}
	}
	
	public void kill(int[][] field) {
		
		for(Segment s : this.snakeBody) {
			s.kill(field);
		}
		
		this.toKill = true;
	}
	
	public boolean isToKill() {
		
		return this.toKill;
	}
	
	public void grow(int segmentsToAdd) {
		
		Segment previousSegment = this.snakeBody.getLast();
		
		for(int i=0; i<segmentsToAdd; i++) {
			
			Segment nextSegment = new Segment(previousSegment.getPosition(), new Follower(previousSegment));
			this.snakeBody.addLast(nextSegment);
			previousSegment = nextSegment;
		}
	}
	
	public Point getHeadPos() {
		
		return this.snakeBody.getFirst().getPosition();
	}
	
	public void foodEaten() {
		
		this.snakeBody.getFirst().foodEaten();
	}
}
