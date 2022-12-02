package UI;
import javax.swing.JFrame;

import Application.ButtonHandler;
import Application.KeyHandler;
import Application.PlayerHandler;
import domain.Player;

public class Main {
	public static void main(String[] args) {

		GameFrame gameFrame = new GameFrame();
		Player player = new Player(gameFrame.getUsernameMessage(),gameFrame.getPasswordMessage());
		ButtonHandler buttonHandler = new ButtonHandler(gameFrame);
		PlayerHandler playerHandler = new PlayerHandler(player, gameFrame);
		KeyHandler keyHandler = new KeyHandler(playerHandler);
		
		gameFrame.setButtonHandler(buttonHandler);
		gameFrame.setKeyHandler(keyHandler);
		gameFrame.switchLoginView();
		
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(600,400);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(false);
		gameFrame.setVisible(true);
	}
}