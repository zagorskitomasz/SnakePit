package com.zagorskidev.snakes.util;

public class Point {

	public static double distance(Point one, Point two) {
		
		return Math.sqrt(Math.pow(Math.abs(one.getLat()-two.getLat()), 2) 
				+ Math.pow(Math.abs(one.getLon()-two.getLon()), 2));
	}
	
	private int latitude;
	private int longitude;
	
	public Point(int latitude, int longitude) {
		
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Point(double latitude, double longitude) {
		
		this.latitude = (int)latitude;
		this.longitude = (int)longitude;
	}
	
	public int getLat() {
		return this.latitude;
	}
	
	public int getLon() {
		return this.longitude;
	}
	
	public boolean equals(Object other) {
		
		return this.latitude==((Point)other).latitude && this.longitude==((Point)other).longitude;
	}
}