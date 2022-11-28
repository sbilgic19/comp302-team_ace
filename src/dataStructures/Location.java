package dataStructures;

public class Location { // Data structure class for location
	private int locationX;
	private int locationY;
	
	
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
	public void changeLocationX(int locationX) {
		this.locationX = locationX;
	}
	public int getLocationY() {
		return locationY;
	}
	public void changeLocationY(int locationY) {
		this.locationY = locationY;
	}
	
	public void changeLocation(int locationX, int locationY) {
		this.locationX = locationX;
		this.locationY = locationY;
	}
	
	

}
