package domain.powerUps;

import dataStructures.Location;

public class PowerUp {

	protected Location location;
    protected String powerUpType;
    
    public PowerUp() {}
    
    public PowerUp(Location location) {
        this.location = location;
    }

    public PowerUp(Location location, String powerUpType) {
        this.location = location;
        this.powerUpType = powerUpType;
    }
    
    public void triggerEffect() {}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getPowerUpType() {
		return powerUpType;
	}

	public void setPowerUpType(String powerUpType) {
		this.powerUpType = powerUpType;
	}
    
    
	
}
