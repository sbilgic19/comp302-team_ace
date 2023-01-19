package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import ApplicationLogic.AuthorizationLogic;
import Database.Client;
import UI.GameFrame;

public class SignUpButtonHandler implements ActionListener{

	private GameFrame gameFrame;
	private Client client;
	
	public SignUpButtonHandler(GameFrame gameFrame) {
		//this.mainScreenPanel = mainScreenPanel;
		this.gameFrame = gameFrame;
		this.client = gameFrame.getGameController().getClient();
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getActionCommand().compareTo("Sign Up") == 0) {
			
			String username = gameFrame.getSignupUsername();
			String password = gameFrame.getSignupPassword();
			String checkPassword = gameFrame.getSignupCheckPassword();
			
			if(client.signUp(username, password, checkPassword)) {
				gameFrame.showPopUpOnScreen("The user has been created", "Dialog", JOptionPane.INFORMATION_MESSAGE);
			}else {
				gameFrame.showPopUpOnScreen("Alert! Either username and/or password is not long enough or passwords are not matching !! or this username already exists", "Alert",JOptionPane.ERROR_MESSAGE);
			}
			
		}else if (event.getActionCommand().compareTo("Back") == 0) {
			gameFrame.backToMainView();
		}
	}
	
	
	
	

}
