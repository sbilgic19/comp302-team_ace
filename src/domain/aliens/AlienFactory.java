package domain.aliens;

import dataStructures.Location;
import domain.powerUps.ExtraLifePowerUp;
import domain.powerUps.ExtraTimePowerUp;
import domain.powerUps.PowerUp;

public class AlienFactory {
	private static AlienFactory factory;
	
    public static AlienFactory getInstance() 
    {
        if (factory == null) {
            factory = new AlienFactory();
        }
        return factory;
    }
    
    
    public Alien getAlien(String alienType, Location location)
    {
    	switch (alienType)
    	{
			case "TimeWasting":
				return new TimeWastingAlien(location);
			case "Shooter":
				return new ShooterAlien(location);
			case "Blind":
				return new BlindAlien(location);
    		default:
    			return null;
    	}
    }

}
