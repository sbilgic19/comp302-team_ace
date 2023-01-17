package Controllers;


import ApplicationLogic.KeyLogic;
import ApplicationLogic.PowerUpLogic;
import UI.GameController;
import UI.GameFrame;
import domain.Key;
import domain.Player;

public class RoomKeyHandler {
	KeyLogic keyLogic;
	GameController gameController;
	Player player;
	Key key;
	
	public RoomKeyHandler(GameController gameController, Player p) {
		keyLogic = new KeyLogic(gameController);
		this.gameController = gameController;
		this.player = p;

	}
	
	public Key getRandomKey() {
		this.key = keyLogic.getKey();
		return key;
	}
	
	public boolean takeKey(Key k) {
		return keyLogic.takeKey(player, k);
	}
	
	public Key getKeyInstance() {
		return key;
	}
	
	public Player getPlayer() {
		return this.player;
	}
}