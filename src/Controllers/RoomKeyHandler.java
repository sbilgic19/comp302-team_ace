package Controllers;


import ApplicationLogic.KeyLogic;
import ApplicationLogic.PowerUpLogic;
import UI.GameController;
import UI.GameFrame;
import dataStructures.Location;
import domain.GameInfo;
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
		key = keyLogic.getKey();
		return key;
	}
	
	public boolean takeKey(Key k) {
		return keyLogic.takeKey(GameInfo.getInstance().getPlayer(), GameInfo.getInstance().getKey());
	}
	
	public Key getKeyInstance() {
		return key;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	public boolean nextLevel() {
		if (Location.distance(GameInfo.getInstance().getPlayer().getLocation(), GameInfo.getInstance().getDoorLocation()) < 3
				&& GameInfo.getInstance().getKey().getIsTaken()) {
			return true;
		}
		return false;
	}
}