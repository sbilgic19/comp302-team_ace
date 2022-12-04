package Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import UI.GameFrame;

public class SignUpButtonHandler implements ActionListener{

	private GameFrame gameFrame;
	
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
			
			if(password.equals(checkPassword) && username.length() > 5 && password.length() > 5) {
				gameFrame.showPopUpOnScreen("The user has been created", "Dialog", JOptionPane.INFORMATION_MESSAGE);
			}else {
				gameFrame.showPopUpOnScreen("Alert! Either username and/or password is not long enough or passwords are not matching !!", "Alert",JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	
	
	
	

}
