package ApplicationLogic;

import UI.GamePanel;
import domain.Player;
import UI.GameFrame;

public class PlayerMoveLogic  {
	
	private GameFrame gameFrame;
	
	public PlayerMoveLogic(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}
	
	public void updatePlayerPosition(Player player, int changeInX, int changeInY) {
		
		/**
		Modifies the position of a Player object in a game.
		@param player The Player object whose position will be modified.
		@param changeInX The change in the player's x position.
		@param changeInY The change in the player's y position.
		@requires changeInX and changeInY must be integers such that the absolute value of changeInX plus the absolute value of changeInY is less than or equal to 1.
		@modifies the position of the player object.
		@effects The player's position will be updated based on the change in x and y position. If the player's new position is not a valid position on the game map, the player's position will not be modified.
		*/
		
		
		if ( Math.abs(changeInX) +  Math.abs(changeInY) <= 1) {
			int xPlayerPosition = player.getLocation().getLocationX();
			int yPlayerPosition = player.getLocation().getLocationY();

			int newXPlayerPosition = xPlayerPosition + changeInX;
			int newYPlayerPosition = yPlayerPosition + changeInY;
			
			if (newXPlayerPosition >= 0 && newXPlayerPosition < gameFrame.getNumRow() && newYPlayerPosition >= 0 
						&& newYPlayerPosition < gameFrame.getNumCol()) {  
				
				if(GamePanel.getGameMap()[newXPlayerPosition][newYPlayerPosition].getIcon() == null) {
					int playerLogoPosition = 0;
					if (changeInX == 1 && changeInY == 0) {
					}
					else if (changeInX == -1 && changeInY == 0) {
						playerLogoPosition = 1;
					}
					else if (changeInX == 0 && changeInY == -1) {
						playerLogoPosition = 2;
					}
					else {
						playerLogoPosition = 3;
					}
					GamePanel.updatePlayerView(xPlayerPosition, yPlayerPosition,
							newXPlayerPosition, newYPlayerPosition, playerLogoPosition);
					player.getLocation().setLocationX(newXPlayerPosition);
					player.getLocation().setLocationY(newYPlayerPosition);
				}
			}
		}
	}

}
