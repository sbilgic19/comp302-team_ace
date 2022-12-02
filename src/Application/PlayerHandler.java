package Application;

import domain.Player;
import UI.GameFrame;

public class PlayerHandler {
	
	private Player player;
	private GameFrame gameFrame;
	private int numRow;
	private int numCol;
	
	public PlayerHandler(Player player, GameFrame gameFrame) {
		this.player = player;
		this.gameFrame = gameFrame;
		numRow = gameFrame.getNumRow();
		numCol = gameFrame.getNumCol();
	}

	public void updatePlayerPosition(int changeInX, int changeInY) {
		
		int xPlayerPosition = player.getXPosition();
		int yPlayerPosition = player.getYPosition();
		
		int newXPlayerPosition = xPlayerPosition + changeInX;
		int newYPlayerPosition = yPlayerPosition + changeInY;
		
		if (newXPlayerPosition >= 0 && newXPlayerPosition < numRow && newYPlayerPosition >= 0 
					&& newYPlayerPosition < numCol) {  
			gameFrame.updatePlayerView(xPlayerPosition, yPlayerPosition,
					newXPlayerPosition, newYPlayerPosition);
			player.setXPosition(newXPlayerPosition);
			player.setYPosition(newYPlayerPosition);
		}
	}	
}