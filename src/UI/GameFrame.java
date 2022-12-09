package UI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import Controllers.LoginAuthorizationHandler;
import Controllers.BuildModeButtonHandler;
import Controllers.KeyHandler;
import Controllers.MainScreenPanelButtonsHandler;
import Controllers.SignUpButtonHandler;

public class GameFrame extends JFrame {

	private int numRow = 15; 
	private int numCol = 29;
	
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JButton loginButton;
	
	private IconFactory iconFactory;
	private ImageIcon gameIcon;
	private ImageIcon playerIcon;
	private ImageIcon barrelIcon;
	private ImageIcon doorIcon;
	private ImageIcon jarIcon;
	private ImageIcon vaultIcon;
	private ImageIcon windowIcon;
	private ImageIcon woodenBoxIcon;
	private ImageIcon firstTreeIcon;
	private ImageIcon secondTreeIcon;
	private ImageIcon thirdTreeIcon;
	private ImageIcon woodIcon;
	private ImageIcon eraserIcon;
	private Image gameImage;
	
	private JLabel[][] gameMap;
	
	private LoginAuthorizationHandler buttonHandler;
	private KeyHandler keyHandler;
	
	private MainScreenPanel mainScreen;
	private MainScreenPanelButtonsHandler mainButtonHandler;
	
	private SignUpPanel signupPanel;
	private SignUpButtonHandler signupHandler;
	
	private JButton buildModeSubmitButton;
	
	private JPanel gamePanel;
	private int panelHeight;
	private int panelWidth;
	
	public GameFrame() {
		
		super("Escape From Koç");
	
		handleIcons();
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
	
	private void handleIcons() {
		iconFactory = IconFactory.getInstance();
		gameIcon = iconFactory.generateIcon("../assets/gameImage.jpg", 0, 0);
		playerIcon = iconFactory.generateIcon("../assets/playerIcon.png", 50, 50);
		barrelIcon = iconFactory.generateIcon("../assets/barrelIcon.png", 50, 50);
		doorIcon = iconFactory.generateIcon("../assets/doorIcon.png", 50, 50);
		jarIcon = iconFactory.generateIcon("../assets/jarIcon.png", 50, 50);
		vaultIcon = iconFactory.generateIcon("../assets/vaultIcon.png", 50, 50);
		windowIcon = iconFactory.generateIcon("../assets/windowIcon.png", 50, 50);
		woodenBoxIcon = iconFactory.generateIcon("../assets/woodenBoxIcon.png", 50, 50);
		firstTreeIcon = iconFactory.generateIcon("../assets/treeIcon1.png", 50, 50);
		secondTreeIcon = iconFactory.generateIcon("../assets/treeIcon2.png", 50, 50);
		thirdTreeIcon = iconFactory.generateIcon("../assets/treeIcon3.png", 50, 50);
		woodIcon = iconFactory.generateIcon("../assets/woodIcon.png", 50, 50);
		eraserIcon = iconFactory.generateIcon("../assets/eraserIcon.png", 50, 50);
	}
	
	public ImageIcon[] getIcons() {
		ImageIcon[] icons = {barrelIcon, doorIcon, jarIcon, vaultIcon, windowIcon, woodenBoxIcon, 
				firstTreeIcon, secondTreeIcon, thirdTreeIcon, woodIcon, eraserIcon};
		return icons;
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

	public void setButtonHandler(LoginAuthorizationHandler buttonHandler) {
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
		
		Insets insets = getInsets();
		
		setLayout(new BorderLayout());
		gamePanel = new JPanel();
		panelWidth = 1500 - (insets.left + insets.right);
		panelHeight = 750 - (insets.top + insets.bottom);
		gamePanel.setPreferredSize(new Dimension(panelWidth, panelHeight)); 
		add(gamePanel, BorderLayout.CENTER);
		pack();		
		
		gamePanel.requestFocus();
		
		gamePanel.setLayout(new GridLayout(numRow, numCol, 0, 0));
		BuildMode buildMode = new BuildMode(this, gamePanel);
		
		buildModeSubmitButton = new JButton("Submit");
		add(buildModeSubmitButton, BorderLayout.SOUTH);
		BuildModeButtonHandler buildModeButtonHandler = new BuildModeButtonHandler(this, buildMode);
		buildModeSubmitButton.addActionListener(buildModeButtonHandler);
	}
	public void showPopUpOnScreen(String message, String popUpType, int MessageType) {
		JOptionPane.showMessageDialog(new JFrame(), message, popUpType,
				MessageType);
	}
	public void setGameMap(BuildMode buildMode) {	
		JLabel[][] buildModeMap = buildMode.getBuildModeMap();
		gameMap = new JLabel[numRow][numCol];
		for (int ii = 0; ii < numRow; ii++) {
			for (int jj = 0; jj < numCol; jj++) {
				gameMap[ii][jj] = buildModeMap[ii][jj];
				gameMap[ii][jj].setBorder(null);
			}	
		} 	
		for (int ii = 0; ii < numRow; ii++) {
			gamePanel.remove(buildModeMap[ii][numCol]);
		}
		remove(buildModeSubmitButton);
		buildMode.removeMouseHandler();
		
		requestFocus();
		gameMap[0][0].setIcon(playerIcon);
		addKeyListener(keyHandler);
	}
	
	public void updatePlayerView(int xPlayerPosition, int yPlayerPosition,  
			int newXPlayerPosition, int newYPlayerPosition) {
		gameMap[xPlayerPosition][yPlayerPosition].setIcon(null);
		gameMap[newXPlayerPosition][newYPlayerPosition].setIcon(playerIcon);
	}
}