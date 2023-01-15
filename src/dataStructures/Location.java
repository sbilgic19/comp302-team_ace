package dataStructures;

import java.io.Serializable;

public class Location implements Serializable {
	private int locationX;
	private int locationY;
	
	
	
	
	
	public Location(int locationX, int locationY) {
		super();
		this.locationX = locationX;
		this.locationY = locationY;
	}
	
	public static double distance(Location location1, Location location2) {
		int x_dist = Math.abs(location1.getLocationX()- location2.getLocationX());
		int y_dist = Math.abs(location1.getLocationY()- location2.getLocationY());
		return Math.sqrt(Math.pow(x_dist, 2) + Math.pow(y_dist, 2));
	}
	
	public static int x_distance(Location location1, Location location2) {
		int x_dist = Math.abs(location1.getLocationX()- location2.getLocationX());
		return x_dist;
	}
	
	public static int y_distance(Location location1, Location location2) {
		int y_dist = Math.abs(location1.getLocationY()- location2.getLocationY());
		return y_dist;
	}

	public void moveLeft() {
		this.locationX--;
	}
	
	public void moveLeft(int n) {
		this.locationX = this.locationX-n;
	}
	
	public void moveRight() {
		this.locationX++;
	}
	
	public void moveRight(int n) {
		this.locationX = this.locationX+n;
	}
	
	public void moveUp() {
		this.locationY++;
	}
	
	public void moveUp(int n) {
		this.locationY = this.locationY+n;
	}
	
	
	public void moveDown() {
		this.locationY--;
	}
	
	public void moveDown(int n) {
		this.locationY = this.locationY-n;
	
	}
	
	
	public int getLocationX() {
		return locationX;
	}
	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}
	public int getLocationY() {
		return locationY;
	}
	public void setLocationY(int locationY) {
		this.locationY = locationY;
	}
	
	public void setLocation(int locationX, int locationY) {
		this.locationX = locationX;
		this.locationY = locationY;
	}
	
	

}
