package Controllers;

import UI.GameFrame;
import UI.GamePanel;
import UI.GameState;
import domain.powerUps.PowerUp;
import domain.Key;

import java.util.Timer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.TimerTask;

public class KeyHandler extends KeyAdapter {
	
	private long lastSavedTime;
	private long currentTime;
	
	private PlayerHandler playerHandler;
	private PowerUpHandler powerUpHandler;
	
	public KeyHandler(PlayerHandler playerHandler) {
		this.playerHandler = playerHandler; 
		currentTime = System.currentTimeMillis();
	}
	public KeyHandler(PlayerHandler playerHandler, PowerUpHandler powerUpHandler) {
		this.playerHandler = playerHandler;
		currentTime = System.currentTimeMillis();
		this.powerUpHandler = powerUpHandler;
	}
	 
	@Override
	public void keyPressed(KeyEvent event) {
		if(!GameState.getInstance().isPaused()) {
			currentTime = System.currentTimeMillis();
			if (currentTime - lastSavedTime > 40) {
				if (event.getKeyCode() == KeyEvent.VK_UP) {
					playerHandler.updatePlayerPosition(-1, 0);
				} else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
					playerHandler.updatePlayerPosition(1, 0);
				} else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
					playerHandler.updatePlayerPosition(0, -1);
				} else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
					playerHandler.updatePlayerPosition(0, 1);
				}else if(event.getKeyCode() == KeyEvent.VK_P && playerHandler.getPlayer().isContains("ProtectionVest")){
					PowerUp powerUp = playerHandler.getPlayer().getPowerUp("ProtectionVest");
					if(powerUp != null){
						powerUp.triggerEffect();
						playerHandler.getGameFrame().updateBagView(null);
					}
				}else if(event.getKeyCode() == KeyEvent.VK_H && playerHandler.getPlayer().isContains("Hint")){
					PowerUp powerUp = playerHandler.getPlayer().getPowerUp("Hint");
					if(powerUp != null){
						Key key = powerUpHandler.getGameFrame().getGamePanel().getKey();
						powerUpHandler.getGameFrame().getGamePanel().addBordersHint(key.getLocation().getLocationX(), key.getLocation().getLocationY());
						Timer timer = new Timer();
						timer.schedule(new TimerTask() {
							@Override
							public void run() {
								powerUpHandler.getGameFrame().getGamePanel().removeBorders();
							}
						}, 10 * 1000);

						playerHandler.getGameFrame().updateBagView(null);
						powerUp.triggerEffect();


					}
				}
				lastSavedTime = currentTime;
			}
		}
	}
}