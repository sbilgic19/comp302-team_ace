package UI;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class LoginPanel extends JPanel {
	

	
	private final JTextField usernameField;
	private final JPasswordField passwordField;
	private final JLabel usernameLabel;
	private final JLabel passwordLabel;
	private final JButton loginButton;
	
	public LoginPanel() {
		
		setLayout(new FlowLayout());
		
		usernameLabel = new JLabel("Username:");
		passwordLabel = new JLabel("Password:");
		usernameField = new JTextField(10);
		passwordField = new JPasswordField(10);
		
		loginButton = new JButton("Login");
		loginButton.setFocusable(false);
		
		add(usernameLabel);
		add(usernameField);
		add(passwordLabel);
		add(passwordField);
		add(loginButton);
		
		
		
	}

	public JButton getLoginButton() {
		return loginButton;
	}
	
	public String getUsernameMessage() {
		return usernameField.getText();
	}
	
	public String getPasswordMessage() {
		return new String(passwordField.getPassword());
	}
	
}
