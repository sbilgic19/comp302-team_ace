package ApplicationLogic;

import UI.GameFrame;
import UI.GamePanel;
import dataStructures.Location;
import domain.Player;

public class PowerUpLogic {

	public void addPowerUp(Location location, String powerUpType) 
	{
			
		if(powerUpType == "ExtraLife")
		GamePanel.placePowerUp(location, "ExtraLife");
		
			
	}	
}
