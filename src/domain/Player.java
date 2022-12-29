package domain;

import dataStructures.Location;

import java.io.Serializable;

public class Player implements Serializable {

	private Location location;
	private int lives;
	private boolean isKeyTaken = false;

	public Player() {
		this.lives = 3;
	}

	public Player(int lives) {
		this.lives = lives;
	}
	public Player(int x, int y) {
		super();
		this.location = new Location(x,y);
    this.lives = 3;
	}
	
	public void takeKey() {
		this.isKeyTaken = true;
	}
	
	
	public boolean getIsKeyTaken() {
		return this.isKeyTaken;
	}
	

	public Location getLocation() {
		return location;
	}
	

	public int getLives() 
	{
		return lives;
	}
	
	/**
	 * Sets the lives of the Player.
	 * 
	 * @param lives integer to be set.
	 */
	public void setLives(int lives) 
	{
		this.lives = lives;
	}
	
	/**
	 * Decrease the number of lives of the Player by 1.
	 */
	public void decreaseLives()
	{
		if (lives > 0)
		{
			this.lives -= 1;
		}
	}
	
	/**
	 * Increase the number of lives of th ePLayer by 1.
	 */
	public void increaseLives()
	{
		this.lives += 1;
	}

}
