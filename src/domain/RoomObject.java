package domain;
import dataStructures.Location;

public class RoomObject {
	
	private Location location;
	private int typeID;

	public RoomObject(Location location, int typeID) {
		this.location = location;
		this.typeID = typeID;
	}
	
	public int getTypeID() {
		return typeID;
	}
 	
	public Location getLocation() {
		return location;
	}
 	
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}	
}