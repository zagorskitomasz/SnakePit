package com.zagorskidev.snakes.moving;

import java.util.List;

import com.zagorskidev.snakes.snake.Segment;
import com.zagorskidev.snakes.util.Food;
import com.zagorskidev.snakes.util.Point;

public interface Movable {

	public boolean move(Segment moved, int[][] field, List<Food> food);
	
	default void moveStateOnField(Segment moved, int[][] field) {

		this.setFieldState(moved.getPreviousPosition(), field, 0);
		this.setFieldState(moved.getPosition(), field, 1);
	}
	
	default void setFieldState(Point point, int[][] field, int state) {
		
		field[point.getLat()][point.getLon()] = state;
	}
}
