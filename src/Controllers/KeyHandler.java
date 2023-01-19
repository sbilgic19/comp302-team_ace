package Controllers;

import UI.GameFrame;
import UI.GamePanel;
import ApplicationLogic.PlasticBottleMovementLogic;
import UI.GameState;
import dataStructures.Location;
import domain.powerUps.PlasticBottlePowerUp;
import domain.powerUps.PowerUp;
import domain.Key;

import java.util.Timer;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.TimerTask;


public class KeyHandler extends KeyAdapter {
	
	private long lastSavedTime;
	private long currentTime;
	private boolean isWPressed = false;
	private boolean isDPressed = false;
	private boolean isSPressed = false;
	private boolean isAPressed = false;
	private boolean isBPressed = false;

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
						playerHandler.getGameController().getGameFrame().updateBagView(null);
					}
				}else if(event.getKeyCode() == KeyEvent.VK_H && playerHandler.getPlayer().isContains("Hint")){
					PowerUp powerUp = playerHandler.getPlayer().getPowerUp("Hint");
					if(powerUp != null){
						Key key = powerUpHandler.getGameController().getGameFrame().getGamePanel().getKey();
						powerUpHandler.getGameController().getGameFrame().getGamePanel().addBordersHint(key.getLocation().getLocationX(), key.getLocation().getLocationY());
						Timer timer = new Timer();
						timer.schedule(new TimerTask() {
							@Override
							public void run() {
								
								//GamePanel.removeBorders();
								powerUpHandler.getGameController().getGameFrame().getGamePanel().removeBorders();
							}
						}, 10 * 1000);

						playerHandler.getGameController().getGameFrame().updateBagView(null);
						powerUp.triggerEffect();


					}
				}
				if(event.getKeyChar() == 'b'){
					isBPressed = true;
				}
				if(event.getKeyChar() == 'w'){
					isWPressed = true;
				}
				if(event.getKeyChar() == 'd'){
					isDPressed = true;
				}
				if(event.getKeyChar() == 'a'){
					isAPressed = true;
				}
				if(event.getKeyChar() == 's'){
					isSPressed = true;
				}
				else if(isBPressed && playerHandler.getPlayer().isContains("PlasticBottle")){
					System.out.println("Hi");
					Location bottleStartPos = new Location(playerHandler.getPlayer().getLocation().getLocationX(),playerHandler.getPlayer().getLocation().getLocationY());
					PlasticBottlePowerUp powerUp = null;
					if(isWPressed){
						System.out.println("HİW");
						powerUp = new PlasticBottlePowerUp(bottleStartPos, playerHandler.getPlayer(),"North");
						PlasticBottleMovementLogic plasticBottleMovementLogic = new PlasticBottleMovementLogic(playerHandler.getGameController(), powerUp);
						plasticBottleMovementLogic.updateBottlePosition();
						playerHandler.getPlayer().removePowerUp("PlasticBottle");
					} else if (isAPressed) {
						powerUp = new PlasticBottlePowerUp(bottleStartPos, playerHandler.getPlayer(),"West");
						PlasticBottleMovementLogic plasticBottleMovementLogic = new PlasticBottleMovementLogic(playerHandler.getGameController(), powerUp);
						plasticBottleMovementLogic.updateBottlePosition();
						playerHandler.getPlayer().removePowerUp("PlasticBottle");
					} else if (isDPressed) {
						powerUp = new PlasticBottlePowerUp(bottleStartPos, playerHandler.getPlayer(),"East");
						PlasticBottleMovementLogic plasticBottleMovementLogic = new PlasticBottleMovementLogic(playerHandler.getGameController(),powerUp);
						plasticBottleMovementLogic.updateBottlePosition();
						playerHandler.getPlayer().removePowerUp("PlasticBottle");
					} else if (isSPressed) {
						powerUp = new PlasticBottlePowerUp(bottleStartPos, playerHandler.getPlayer(),"South");
						PlasticBottleMovementLogic plasticBottleMovementLogic = new PlasticBottleMovementLogic(playerHandler.getGameController(), powerUp);
						plasticBottleMovementLogic.updateBottlePosition();
						playerHandler.getPlayer().removePowerUp("PlasticBottle");
					}
				}
				lastSavedTime = currentTime;
			}
		}
	}

	@Override
	public synchronized void keyReleased(KeyEvent event) {
		if(event.getKeyChar() == 'w'){
			isWPressed = false;
		}
		if(event.getKeyChar() == 'd'){
			isDPressed = false;
		}
		if(event.getKeyChar() == 'a'){
			isAPressed = false;
		}
		if(event.getKeyChar() == 's'){
			isSPressed = false;
		}
		if(event.getKeyChar() == 'b'){
			isBPressed = false;
		}
	}
}