package domain.aliens;

import dataStructures.Location;

public class TimeWastingAlien implements Alien{
	private Location location;
	private String alienType;
	private Boolean isActive;
	

	@Override
	public Location getLocation() {
		return this.location;
	}

	@Override
	public String getAlienType() {
		return this.alienType;
	}
	
	
	
	

	

}
