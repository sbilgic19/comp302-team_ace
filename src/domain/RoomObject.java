package domain;
import dataStructures.Location;

import java.io.Serializable;

public class RoomObject implements Serializable {
	
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

	public boolean equals(Object o) {
		if (o == this) return true;
		if (!(o instanceof RoomObject)) return false;
		RoomObject p = (RoomObject) o;
		return p.typeID == typeID && p.location.getLocationX() == location.getLocationX()
					&& p.location.getLocationY() == location.getLocationY();
	}
}