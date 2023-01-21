package UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpPanel extends JPanel{
	
	
	private final JTextField signUsername;
	private final JPasswordField signPassword;
	private final JPasswordField signCheckPassword;
	private final JLabel signUsernameLabel;
	private final JLabel signPasswordLabel;
	private final JLabel signCheckPasswordLabel;
	private final JButton signUpButton;
	private final JButton backButton;
	private boolean isOn = false;
	public SignUpPanel() {
		//setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setLayout(new FlowLayout());
		signUsername = new JTextField(10);
		signPassword = new JPasswordField(10);
		signCheckPassword = new JPasswordField(10);
		
		signUsernameLabel = new JLabel("Username: ");
		signPasswordLabel = new JLabel("Password: ");
		signCheckPasswordLabel = new JLabel("Please re-enter your password: ");
		
		backButton = new JButton("Back");
		backButton.setFocusable(false);
		
		signUpButton = new JButton("Sign Up");
		signUpButton.setFocusable(false);
		
		add(signUsernameLabel);
		add(signUsername);
		
		add(signPasswordLabel);
		add(signPassword);
		
		add(signCheckPasswordLabel);
		add(signCheckPassword);

		add(signUpButton);
		add(backButton);
	}
	
	public String getSignupUsername() {
		return signUsername.getText();
	}
	
	public String getSignupPassword() {
		return new String(signPassword.getPassword());
	}
	public String getSignupCheckPassword() {
		return new String(signCheckPassword.getPassword());
	}
	public JButton getSignupButton() {
		return signUpButton;
	}
	public JButton getBackButton() {
		return backButton;
	}
	public boolean getIsOn() {
		return isOn;
	}
	public void setIsOn(boolean newState) {
		this.isOn = newState;
	}
}