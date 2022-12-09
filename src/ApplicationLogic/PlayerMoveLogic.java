package ApplicationLogic;

import UI.GamePanel;
import domain.Player;
import UI.GameFrame;

public class PlayerMoveLogic  {
	
	private GameFrame gameFrame;
	
	public PlayerMoveLogic(GameFrame gameFrame) {
		this.gameFrame = gameFrame;
	}
	
	public void updatePlayerPosition(Player player, int numRow, int numCol, int changeInX, int changeInY) {
			
			int xPlayerPosition = player.getLocation().getLocationX();
			int yPlayerPosition = player.getLocation().getLocationY();
			
			int newXPlayerPosition = xPlayerPosition + changeInX;
			int newYPlayerPosition = yPlayerPosition + changeInY;
			
			if (newXPlayerPosition >= 0 && newXPlayerPosition < numRow && newYPlayerPosition >= 0 
						&& newYPlayerPosition < numCol) {  
				
				if(GamePanel.getGameMap()[newXPlayerPosition][newYPlayerPosition].getIcon() == null) {
					GamePanel.updatePlayerView(xPlayerPosition, yPlayerPosition,
							newXPlayerPosition, newYPlayerPosition);
					player.getLocation().setLocationX(newXPlayerPosition);
					player.getLocation().setLocationY(newYPlayerPosition);
				}
			}
		}

}
