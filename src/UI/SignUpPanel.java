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
	
	
	private JTextField signUsername;
	private JPasswordField signPassword;
	private JPasswordField signCheckPassword;
	private JLabel signUsernameLabel;
	private JLabel signPasswordLabel;
	private JLabel signCheckPasswordLabel;
	private JButton signUpButton;
	
	public SignUpPanel() {
		//setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setLayout(new FlowLayout());
		signUsername = new JTextField(10);
		signPassword = new JPasswordField(10);
		signCheckPassword = new JPasswordField(10);
		
		signUsernameLabel = new JLabel("Username: ");
		signPasswordLabel = new JLabel("Password: ");
		signCheckPasswordLabel = new JLabel("Please re-enter your password: ");
		signUpButton = new JButton("Sign Up");
		signUpButton.setFocusable(false);
		signUpButton.setBackground(Color.GRAY);
		signUpButton.setOpaque(false);
		
		add(signUsernameLabel);
		add(signUsername);
		
		add(signPasswordLabel);
		add(signPassword);
		
		add(signCheckPasswordLabel);
		add(signCheckPassword);

		add(signUpButton);
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
}
