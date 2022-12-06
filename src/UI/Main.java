package UI;
import javax.swing.JFrame;

import Controllers.LoginAuthorizationHandler;
import Controllers.KeyHandler;
import Controllers.PlayerHandler;
import domain.Player;
import domain.User;
import authorization.Authorization;


public class Main {
	public static void main(String[] args) {

		//Authorization.addUserToRecord(new User("nsavran", "123456"));

		GameFrame gameFrame = new GameFrame();
		//AuthorizationLogic authorizationHandler = new AuthorizationLogic(gameFrame);
		Player player = new Player(gameFrame.getUsernameMessage(),gameFrame.getPasswordMessage());
		LoginAuthorizationHandler buttonHandler = new LoginAuthorizationHandler(gameFrame);
		PlayerHandler playerHandler = new PlayerHandler(player, gameFrame);
		KeyHandler keyHandler = new KeyHandler(playerHandler);
		
		gameFrame.setButtonHandler(buttonHandler);
		gameFrame.setKeyHandler(keyHandler);
		//gameFrame.switchLoginView();
		gameFrame.showMainView();
		
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(1600,920);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(false);
		gameFrame.setVisible(true);
	}
}