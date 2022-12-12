package UI;
import java.awt.*;

import javax.swing.*;

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
	
	private MainScreenPanel mainScreen;
	private MainScreenPanelButtonsHandler mainButtonHandler;

	private SignUpPanel signupPanel;
	private SignUpButtonHandler signupHandler;

	private Timer timer;
	private JLabel timerAsSecond;
	private int second;

	private JButton buildModeSubmitButton;
	private BuildPanel buildPanel;
	
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
		signupPanel.getBackButton().addActionListener(signupHandler);
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
		requestFocus();
	}
	
	public void backToMainView() {
		signupPanel.setVisible(false);
		signupPanel.setEnabled(false);
		remove(signupPanel);

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
		BuildMode buildMode = new BuildMode(this, buildPanel);
		buildModeSubmitButton = new JButton("Submit");
		add(buildModeSubmitButton, BorderLayout.SOUTH);
		BuildModeButtonHandler buildModeButtonHandler = new BuildModeButtonHandler(this, buildMode, buildPanel);
		buildModeSubmitButton.addActionListener(buildModeButtonHandler);
	}

	public void switchGameView(JLabel[][] buildModeMap) {
		
		remove(buildModeSubmitButton);
		remove(buildPanel);
	
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

		lives.setText("Remaining lives: 3");
		lives.setVisible(true);
		add(lives,BorderLayout.NORTH);
		
		key.setText("No Key");
		key.setVisible(true);
		add(key,BorderLayout.WEST);
		
		add(pauseButton,BorderLayout.EAST);
		gamePanel = new GamePanel(this);
		add(gamePanel,BorderLayout.CENTER);

		gamePanel.setGameMap(buildModeMap);
		
		gamePanel.requestFocus();
		gamePanel.addKeyListener(keyHandler);
		
		timerAsSecond = GameTime.getInstance().getTimerAsSecond();
		timerAsSecond.setSize(50,50);

		add(timerAsSecond,BorderLayout.SOUTH);

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
			key.setText("Key taken");
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

	public void setRoomKeyHandler(RoomKeyHandler roomKeyHandler) {
		this.roomKeyHandler = roomKeyHandler;
	}
	
	

	
	
}