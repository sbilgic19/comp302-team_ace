package Controllers;

import ApplicationLogic.PlasticBottleMovementLogic;
import UI.GameState;
import dataStructures.Location;
import domain.GameInfo;
import domain.powerUps.PlasticBottlePowerUp;
import domain.powerUps.PowerUp;
import domain.Key;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
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
	private Timer hintTimer;

	private int seconds = 0;

	private PlayerHandler playerHandler;
	private PowerUpHandler powerUpHandler;
	
	public KeyHandler(PlayerHandler playerHandler) {
		this.playerHandler = playerHandler; 
		currentTime = System.currentTimeMillis();
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
				}else if(event.getKeyCode() == KeyEvent.VK_P && GameInfo.getInstance().getPlayer().isContains("ProtectionVest")){
					PowerUp powerUp = GameInfo.getInstance().getPlayer().getPowerUp("ProtectionVest");
					if(powerUp != null){
						powerUp.triggerEffect();
						playerHandler.getGameController().getGameFrame().updateBagView(null);
					}
				}else if(event.getKeyCode() == KeyEvent.VK_H && GameInfo.getInstance().getPlayer().isContains("Hint") && !playerHandler.getPlayer().getIsKeyTaken()){
					PowerUp powerUp = GameInfo.getInstance().getPlayer().getPowerUp("Hint");
					if(powerUp != null){
						Key key = GameInfo.getInstance().getKey();
						playerHandler.getGameController().getGameFrame().getGamePanel().addBordersHint(key.getLocation().getLocationX(), key.getLocation().getLocationY());

						hintTimer = new Timer(1000, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								if (!GameState.getInstance().isPaused())
								{
									seconds++;
									if(seconds == 10){
										playerHandler.getGameController().getGameFrame().getGamePanel().removeBorders();
										hintTimer.stop();
									}
								}
							}
						});
						powerUp.triggerEffect();
						hintTimer.start();
						playerHandler.getGameController().getGameFrame().updateBagView(null);




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
				else if(isBPressed && GameInfo.getInstance().getPlayer().isContains("PlasticBottle")){
					System.out.println("Hi");
					Location bottleStartPos = new Location(GameInfo.getInstance().getPlayer().getLocation().getLocationX(),GameInfo.getInstance().getPlayer().getLocation().getLocationY());
					PlasticBottlePowerUp powerUp = null;
					if(isWPressed){
						System.out.println("HİW");
						powerUp = new PlasticBottlePowerUp(bottleStartPos,GameInfo.getInstance().getPlayer(), "North");
						PlasticBottleMovementLogic plasticBottleMovementLogic = new PlasticBottleMovementLogic(playerHandler.getGameController(), powerUp);
						plasticBottleMovementLogic.updateBottlePosition();
						playerHandler.getPlayer().removePowerUp("PlasticBottle");
					} else if (isAPressed) {
						powerUp = new PlasticBottlePowerUp(bottleStartPos, GameInfo.getInstance().getPlayer(),"West");
						PlasticBottleMovementLogic plasticBottleMovementLogic = new PlasticBottleMovementLogic(playerHandler.getGameController(), powerUp);
						plasticBottleMovementLogic.updateBottlePosition();
						playerHandler.getPlayer().removePowerUp("PlasticBottle");
					} else if (isDPressed) {
						powerUp = new PlasticBottlePowerUp(bottleStartPos, GameInfo.getInstance().getPlayer(),"East");
						PlasticBottleMovementLogic plasticBottleMovementLogic = new PlasticBottleMovementLogic(playerHandler.getGameController(),powerUp);
						plasticBottleMovementLogic.updateBottlePosition();
						playerHandler.getPlayer().removePowerUp("PlasticBottle");
					} else if (isSPressed) {
						powerUp = new PlasticBottlePowerUp(bottleStartPos, GameInfo.getInstance().getPlayer(),"South");
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