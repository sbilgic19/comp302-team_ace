package Controllers;


import ApplicationLogic.KeyLogic;
import ApplicationLogic.PowerUpLogic;
import UI.GameFrame;
import domain.Key;
import domain.Player;

public class RoomKeyHandler {
	KeyLogic keyLogic;
	GameFrame gameFrame;
	Player player;
	Key key;
	
	public RoomKeyHandler(GameFrame gameFrame, Player p) {
		keyLogic = new KeyLogic(gameFrame);
		this.gameFrame = gameFrame;
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
}