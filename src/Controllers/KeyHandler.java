package Controllers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {
	
	private long lastSavedTime;
	private long currentTime;
	
	private PlayerHandler playerHandler;
	
	public KeyHandler(PlayerHandler playerHandler) {
		this.playerHandler = playerHandler; 
		currentTime = System.currentTimeMillis();
	}
	 
	@Override
	public void keyPressed(KeyEvent event) {
		currentTime = System.currentTimeMillis();
		if (currentTime - lastSavedTime > 40) {
			if (event.getKeyCode() == KeyEvent.VK_UP) {
				playerHandler.updatePlayerPosition(-1, 0);
			}
			else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
				playerHandler.updatePlayerPosition(1, 0);
			}
			else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
				playerHandler.updatePlayerPosition(0, -1);
			}
			else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
				playerHandler.updatePlayerPosition(0, 1);
			}
			lastSavedTime = currentTime;
		}
	}
}