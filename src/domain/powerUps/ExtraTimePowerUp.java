package domain.powerUps;

import domain.Location;

public class ExtraTimePowerUp implements PowerUp{

	Location location;
	String powerUpType;
	
	public ExtraTimePowerUp(Location location) {
        this.location = location;
        this.powerUpType = "ExtraTime";
    }
	
	 @Override
	 public void triggerEffect() {
	 }
	 
	 public String getPowerUpType() {
		 return this.powerUpType;
	 }
	 public Location getLocation() {
		 return this.location;
	 }
	 
	 
	
}
