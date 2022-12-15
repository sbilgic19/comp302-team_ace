package domain;

import dataStructures.Location;

public class Key {
	private Location location;
	private boolean isTaken = false;
	
	public Key(Location location) {
		super();
		this.location = location;
		System.out.println("key is created on " + location.getLocationX() +" " + location.getLocationY() );
	}

	public void taken(Player p ) {
		p.takeKey();
		this.isTaken =true;
	}
	
	public boolean getIsTaken() {
		return this.isTaken;
	}
	
	public void put(Location l) {
		this.location = l;
	}


	public Location getLocation() {
		return location;
	}
}