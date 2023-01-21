package ApplicationLogic;

import java.util.Random;

import UI.GameController;
import dataStructures.Location;
import domain.GameInfo;
import domain.Player;
import domain.powerUps.PowerUp;
import domain.powerUps.PowerUpFactory;

public class PowerUpLogic {

	private final GameController gameController;
	private final Random r = new Random();
	
	public PowerUpLogic(GameController gameController, Player player) {
		this.gameController = gameController;
	}
	public void addPowerUp(PowerUp powerUp) 
	{
		String powerUpType = powerUp.getPowerUpType();
		Location location = powerUp.getLocation();
		
		gameController.getGameFrame().getGamePanel().placePowerUp(location, powerUpType);	
			
	}	
	public void usePowerUp(PowerUp powerUp) {
		if(powerUp.getPowerUpType().equalsIgnoreCase("ExtraLife") || powerUp.getPowerUpType().equalsIgnoreCase("ExtraTime"))
			powerUp.triggerEffect();
		else
			gameController.getGameFrame().updateBagView(powerUp);
			GameInfo.getInstance().getPlayer().addToBag(powerUp);
			System.out.println(powerUp.getPowerUpType() + " added to the bag");
	}
	
	public PowerUp getPowerUp() {
		int row = 0, column = 0;
		int rowCount = gameController.getGameFrame().getNumRow();
		int columnCount = gameController.getGameFrame().getNumCol();
		boolean isOccupied = true;
		while (isOccupied) {
			row = r.nextInt(rowCount);
			column = r.nextInt(columnCount);
			if (gameController.getGameFrame().getGamePanel().getGameMap()[row][column].getIcon() == null) {
				isOccupied = false;
			}
		}
		int rand = r.nextInt(5);
		PowerUp powerUp;
		rand = 3;
		//It will be random Location.
		Location location = new Location(row,column);
		
		switch (rand) 
		{
			case 0:
				powerUp = PowerUpFactory.getInstance().getPowerUp("ExtraLife", location);
				this.addPowerUp(powerUp);
	            break;
			case 1:
				powerUp = PowerUpFactory.getInstance().getPowerUp("ExtraTime", location);
				this.addPowerUp(powerUp);
				break;
			case 2:
				powerUp = PowerUpFactory.getInstance().getPowerUp("ProtectionVest", location);
				this.addPowerUp(powerUp);
				break;
      		case 3:
				powerUp = PowerUpFactory.getInstance().getPowerUp("PlasticBottle", location);
				this.addPowerUp(powerUp);
				break;
			case 4:
				powerUp = PowerUpFactory.getInstance().getPowerUp("Hint", location);
				this.addPowerUp(powerUp);
				break;
	    default:
				powerUp = null;
		}
		GameInfo.getInstance().setActivePowerUp(powerUp);
		gameController.setPowerUp(powerUp);
		return powerUp;
		}
}
