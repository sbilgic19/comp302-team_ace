package domain.aliens;

import java.util.ArrayList;

import ApplicationLogic.AlienBehaviourStrategy;
import ApplicationLogic.AlienBehaviourStrategyA;
import ApplicationLogic.AlienBehaviourStrategyB;
import ApplicationLogic.AlienBehaviourStrategyC;
import dataStructures.Location;
import domain.Key;
import domain.RoomObject;

public class TimeWastingAlien implements Alien{
	private Key key;
	private ArrayList<RoomObject> objectList;
	private Location location;
	private String alienType;
	private Boolean isActive;
	private Boolean isCalledBefore = false;
	
	private AlienBehaviourStrategy strategyA;
	private AlienBehaviourStrategy strategyB;
	private AlienBehaviourStrategy strategyC;
	
	private int initializeTime;
	private int currentTime;
	private int levelTime;
	private Location doorLocation;
	
	public TimeWastingAlien(ArrayList<RoomObject> objectList, Location location, Key key, 
			Location doorLocation) {
		super();
		this.location = location;
		this.key = key;
		this.alienType = "Time Wasting";
		this.isActive = true;
		this.objectList = objectList;
		this.doorLocation = doorLocation;
		
		strategyA = new AlienBehaviourStrategyA(objectList, key, doorLocation);
		strategyB = new AlienBehaviourStrategyB(objectList, key, doorLocation);
		strategyC = new AlienBehaviourStrategyC();
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public void setAlienType(String alienType) {
		this.alienType = alienType;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}

	@Override
	public Location getLocation() {
		return this.location;
	}

	@Override
	public String getAlienType() {
		return this.alienType;
	}
	
	private AlienBehaviourStrategy determineBehaviourStrategy() {
		if ((currentTime * 100) / (float) levelTime < 30) {
			System.out.println("A");
			return strategyA;	
		}
		
		else if ((currentTime * 100)/ (float) levelTime > 70) {
			System.out.println("B");
			return strategyB;
		}
		
		else {
			System.out.println("C");
			return strategyC;
		}
	}	

	public void setLevelTime(int levelTime) {
		this.levelTime = levelTime;
	}
	
	public void setCurrentTime(int currentTime) {
		if (isCalledBefore == false) {
			this.initializeTime = currentTime;
		}
		
		this.currentTime = currentTime;
		isActive = determineBehaviourStrategy().changeLocationOfTheKey();
	}
}