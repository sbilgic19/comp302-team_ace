package UI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controllers.MainScreenPanelButtonsHandler;

public class MainScreenPanel extends JPanel{
	
	private JButton loginButton;
	private JButton signinButton;
	private JLabel mainScreenLabel;
	private MainScreenPanelButtonsHandler bHandler;
	public MainScreenPanel() {
		setLayout(null);
		
		mainScreenLabel = new JLabel();
		loginButton = new JButton("Log In");
		loginButton.setFocusable(false);
		
		signinButton = new JButton("Sign Up");
		signinButton.setFocusable(false);
	
		loginButton.setBounds(650, 325, 200, 50);
		signinButton.setBounds(650, 375, 200, 50);
		
		add(loginButton);
		add(signinButton);
		
	}
	public JButton getLoginButton() {
		return loginButton;
	}
	public JButton getSigninButton() {
		return signinButton;
	}
	public JLabel getMainLabel() {
		return mainScreenLabel;
	}
}