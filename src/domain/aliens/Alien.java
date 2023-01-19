package domain.aliens;
import dataStructures.Location;

import java.io.Serializable;

public interface Alien extends Serializable {
	
	Location getLocation();
	String getAlienType();
	Boolean getIsActive();
	void setLocation(Location l);
	void setAlienType(String s);
	void setIsActive(Boolean b);
}