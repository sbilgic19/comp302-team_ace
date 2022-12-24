package domain.aliens;

import dataStructures.Location;

public class TimeWastingAlien implements Alien{
	private Location location;
	private String alienType;
	private Boolean isActive;
	
	
	

	public TimeWastingAlien(Location location) {
		super();
		this.location = location;
		this.alienType = "Time Wasting";
		this.isActive = false;
	}
	

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public void setAlienType(String alienType) {
		this.alienType = alienType;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	


	public Boolean getIsActive() {
		return isActive;
	}

	@Override
	public Location getLocation() {
		return this.location;
	}

	@Override
	public String getAlienType() {
		return this.alienType;
	}
	
	
	
	
	
	

	

}
