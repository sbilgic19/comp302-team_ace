package domain.powerUps;

import domain.Location;

public interface PowerUp {
    
    void triggerEffect();
    String getPowerUpType();
    Location getLocation();
	
}
