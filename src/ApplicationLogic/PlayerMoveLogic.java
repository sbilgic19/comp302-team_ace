package ApplicationLogic;

import domain.Player;
import UI.GameFrame;

public class PlayerMoveLogic  {
	
	private GameFrame gameFrame;
	
	public PlayerMoveLogic(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}
	
	public void updatePlayerPosition(Player player, int numRow, int numCol, int changeInX, int changeInY) {
			
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
