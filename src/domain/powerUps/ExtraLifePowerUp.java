package domain.powerUps;

import dataStructures.Location;

public class ExtraLifePowerUp extends PowerUp{

	public ExtraLifePowerUp(Location location) {
        super(location);
        this.powerUpType = "ExtraLife";
    }
	
	 @Override
	 public void triggerEffect() {
		 super.triggerEffect();
	 }
	 
	 
	
}
