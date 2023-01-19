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
    	Alien aliean;
    	switch (alienType)
    	{
			case "TimeWasting":
				aliean = new TimeWastingAlien(location);
				GameInfo.getInstance().clearAlienList();
				GameInfo.getInstance().addAlien(aliean);
				return aliean;
			case "Shooter":
				aliean = new ShooterAlien(location);
				GameInfo.getInstance().clearAlienList();
				GameInfo.getInstance().addAlien(aliean);
				return aliean;
			case "Blind":
				aliean = new BlindAlien(location);
				GameInfo.getInstance().clearAlienList();
				GameInfo.getInstance().addAlien(aliean);
				return aliean;
    		default:
    			return null;
    	}
    }

}
