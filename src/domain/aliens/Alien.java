package domain.aliens;
import dataStructures.Location;

public interface Alien {
	
	Location getLocation();
	String getAlienType();
	Boolean getIsActive();
	void setLocation(Location l);
	void setAlienType(String s);
	void setIsActive(Boolean b);
}