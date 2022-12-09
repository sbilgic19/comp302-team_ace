

package UI;

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
		signinButton = new JButton("Sign Up");
		loginButton.setBounds(700, 400, 200, 50);
		signinButton.setBounds(700, 450, 200, 50);
		
		
		
		
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