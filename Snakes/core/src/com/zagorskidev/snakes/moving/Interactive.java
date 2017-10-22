package com.zagorskidev.snakes.moving;

import java.util.List;

import com.badlogic.gdx.Input;
import com.zagorskidev.snakes.snake.Segment;
import com.zagorskidev.snakes.util.Direction;
import com.zagorskidev.snakes.util.Food;
import com.zagorskidev.snakes.util.Point;

public class Interactive implements Movable {

	private Direction direction;
	private Input input;
	
	public Interactive(Direction direction, Input input) {
		
		this.direction = direction;
		this.input = input;
	}
	
	@Override
	public boolean move(Segment moved, int[][] field, List<Food> food) {
		
		if(input.isKeyPressed(Input.Keys.W))
			pressedUp();
		else if(input.isKeyPressed(Input.Keys.S))
			pressedDown();
		else if(input.isKeyPressed(Input.Keys.A))
			pressedLeft();
		else if(input.isKeyPressed(Input.Keys.D))
			pressedRight();
		
		if(field[moved.getX()+this.direction.getXDir()]
				[moved.getY()+this.direction.getYDir()]==1)
			return true;
		
		Point newPosition = calculateNewPosition(moved);
		
		moved.setPosition(newPosition);
		this.moveStateOnField(moved, field);
		return false;
	}
	
	private void pressedUp() {
		if(this.direction.getDirection()==Direction.LEFT)
			this.direction=Direction.turnRight(this.direction);
		else if(this.direction.getDirection()==Direction.RIGHT)
			this.direction=Direction.turnLeft(this.direction);
	}
	
	private void pressedDown() {
		if(this.direction.getDirection()==Direction.LEFT)
			this.direction=Direction.turnLeft(this.direction);
		else if(this.direction.getDirection()==Direction.RIGHT)
			this.direction=Direction.turnRight(this.direction);
	}
	
	private void pressedLeft() {
		if(this.direction.getDirection()==Direction.UP)
			this.direction=Direction.turnLeft(this.direction);
		else if(this.direction.getDirection()==Direction.DOWN)
			this.direction=Direction.turnRight(this.direction);
	}
	
	private void pressedRight() {
		if(this.direction.getDirection()==Direction.UP)
			this.direction=Direction.turnRight(this.direction);
		else if(this.direction.getDirection()==Direction.DOWN)
			this.direction=Direction.turnLeft(this.direction);
	}
	
	public Point calculateNewPosition(Segment moved) {
		
		return new Point(moved.getX()+this.direction.getXDir(),
				moved.getY()+this.direction.getYDir());
	}
}
