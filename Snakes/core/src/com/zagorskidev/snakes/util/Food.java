package com.zagorskidev.snakes.util;

import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zagorskidev.snakes.moving.Stationary;
import com.zagorskidev.snakes.snake.Segment;
import com.zagorskidev.snakes.snake.Snake;

public class Food {

	private Segment segment;
	private boolean toKill;
	private int targeted;
	
	public Food(Point position, int[][] field) {
		
		this.segment = new Segment(position, new Stationary());
		field[position.getLat()][position.getLon()] = 2;
		this.toKill = false;
		this.targeted = 0;
	}
	
	public void show(ShapeRenderer renderer) {
		
		this.segment.show(renderer, Color.YELLOW);
	}
	
	public void checkCollision(List<Snake> snakes, int[][] field) {
		
		for(Snake snake : snakes) {
			if(snake.getHeadPos().equals(this.segment.getPosition())) {
				snake.grow(1);
				field[this.segment.getPosition().getLat()][this.segment.getPosition().getLon()] = 0;
				this.toKill = true;
				
				for(Snake hungry : snakes)
					hungry.foodEaten();
			}
		}
	}
	
	public boolean isToKill() {
		
		return this.toKill;
	}
	
	public Point getPosition() {
		
		return this.segment.getPosition();
	}
	
	public int getX() {
		
		return this.segment.getX();
	}
	
	public int getY() {
		
		return this.segment.getY();
	}
	
	public void target() {
		
		this.targeted++;
	}
	
	public void untarget() {
		
		if(this.targeted>0)
			this.targeted--;
	}
	
	public int getTargeted() {
		
		return this.targeted;
	}
}
