package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import domain.User;
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
		}else if (event.getActionCommand().compareTo("Sign Up") == 0) {
			gameFrame.switchSignUpView();
		}else {
			gameFrame.showInfoView();
		}
	}
}
