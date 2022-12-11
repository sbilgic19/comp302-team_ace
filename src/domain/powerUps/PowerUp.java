package domain.powerUps;

import dataStructures.Location;

public interface PowerUp {
    
    void triggerEffect();
    String getPowerUpType();
    Location getLocation();
	
}
