package domain.powerUps;

import dataStructures.Location;

public class ExtraTimePowerUp implements PowerUp{

	Location location;
	String powerUpType;
	PowerUpBehavior behaviour;
	
	public ExtraTimePowerUp(Location location) {
        this.location = location;
        this.powerUpType = "ExtraTime";
		this.behaviour = new IncreaseTimeBehavior();
    }
	public ExtraTimePowerUp(){
		this.powerUpType = "ExtraTime";
		this.behaviour = new IncreaseTimeBehavior();
	}
	
	 @Override
	 public void triggerEffect() {
		behaviour.performBehavior();
	 }
	 
	 public String getPowerUpType() {
		 return this.powerUpType;
	 }
	 public Location getLocation() {
		 return this.location;
	 }
	 
	 
	
}
