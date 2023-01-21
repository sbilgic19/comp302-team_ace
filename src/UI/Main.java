package UI;
import javax.swing.*;


import Controllers.*;
import domain.GameInfo;
import domain.Player;
import domain.powerUps.PowerUpFactory;


public class Main {
	
	private static final int frameWidth = 1500;
	private static final int frameHeight = 750;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

	}

	private static void createAndShowGUI(){
		//Authorization.addUserToRecord(new User("nsavran", "123456"));

		
		
		GameController gameController = new GameController();
		gameController.createAndShowGUI();
		
		
		
		
//		GameFrame gameFrame = new GameFrame();
//		
//		Player player = new Player();
//		RoomKeyHandler roomKeyHandler = new RoomKeyHandler(gameFrame, player);
//		PlayerHandler playerHandler = new PlayerHandler(player, gameFrame);
//
//		KeyHandler keyHandler = new KeyHandler(playerHandler);
//		ShooterAlienHandler shooterAlienHandler = new ShooterAlienHandler(player, gameFrame);
//		BlindAlienHandler blindAlienHandler = new BlindAlienHandler(player,gameFrame);
//		PowerUpHandler powerUpHandler = new PowerUpHandler(gameFrame, player);
//
//
//
//		GameInfo.getInstance().setPlayer(player);
//		PowerUpFactory.getInstance().setPlayer(player);
//		
//		//gameFrame.setButtonHandler(buttonHandler);
//		gameFrame.setShooterAlienHandler(shooterAlienHandler);
//		gameFrame.setBlindAlienHandler(blindAlienHandler);
//		gameFrame.setKeyHandler(keyHandler);
//		gameFrame.setRoomKeyHandler(roomKeyHandler);
//		gameFrame.setPowerUpHandler(powerUpHandler);
//		gameFrame.showMainView();
//		GamePanel.setPlayer(player);
//		
//		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		gameFrame.setSize(frameWidth, frameHeight);
//		gameFrame.setLocationRelativeTo(null);
//		gameFrame.setResizable(false);
//		gameFrame.setVisible(true);
	}

}