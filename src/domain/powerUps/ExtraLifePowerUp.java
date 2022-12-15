package domain.powerUps;

import domain.Location;

public class ExtraLifePowerUp implements PowerUp{

	Location location;
	String powerUpType;
	
	public ExtraLifePowerUp(Location location) {
        this.location = location;
        this.powerUpType = "ExtraLife";
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
