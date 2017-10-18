package com.zagorskidev.snakes.util;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.zagorskidev.snakes.moving.Stationary;
import com.zagorskidev.snakes.snake.Segment;

public class Wall {

	private List<Segment> segments;
	private Color color;
	
	public Wall(int[][] field, Color color) {
		
		this.segments = new LinkedList<>();
		this.color = color;
		
		for(int i=0; i<field.length; i++) {
			this.createSegment(new Point(i, 0), field);
			this.createSegment(new Point(i, field[i].length-1), field);
		}
		
		for(int i=1; i<field[i].length-1; i++) {
			this.createSegment(new Point(0, i), field);
			this.createSegment(new Point(field.length-1, i), field);
		}
	}
	
	public Wall(int[][] field, Point lowerLeft, Point upperRight, Color color) {
		
		this.segments = new LinkedList<>();
		this.color = color;
		
		for(int i=lowerLeft.getLat(); i<=upperRight.getLat(); i++) {
			for(int j=lowerLeft.getLon(); j<=upperRight.getLon(); j++) {
				this.createSegment(new Point(i, j), field);
			}
		}
	}
	
	private void createSegment(Point point, int[][] field) {
		this.segments.add(new Segment(point, new Stationary()));
		field[point.getLat()][point.getLon()] = 1;
	}
	
	public void show(ShapeRenderer renderer, int[][] field) {
		
		for(Segment s : this.segments) {
			s.show(renderer, this.color);
			field[s.getX()][s.getY()] = 1;
		}
	}
}
