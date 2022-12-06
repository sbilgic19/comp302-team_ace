package Application;

import domain.Player;
import UI.GameFrame;

public class PlayerHandler {
	
	private Player player;
	private GameFrame gameFrame;
	private int numRow;
	private int numCol;
	PlayerMoveLogic playerLogic;
	
	public PlayerHandler(Player player, GameFrame gameFrame) {
		this.player = player;
		this.gameFrame = gameFrame;
		numRow = gameFrame.getNumRow();
		numCol = gameFrame.getNumCol();
		playerLogic = new PlayerMoveLogic(this.gameFrame);
	}

	public void updatePlayerPosition(int changeInX, int changeInY) {
		
			playerLogic.updatePlayerPosition(this.player, this.numRow, this.numCol, changeInX, changeInY);
		}
	
	
	
}