package domain.powerUps;

import domain.Player;
import dataStructures.Location;

public class ExtraLifePowerUp implements PowerUp{

	Location location;
	String powerUpType;
	PowerUpBehavior behavior;
	
	public ExtraLifePowerUp(Location location, Player player) {
        this.location = location;
        this.powerUpType = "ExtraLife";
		this.behavior = new IncreaseLifeBehavior(player);
    }
	
	 @Override
	 public void triggerEffect() {
		behavior.performBehavior();
		 
	 }
	 
	 public String getPowerUpType() {
		 return this.powerUpType;
	 }
	 public Location getLocation() {
		 return this.location;
	 }
	 
	 
	
}
