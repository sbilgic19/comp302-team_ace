

package UI;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Application.MainScreenPanelButtonsHandler;

public class MainScreenPanel extends JPanel{
	
	private JButton loginButton;
	private JButton signinButton;
	private JLabel mainScreenLabel;
	private MainScreenPanelButtonsHandler bHandler;
	public MainScreenPanel() {
		setLayout(null);
		
		mainScreenLabel = new JLabel();
		loginButton = new JButton("Log In");
		signinButton = new JButton("Sign Up");
		loginButton.setBounds(250, 150, 100, 20);
		signinButton.setBounds(250, 190, 100,20);
		
		
		
		
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