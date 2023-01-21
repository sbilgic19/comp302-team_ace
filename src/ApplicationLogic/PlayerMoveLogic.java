package ApplicationLogic;

import UI.GameState;
import domain.GameInfo;
import UI.GameController;

public class PlayerMoveLogic  {
	
	private final GameController gameController;
	
	public PlayerMoveLogic(GameController gameController) {
		this.gameController = gameController;
	}
	
	public void updatePlayerPosition(int changeInX, int changeInY) {
		
		/**
		Modifies the position of a Player object in a game.
		@param player The Player object whose position will be modified.
		@param changeInX The change in the player's x position.
		@param changeInY The change in the player's y position.
		@requires changeInX and changeInY must be integers such that the absolute value of changeInX plus the absolute value of changeInY is less than or equal to 1.
		@modifies the position of the player object.
		@effects The player's position will be updated based on the change in x and y position. If the player's new position is not a valid position on the game map, the player's position will not be modified.
		*/
		
		
		if ( Math.abs(changeInX) +  Math.abs(changeInY) <= 1 && !GameState.getInstance().isGameOver()) {
			int xPlayerPosition = GameInfo.getInstance().getPlayer().getLocation().getLocationX();
			int yPlayerPosition = GameInfo.getInstance().getPlayer().getLocation().getLocationY();

      int newXPlayerPosition = xPlayerPosition + changeInX;
			int newYPlayerPosition = yPlayerPosition + changeInY;
			
			if (newXPlayerPosition >= 0 && newXPlayerPosition < gameController.getGameFrame().getNumRow() && newYPlayerPosition >= 0 
						&& newYPlayerPosition < gameController.getGameFrame().getNumCol()) {  
				
				if(gameController.getGameFrame().getGamePanel().getGameMap()[newXPlayerPosition][newYPlayerPosition].getIcon() == null) {
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
					gameController.getGameFrame().getGamePanel().updatePlayerView(xPlayerPosition, yPlayerPosition,
							newXPlayerPosition, newYPlayerPosition, playerLogoPosition);
					//GamePanel.
					gameController.getPlayer().getLocation().setLocationX(newXPlayerPosition);
					gameController.getPlayer().getLocation().setLocationY(newYPlayerPosition);
				}
			}
		}
	}

}
