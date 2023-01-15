package domain.aliens;

import dataStructures.Location;

public class ShooterAlien implements Alien {
	private Location location;
	private String alienType;
	private Boolean isActive;
	
	
	public ShooterAlien(Location location) {
		super();
		this.location = location;
		this.alienType = "Shooter";
		this.isActive = true;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return this.location;
	}

	@Override
	public String getAlienType() {
		// TODO Auto-generated method stub
		return this.alienType;
	}

	@Override
	public Boolean getIsActive() {
		// TODO Auto-generated method stub
		return this.isActive;
	}

	@Override
	public void setLocation(Location l) {
		// TODO Auto-generated method stub
		this.location = l;
	}

	@Override
	public void setAlienType(String s) {
		// TODO Auto-generated method stub
		this.alienType = s;
	}

	@Override
	public void setIsActive(Boolean b) {
		// TODO Auto-generated method stub
		this.isActive = b;
	}

}
