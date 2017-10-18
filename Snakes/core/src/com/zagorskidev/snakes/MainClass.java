package com.zagorskidev.snakes;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;
import com.zagorskidev.snakes.snake.Snake;
import com.zagorskidev.snakes.util.Direction;
import com.zagorskidev.snakes.util.Food;
import com.zagorskidev.snakes.util.Point;
import com.zagorskidev.snakes.util.Wall;

public class MainClass extends ApplicationAdapter {

	private ShapeRenderer renderer;
	
	private List<Snake> snakes;
	private List<Wall> walls;
	private List<Food> food;
	int[][] field;
	
	private int snakeLength;
	private Random gen;
	
	private long lastMove;
	private int moveInterval = 50;
	
	private long lastFood;
	private int foodInterval = 1000;
	
	@Override
	public void create () {
		
		renderer = new ShapeRenderer();
		gen = new Random();
		
		snakes = new LinkedList<>();
		walls = new LinkedList<>();
		food = new LinkedList<>();
		
		field = new int[128][96];
		
		snakeLength = 8;
		
		walls.add(new Wall(field, Color.GRAY));
		
		snakes.add(new Snake(snakeLength, Color.GREEN, new Point(64, 10), new Direction(0, 1), Gdx.input));
		
		for(int i=0; i<3; i++)
			snakes.add(new Snake(snakeLength, Color.PURPLE, new Point(gen.nextInt(30)+3, gen.nextInt(90)+3), new Direction(1, 0), null));
		for(int i=0; i<3; i++)
			snakes.add(new Snake(snakeLength, Color.RED, new Point(gen.nextInt(30)+90, gen.nextInt(90)+3), new Direction(-1, 0), null));
		
		lastMove = TimeUtils.millis()+3000;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(TimeUtils.timeSinceMillis(lastMove)>moveInterval) {
			
			snakes.forEach(snake -> {
				if(snake.move(field, food))
					snake.kill(field);
			});
			
			lastMove = TimeUtils.millis();
		}
		
		if(TimeUtils.timeSinceMillis(lastFood)>foodInterval) {
			
			food.add(new Food(new Point(gen.nextInt(126)+1, gen.nextInt(94)+1),field));
			lastFood = TimeUtils.millis();
		}
		
		food.forEach(food -> food.checkCollision(snakes, field));

		walls.forEach(wall -> wall.show(renderer, field));
		snakes.forEach(snake -> snake.show(renderer));
		food.forEach(food -> food.show(renderer));
		
		snakes.removeIf(snake -> snake.isToKill());
		food.removeIf(food -> food.isToKill());
	}
	
	@Override
	public void dispose () {
		renderer.dispose();
	}
}
