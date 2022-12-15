package UI;
import java.awt.*;

import javax.swing.*;

import dataStructures.Location;

import Controllers.*;

public class GameFrame extends JFrame {
	private int numRow = 15;
	private int numCol = 29;
	private GamePanel gamePanel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JButton loginButton;
	private JButton pauseButton;
	private JButton resumeButton;
	private IconFactory iconFactory;
	private ImageIcon gameIcon;
	private Image gameImage;
	
	private JLabel lives;
	private JLabel key;
	private LoginAuthorizationHandler buttonHandler;
	private KeyHandler keyHandler;
	private RoomKeyHandler roomKeyHandler;
	private JPanel buttonPanel;
	
	private MainScreenPanel mainScreen;
	private MainScreenPanelButtonsHandler mainButtonHandler;
	
	private InfoViewPanel infoPanel = new InfoViewPanel();

	private SignUpPanel signupPanel = new SignUpPanel();
	private SignUpButtonHandler signupHandler;

	private Timer timer;
	private JLabel timerAsSecond;
	private int second;

	private JButton buildModeSubmitButton;
	private BuildPanel buildPanel;
	private BuildMode buildMode;
	
	public GameFrame() {
		
		super("Escape From Koc");
		
		iconFactory = IconFactory.getInstance();
		gameIcon = iconFactory.generateIcon("../assets/gameImage.jpg", 0, 0);
		gameImage = gameIcon.getImage();
		setIconImage(gameImage);
		
		usernameLabel = new JLabel("Username:");
		passwordLabel = new JLabel("Password:");
		usernameField = new JTextField(10);
		passwordField = new JPasswordField(10);
		lives = new JLabel();
		key = new JLabel();
		
		loginButton = new JButton("Login");
		loginButton.setFocusable(false);
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
		
		//signupPanel = new SignUpPanel();
		signupHandler = new SignUpButtonHandler(this);
		signupPanel.setIsOn(true);
		add(signupPanel);
		signupPanel.getSignupButton().addActionListener(signupHandler);
		signupPanel.getBackButton().addActionListener(signupHandler);
		signupPanel.setVisible(true);
		
		requestFocus();
	}
	
	public void showInfoView() {
		mainScreen.setVisible(false);
		mainScreen.setEnabled(false);
		remove(mainScreen);
		
		
		infoPanel.setIsOn(true);
		add(infoPanel);
		infoPanel.setVisible(true);
		infoPanel.getInfoPanelBackButton().addActionListener((e) -> {
			this.backToMainView();
		});
	}
	public void showMainView() {
		mainScreen = new MainScreenPanel();
		add(mainScreen);
		mainButtonHandler = new MainScreenPanelButtonsHandler(this);
		mainScreen.setVisible(true);
		mainScreen.getLoginButton().addActionListener(mainButtonHandler);
		mainScreen.getSigninButton().addActionListener(mainButtonHandler);
		mainScreen.getInfoButton().addActionListener(mainButtonHandler);
		requestFocus();
	}
	
	
	public void backToMainView() {
		
		if (infoPanel.getIsOn()) {
			infoPanel.setIsOn(false);
			infoPanel.setVisible(false);
			infoPanel.setEnabled(false);
			remove(infoPanel);
		}
		if (signupPanel.getIsOn()) {
			signupPanel.setIsOn(false);
			signupPanel.setVisible(false);
			signupPanel.setEnabled(false);
			remove(signupPanel);
		}
		

		showMainView();
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

	public int getNumRow(){return numRow;}

	public int getNumCol(){return numCol;}

	public void switchBuildView() {
		remove(usernameLabel);
		remove(usernameField);
		remove(passwordLabel);
		remove(passwordField);
		remove(loginButton);
		
		setLayout(new BorderLayout());
		buildPanel = new BuildPanel(this);
		add(buildPanel, BorderLayout.CENTER);
		buildMode = new BuildMode(this, buildPanel);
		buttonPanel = new JPanel();
		add(buttonPanel,BorderLayout.SOUTH);
		
		buildModeSubmitButton = new JButton("Submit");
		buildModeSubmitButton.setFocusable(false);
		buildModeSubmitButton.setPreferredSize(new Dimension(200,30));
		
		buttonPanel.add(buildModeSubmitButton);
		BuildModeButtonHandler buildModeButtonHandler = new BuildModeButtonHandler(this, buildMode, buildPanel);
		buildModeSubmitButton.addActionListener(buildModeButtonHandler);
	}

	public void switchGameView(JLabel[][] buildModeMap) {
		
		remove(buildModeSubmitButton);
		remove(buildPanel);
		buttonPanel.remove(buildModeSubmitButton);
	
		pauseButton = new JButton("II");
		pauseButton.setFocusable(false);
		pauseButton.setSize(50,50);

		resumeButton = new JButton(">");
		resumeButton.setFocusable(false);
		resumeButton.setVisible(false);
		resumeButton.setSize(50,50);
		
		GameTime.getInstance().setSeconds(5*buildPanel.getBuildingObjectCounter());
		timer = GameTime.getInstance().getTimer();
		timer.start();

		pauseButton.addActionListener(e -> {
			if(!GameState.getInstance().isPaused()) {
				GameState.getInstance().setPaused(true);
				timer.stop();
				pauseButton.setText(">");
			}else{
				GameState.getInstance().setPaused(false);
				timer.start();
				pauseButton.setText("II");}
		});

		gamePanel = new GamePanel(this);
		add(gamePanel,BorderLayout.CENTER);
		
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		gamePanel.setGameMap(buildModeMap);
		
		gamePanel.requestFocus();
		gamePanel.addKeyListener(keyHandler);
		
		JPanel livesPanel = new JPanel();
		add(livesPanel, BorderLayout.NORTH);
		livesPanel.add(lives, BorderLayout.CENTER);
		lives.setText("Remaining lives: 3");
		lives.setVisible(true);
		
		JPanel keyPanel = new JPanel();
		add(keyPanel, BorderLayout.WEST);
		keyPanel.setLayout(new BorderLayout());
		JLabel bagLabel = new JLabel();
		bagLabel.setIcon(gamePanel.getGamePanelIcons()[1]);
		keyPanel.add(bagLabel, BorderLayout.NORTH);
		keyPanel.add(key, BorderLayout.CENTER);
		
		buttonPanel.add(pauseButton);
		pauseButton.setPreferredSize(new Dimension(200,30));
		
		timerAsSecond = GameTime.getInstance().getTimerAsSecond();

		JPanel timerPanel = new JPanel();
		JLabel timerLabel = new JLabel();
		timerLabel.setIcon(gamePanel.getGamePanelIcons()[2]);
		add(timerPanel,BorderLayout.EAST);
		timerPanel.setLayout(new BorderLayout());
		timerPanel.add(timerLabel, BorderLayout.NORTH);
		timerPanel.add(timerAsSecond, BorderLayout.CENTER);
	}

	public void showPopUpOnScreen(String message, String popUpType, int MessageType) {
		JOptionPane.showMessageDialog(new JFrame(), message, popUpType,
				MessageType);
	}
	
	public void updatePlayerLivesView(int life) {
        lives.setText("Remaining lives: " + life);
    }
	
	public void updateKeyView(Boolean is_taken) {
		if(is_taken) {
			key.setIcon(gamePanel.getGamePanelIcons()[0]);
		}
	}
	
	public void increaseSecond(int second) {
		int newSecond = GameTime.getInstance().getSeconds() + second;
		GameTime.getInstance().setSeconds(newSecond);
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public RoomKeyHandler getRoomKeyHandler() {
		return roomKeyHandler;
	}

	public Location getDoorLocation() {
		return buildMode.getDoorLocation();
	}
	public void setRoomKeyHandler(RoomKeyHandler roomKeyHandler) {
		this.roomKeyHandler = roomKeyHandler;
	}
}