package domain.aliens;

import ApplicationLogic.TimeWastingAlienBehaviourStrategy;
import ApplicationLogic.TimeWastingAlienBehaviourStrategyA;
import ApplicationLogic.TimeWastingAlienBehaviourStrategyB;
import ApplicationLogic.TimeWastingAlienBehaviourStrategyC;
import dataStructures.Location;

public class TimeWastingAlien implements Alien{
	private Location location;
	private String alienType;
	private Boolean isActive;
	
	private TimeWastingAlienBehaviourStrategy behaviourA;
	private TimeWastingAlienBehaviourStrategy behaviourB;
	private TimeWastingAlienBehaviourStrategy behaviourC;
	
	private int currentTime;
	private int levelTime;
	
	public TimeWastingAlien(Location location) {
		super();
		this.location = location;
		this.isActive = true;
		
		behaviourA = new TimeWastingAlienBehaviourStrategyA();
		behaviourB = new TimeWastingAlienBehaviourStrategyB();
		behaviourC = new TimeWastingAlienBehaviourStrategyC();
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
	
	public TimeWastingAlienBehaviourStrategy[] getBehaviours() {
		TimeWastingAlienBehaviourStrategy[] behavioursArray = 
			{behaviourA, behaviourB, behaviourC};
		return behavioursArray;
	}
	
	private TimeWastingAlienBehaviourStrategy determineBehaviourStrategy() {
		if ((currentTime * 100) / (float) levelTime < 30) {
			System.out.println("A");
			return behaviourA;	
		}
		
		else if ((currentTime * 100)/ (float) levelTime > 70) {
			System.out.println("B");
			return behaviourB;
		}
		
		else {
			System.out.println("C");
			return behaviourC;
		}
	}	

	public void setLevelTime(int levelTime) {
		this.levelTime = levelTime;
	}
	
	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
		isActive = determineBehaviourStrategy().changeLocationOfTheKey();
	}
}