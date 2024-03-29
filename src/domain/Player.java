package domain;

import dataStructures.Location;
import domain.powerUps.PowerUp;
import domain.powerUps.PowerUpFactory;

import java.io.*;
import java.util.ArrayList;

public class Player implements Serializable {

	private Location location;
	private int lives;
	private boolean isKeyTaken = false;
	private boolean isProtected = false;
	private boolean isThrowing = false;
	private final ArrayList<PowerUp> bag = new ArrayList<PowerUp>();

	public Player() {
		this.lives = 3;
		this.location = new Location(0,5);
		GameInfo.getInstance().setPlayerLocation(location);
	}

	public Player(int lives) {
		this.lives = lives;
		this.location = new Location(0,5);
	}
	public Player(Location location) {
		super();
		this.location = location;
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

	public boolean isProtected() {
		return this.isProtected;
	}
	public boolean isThrowing(){return this.isThrowing;}
	public void setIsThrowing(boolean isThrowing){ this.isThrowing = isThrowing; }

	public void setIsProtected(boolean isProtected) {
		this.isProtected = isProtected;
	}

	/**
	 * Adds the given powerUp to the bag.
	 * @param powerUp powerUp to add to the bag.
	 */
	public void addToBag(PowerUp powerUp){
		PowerUp copy = PowerUpFactory.getInstance().getPowerUp(powerUp.getPowerUpType(), powerUp.getLocation());
		bag.add(copy);
	}

	/**
	 * Getter for bag.
	 * @return the bag of the player.
	 */
	public ArrayList<PowerUp> getBag(){
		return bag;
	}

	public PowerUp getPowerUp(String powerUpName){
		for (int i = 0 ; i < bag.size(); i++){
			if (bag.get(i).getPowerUpType().equalsIgnoreCase(powerUpName)){
				return bag.get(i);
			}
		}
		return null;
	}

	public void removePowerUp(String powerUpName){
		for (int i = 0 ; i < bag.size(); i++){
			if (bag.get(i).getPowerUpType().equalsIgnoreCase(powerUpName)){
				bag.remove(i);
				return;
			}
		}
	}


	public boolean isContains(String powerUpName) {
		for (int i = 0 ; i < bag.size(); i++){
			if (bag.get(i).getPowerUpType().equalsIgnoreCase(powerUpName)){
				return true;
			}
		}
		return false;
	}
	public void setLocation(Location location){this.location = location; }
	public void setKeyTaken(boolean isKeyTaken){this.isKeyTaken = isKeyTaken;}

}
