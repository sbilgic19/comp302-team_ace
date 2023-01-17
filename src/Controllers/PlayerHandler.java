package Controllers;

import domain.Player;
import ApplicationLogic.PlayerMoveLogic;
import UI.GameController;
import UI.GameFrame;

public class PlayerHandler {
	
	private Player player;
	private GameController gameController;
	private int numRow;
	private int numCol;
	PlayerMoveLogic playerMoveLogic;
	
	

	public PlayerHandler(Player player, GameController gameController) {
		this.player = player;
		this.gameController = gameController;
		playerMoveLogic = new PlayerMoveLogic(this.gameController);
	}

	public void updatePlayerPosition(int changeInX, int changeInY) {
			playerMoveLogic.updatePlayerPosition(player, changeInX, changeInY);
		}

	public Player getPlayer() {
		return player;
	}

	public GameController getGameController() {
		return this.gameController;
	}
}