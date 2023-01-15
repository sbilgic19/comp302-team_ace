package UI;
import javax.swing.*;

import Controllers.LoginAuthorizationHandler;
import Controllers.BlindAlienHandler;
import Controllers.KeyHandler;
import Controllers.PlayerHandler;
import Controllers.RoomKeyHandler;
import Controllers.ShooterAlienHandler;
import domain.GameInfo;
import domain.Player;
import domain.powerUps.PowerUpFactory;


public class Main {
	
	private static int frameWidth = 1500;
	private static int frameHeight = 750;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}

	private static void createAndShowGUI(){
		//Authorization.addUserToRecord(new User("nsavran", "123456"));

		GameFrame gameFrame = new GameFrame();
		//AuthorizationLogic authorizationHandler = new AuthorizationLogic(gameFrame);
		Player player = new Player(0,5);
		RoomKeyHandler roomKeyHandler = new RoomKeyHandler(gameFrame, player);
		LoginAuthorizationHandler buttonHandler = new LoginAuthorizationHandler(gameFrame);
		PlayerHandler playerHandler = new PlayerHandler(player, gameFrame);
		KeyHandler keyHandler = new KeyHandler(playerHandler);
		ShooterAlienHandler shooterAlienHandler = new ShooterAlienHandler(player, gameFrame);
		BlindAlienHandler blindAlienHandler = new BlindAlienHandler(player,gameFrame);
		
		
		GameInfo.getInstance().setPlayer(player);
		PowerUpFactory.getInstance().setPlayer(player);
		
		gameFrame.setButtonHandler(buttonHandler);
		gameFrame.setShooterAlienHandler(shooterAlienHandler);
		gameFrame.setBlindAlienHandler(blindAlienHandler);
		gameFrame.setKeyHandler(keyHandler);
		gameFrame.setRoomKeyHandler(roomKeyHandler);
		//gameFrame.switchLoginView();
		gameFrame.showMainView();
		GamePanel.setPlayer(player);
		
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(frameWidth, frameHeight);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(false);
		gameFrame.setVisible(true);
	}

}