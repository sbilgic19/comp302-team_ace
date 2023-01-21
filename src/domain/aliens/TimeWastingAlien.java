package domain.aliens;

import ApplicationLogic.TimeWastingAlienBehaviourStrategy;
import ApplicationLogic.TimeWastingAlienBehaviourStrategyA;
import ApplicationLogic.TimeWastingAlienBehaviourStrategyB;
import ApplicationLogic.TimeWastingAlienBehaviourStrategyC;
import dataStructures.Location;

public class TimeWastingAlien implements Alien{
	private Location location;
	private String alienType = "TimeWasting";
	private Boolean isActive;
	
	private final TimeWastingAlienBehaviourStrategy behaviourA;
	private final TimeWastingAlienBehaviourStrategy behaviourB;
	private final TimeWastingAlienBehaviourStrategy behaviourC;

	
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
	

	
	public void triggerAction(TimeWastingAlienBehaviourStrategy currentBehaviour) {
		isActive = currentBehaviour.changeLocationOfTheKey();
	}
}