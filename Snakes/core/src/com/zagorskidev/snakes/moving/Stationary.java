package com.zagorskidev.snakes.moving;

import java.util.List;

import com.zagorskidev.snakes.snake.Segment;
import com.zagorskidev.snakes.util.Food;

public class Stationary implements Movable {

	@Override
	public boolean move(Segment moved, int[][] field, List<Food> food) {
		
		return false;
	}

}
