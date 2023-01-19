package domain.powerUps;

import dataStructures.Location;

import java.io.Serializable;

public interface PowerUp extends Serializable {
    
    void triggerEffect();
    String getPowerUpType();
    Location getLocation();
	
}
