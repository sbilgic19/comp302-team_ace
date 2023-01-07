package domain;

import dataStructures.Location;

public class Key {
	/*
	 Class Summary
The Key class represents a key in a game or application. It has a location, which is represented by a Location object, and a boolean flag isTaken that indicates whether the key has been taken by a player or not.

Class Methods
public Key(Location location)
This is the constructor for the Key class. It takes a Location object as an argument and sets the location field of the Key object to the given location. It also prints a message indicating that a key has been created at the given location.

public void taken(Player p)
This method is called when a player takes the key. It takes a Player object as an argument and calls the takeKey() method on it. It also sets the isTaken flag to true.

public boolean getIsTaken()
This method returns the value of the isTaken flag.

public void put(Location l)
This method sets the location field of the Key object to the given location.

public Location getLocation()
This method returns the location field of the Key object.

public void setLocation(Location location)
This method sets the location field of the Key object to the given location
	 */
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

	public void setLocation(Location location2) {
		this.location = location2;	
	}
}