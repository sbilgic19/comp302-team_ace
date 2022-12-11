package Controllers;

import domain.Player;
import ApplicationLogic.PlayerLivesLogic;
import ApplicationLogic.PlayerMoveLogic;
import UI.GameFrame;

public class PlayerHandler {
	
	private Player player;
	private GameFrame gameFrame;
	private int numRow;
	private int numCol;
	PlayerMoveLogic playerMoveLogic;
	PlayerLivesLogic playerLivesLogic;
	
	

	public PlayerHandler(Player player, GameFrame gameFrame) {
		this.player = player;
		this.gameFrame = gameFrame;
		numRow = gameFrame.getNumRow();
		numCol = gameFrame.getNumCol();
		playerMoveLogic = new PlayerMoveLogic(this.gameFrame);
		playerLivesLogic = new PlayerLivesLogic(this.gameFrame);
	}

	public void updatePlayerPosition(int changeInX, int changeInY) {
		
			playerMoveLogic.updatePlayerPosition(this.player, this.numRow, this.numCol, changeInX, changeInY);
		}
	
	public void updatePlayerLives(boolean isIncreased)
	{
		playerLivesLogic.updatePlayerLives(this.player, isIncreased);
	}
	
	
}