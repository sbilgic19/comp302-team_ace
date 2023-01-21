package Controllers;

import domain.Player;
import ApplicationLogic.PlayerMoveLogic;
import UI.GameController;
import UI.GameFrame;

public class PlayerHandler {
	
	private final Player player;
	private final GameController gameController;
	private int numRow;
	private int numCol;
	PlayerMoveLogic playerMoveLogic;
	
	

	public PlayerHandler(Player player, GameController gameController) {
		this.player = player;
		this.gameController = gameController;
		playerMoveLogic = new PlayerMoveLogic(this.gameController);
	}

	public void updatePlayerPosition(int changeInX, int changeInY) {
			playerMoveLogic.updatePlayerPosition(changeInX, changeInY);
		}

	public Player getPlayer() {
		return player;
	}

	public GameController getGameController() {
		return this.gameController;
	}
}