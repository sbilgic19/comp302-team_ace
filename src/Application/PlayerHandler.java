package Application;

import Domain.Player;
import UI.GameFrame;

public class PlayerHandler {
	
	private Player player;
	private GameFrame gameFrame;
	
	public PlayerHandler(Player player, GameFrame gameFrame) {
		this.player = player;
		this.gameFrame = gameFrame;
	}

	public void updatePlayerPosition(int changeInX, int changeInY) {
		
		int xPlayerPosition = player.getXPosition();
		int yPlayerPosition = player.getYPosition();
		
		int newXPlayerPosition = xPlayerPosition + changeInX;
		int newYPlayerPosition = yPlayerPosition + changeInY;
		
		if (newXPlayerPosition >= 0 && newXPlayerPosition < 10 && newYPlayerPosition >= 0 
					&& newYPlayerPosition < 15) {  
			gameFrame.updatePlayerView(xPlayerPosition, yPlayerPosition,
					newXPlayerPosition, newYPlayerPosition);
			player.setXPosition(newXPlayerPosition);
			player.setYPosition(newYPlayerPosition);
		}
	}	
}