package Controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import UI.GameFrame;
import authorization.Authorization;

public class SignUpButtonHandler implements ActionListener{

	private GameFrame gameFrame;
	private Authorization authorization;
	
	public SignUpButtonHandler(GameFrame gameFrame) {
		//this.mainScreenPanel = mainScreenPanel;
		this.gameFrame = gameFrame;
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if (event.getActionCommand().compareTo("Sign Up") == 0) {
			
			String username = gameFrame.getSignupUsername();
			String password = gameFrame.getSignupPassword();
			String checkPassword = gameFrame.getSignupCheckPassword();
			authorization = new Authorization();
			
			if(authorization.signupAuthorization(username, password, checkPassword)) {
				gameFrame.showPopUpOnScreen("The user has been created", "Dialog", JOptionPane.INFORMATION_MESSAGE);
			}else {
				gameFrame.showPopUpOnScreen("Alert! Either username and/or password is not long enough or passwords are not matching !!", "Alert",JOptionPane.ERROR_MESSAGE);
			}
			
		}else if (event.getActionCommand().compareTo("Back") == 0) {
			gameFrame.backToMainView();
		}
	}
	
	
	
	

}