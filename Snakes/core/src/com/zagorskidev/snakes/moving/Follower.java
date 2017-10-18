package com.zagorskidev.snakes.moving;

import java.util.List;

import com.zagorskidev.snakes.snake.Segment;
import com.zagorskidev.snakes.util.Food;

public class Follower implements Movable {

	private Segment previousSegment;
	
	public Follower(Segment previousSegment) {
		
		this.previousSegment = previousSegment;
	}
	
	@Override
	public boolean move(Segment moved, int[][] field, List<Food> food) {
		
		moved.setPosition(this.previousSegment.getPreviousPosition());
		
		this.moveStateOnField(moved, field);
		
		return false;
	}
}
