package domain.powerUps;

import dataStructures.Location;

public class ExtraTimePowerUp extends PowerUp{

	public ExtraTimePowerUp(Location location) {
        super(location);
        this.powerUpType = "ExtraTime";
    }
	
	 @Override
	 public void triggerEffect() {
		 super.triggerEffect();
	 }
	 
	 
	
}
