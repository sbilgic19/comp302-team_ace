package Controllers;

import domain.Player;
import ApplicationLogic.PlayerMoveLogic;
import UI.GameFrame;

public class PlayerHandler {
	
	private Player player;
	private GameFrame gameFrame;
	private int numRow;
	private int numCol;
	PlayerMoveLogic playerMoveLogic;
	
	

	public PlayerHandler(Player player, GameFrame gameFrame) {
		this.player = player;
		this.gameFrame = gameFrame;
		numRow = gameFrame.getNumRow();
		numCol = gameFrame.getNumCol();
		playerMoveLogic = new PlayerMoveLogic(this.gameFrame);
	}

	public void updatePlayerPosition(int changeInX, int changeInY) {
			playerMoveLogic.updatePlayerPosition(player, numRow, numCol, changeInX, changeInY);
		}

	public Player getPlayer() {
		return player;
	}
}