package UI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controllers.MainScreenPanelButtonsHandler;

public class MainScreenPanel extends JPanel{
	
	private final JButton loginButton;
	private final JButton signinButton;
	private final JLabel mainScreenLabel;

	
	private final ImageIcon infoIcon;
	private final JButton infoButton;
	
	public MainScreenPanel() {
		setLayout(null);
		
		mainScreenLabel = new JLabel();
		loginButton = new JButton("Log In");
		loginButton.setFocusable(false);
		
		IconFactory iconFactory = IconFactory.getInstance();
		infoIcon = iconFactory.generateIcon("../assets/infoIcon.png",60, 60);
		infoButton = new JButton(infoIcon);
		infoButton.setBorderPainted(false);
		infoButton.setFocusable(false);
		infoButton.setBackground(null);
		
		signinButton = new JButton("Sign Up");
		signinButton.setFocusable(false);
	
		loginButton.setBounds(650, 325, 200, 50);
		signinButton.setBounds(650, 375, 200, 50);
		infoButton.setBounds(720, 500, 60, 60);
		
		add(loginButton);
		add(signinButton);
		add(infoButton);
		
	}
	public JButton getLoginButton() {
		return loginButton;
	}
	public JButton getSigninButton() {
		return signinButton;
	}
	public JButton getInfoButton() {
		return infoButton;
	}
	public JLabel getMainLabel() {
		return mainScreenLabel;
	}
}