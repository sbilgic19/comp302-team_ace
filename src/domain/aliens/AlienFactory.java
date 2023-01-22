package domain.aliens;

import dataStructures.Location;
import domain.GameInfo;
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
    	Alien alien;
    	switch (alienType)
    	{
			case "TimeWasting":
				alien = new TimeWastingAlien(location);
				GameInfo.getInstance().setActiveAlien(alien);
				return alien;
			case "Shooter":
				alien = new ShooterAlien(location);
				GameInfo.getInstance().setActiveAlien(alien);
				return alien;
			case "Blind":
				alien = new BlindAlien(location);
				GameInfo.getInstance().setActiveAlien(alien);
				return alien;
    		default:
    			return null;
    	}
    }

}
