package Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Domain.User;
import UI.GameFrame;
import UI.MainScreenPanel;
import authorization.Authorization;

public class MainScreenPanelButtonsHandler implements ActionListener{

	//private MainScreenPanel mainScreenPanel;
	private GameFrame gameFrame;
	
	public MainScreenPanelButtonsHandler(GameFrame gameFrame) {
		//this.mainScreenPanel = mainScreenPanel;
		this.gameFrame = gameFrame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().compareTo("Log In") == 0) {
			gameFrame.switchLoginView();
			
//			String username = mainScreenPanel.getUsernameMessage();
//			String password = mainScreenPanel.getPasswordMessage();
//			for(User user: Authorization.getRecordedUsers()){
//				if (username.compareTo(user.getUsername()) == 0 &&
//						password.compareTo(user.getPassword()) == 0) {
//					mainScreenPanel.switchGameView();
//				}
//				//	else {gameFrame.giveAnErrorPopUpInTheScreen();	}
//			}
		}else if (event.getActionCommand().compareTo("Sign Up") == 0) {
			gameFrame.switchSignUpView();
		}
	}
}
