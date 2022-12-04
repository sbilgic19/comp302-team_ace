package UI;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import Application.ButtonHandler;
import Application.KeyHandler;
import Application.MainScreenPanelButtonsHandler;
import Application.SignUpButtonHandler;

public class GameFrame extends JFrame {

	private int numRow = 10; 
	private int numCol = 15;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JButton loginButton;
	
	private IconFactory iconFactory;
	private ImageIcon gameIcon;
	private ImageIcon playerIcon;
	private Image gameImage;
	
	private JLabel[][] gameMap;
	
	private ButtonHandler buttonHandler;
	private KeyHandler keyHandler;
	
	private MainScreenPanel mainScreen;
	private MainScreenPanelButtonsHandler mainButtonHandler;
	
	private SignUpPanel signupPanel;
	private SignUpButtonHandler signupHandler;
	public GameFrame() {
		
		super("Escape From Koç");
		
		
		
		iconFactory = IconFactory.getInstance();
		gameIcon = iconFactory.generateIcon("gameImage.jpg", 0, 0);
		playerIcon = iconFactory.generateIcon("playerIcon.png", 40, 40);
		gameImage = gameIcon.getImage();
		setIconImage(gameImage);
		
		usernameLabel = new JLabel("Username:");
		passwordLabel = new JLabel("Password:");
		usernameField = new JTextField(10);
		passwordField = new JPasswordField(10);
		
		loginButton = new JButton("Login");
		loginButton.setFocusable(false);
		loginButton.setBackground(Color.GRAY);
		loginButton.setOpaque(false);
	}
	
	public void switchLoginView() {
		mainScreen.setVisible(false);
		mainScreen.setEnabled(false);
		remove(mainScreen);
		
		requestFocus();
		setLayout(new FlowLayout());
		
		add(usernameLabel);
		add(usernameField);
		add(passwordLabel);
		add(passwordField);
		add(loginButton);
		
		loginButton.addActionListener(buttonHandler);
	}
	public void switchSignUpView() {
		mainScreen.setVisible(false);
		mainScreen.setEnabled(false);
		remove(mainScreen);
		
		signupPanel = new SignUpPanel();
		signupHandler = new SignUpButtonHandler(this);
		add(signupPanel);
		signupPanel.getSignupButton().addActionListener(signupHandler);
		signupPanel.setVisible(true);
		
		requestFocus();
		
		
	}
	
		
	public void showMainView() {
		mainScreen = new MainScreenPanel();
		add(mainScreen);
		mainButtonHandler = new MainScreenPanelButtonsHandler(this);
		mainScreen.setVisible(true);
		mainScreen.getLoginButton().addActionListener(mainButtonHandler);
		mainScreen.getSigninButton().addActionListener(mainButtonHandler);
	}
	
	public void setButtonHandler(ButtonHandler buttonHandler) {
		this.buttonHandler = buttonHandler;
	}
	
	public void setKeyHandler(KeyHandler keyHandler) {
		this.keyHandler = keyHandler;
	}
	
	// Sign Up Check i için
	public String getSignupUsername() {
		return signupPanel.getSignupUsername();
	}
	
	public String getSignupPassword() {
		return signupPanel.getSignupPassword();
	}
	
	public String getSignupCheckPassword() {
		return signupPanel.getSignupCheckPassword();
	}
	//
	
	public String getUsernameMessage() {
		return usernameField.getText();
	}
	
	public String getPasswordMessage() {
		return new String(passwordField.getPassword());
	}
	
	public int getNumRow() {
		return numRow;
	}
	
	public int getNumCol() {
		return numCol;
	}
	
	public void switchGameView() {
		remove(usernameLabel);
		remove(usernameField);
		remove(passwordLabel);
		remove(passwordField);
		remove(loginButton);
		
		requestFocus();
		
		setLayout(new GridLayout(numRow, numCol, 0, 0));
		setGameMap();
		
		addKeyListener(keyHandler);
	
		gameMap[0][0].setIcon(playerIcon);
	}
	public void showPopUpOnScreen(String message, String popUpType, int MessageType) {
		JOptionPane.showMessageDialog(new JFrame(), message, popUpType,
				MessageType);
	}
	private void setGameMap() {	
		gameMap = new JLabel[numRow][numCol];
		for (int ii = 0; ii < numRow; ii++) {
			for (int jj = 0; jj < numCol; jj++) {
				gameMap[ii][jj] = new JLabel();
				add(gameMap[ii][jj]);
			}	
		} 	
	}
	
	public void updatePlayerView(int xPlayerPosition, int yPlayerPosition,  
			int newXPlayerPosition, int newYPlayerPosition) {
		gameMap[xPlayerPosition][yPlayerPosition].setIcon(null);
		gameMap[newXPlayerPosition][newYPlayerPosition].setIcon(playerIcon);
	}
}