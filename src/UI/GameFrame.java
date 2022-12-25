package UI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Database.Client;
import dataStructures.Location;
import domain.RoomObject;
import Controllers.*;

public class GameFrame extends JFrame {
	private int numRow = 15;
	private int numCol = 29;
	private GamePanel gamePanel;
//	private JTextField usernameField;
//	private JPasswordField passwordField;
//	private JLabel usernameLabel;
//	private JLabel passwordLabel;
//	private JButton loginButton;
	private JButton pauseButton;
	private JButton resumeButton;
	private IconFactory iconFactory;
	private ImageIcon gameIcon;
	private Image gameImage;
	
	private JLabel lives;
	private JLabel key;
	
	private KeyHandler keyHandler;
	private RoomKeyHandler roomKeyHandler;
	private JPanel buttonPanel;
	
	private LoginPanel loginPanel;
	private LoginAuthorizationHandler buttonHandler;
	
	private MainScreenPanel mainScreen;
	private MainScreenPanelButtonsHandler mainButtonHandler;
	
	private LoadOrNewGameSelectionScreen loadNewGameScreen;
	private NewOrLoadGameSelectionHandler newLoadSelectionScreenHandler;
	
	private InfoViewPanel infoPanel = new InfoViewPanel();

	private SignUpPanel signupPanel = new SignUpPanel();
	private SignUpButtonHandler signupHandler;

	private Timer timer;
	private JLabel timerAsSecond;
	private int second;

	private JButton buildModeSubmitButton;
	private BuildPanel buildPanel = new BuildPanel(this);;
	private BuildMode buildMode;
	
	private PausedGameScreen pauseDialog;

	private int levelTime;
	
	private ArrayList<RoomObject> objectList;
  
  private Client client;
	private String serviceUsed;
	
	public GameFrame() {
		
		super("Escape From Koc");

		serviceUsed = "MongoDB";
		client = new Client(serviceUsed);

		loadNewGameScreen = new LoadOrNewGameSelectionScreen();
		iconFactory = IconFactory.getInstance();
		gameIcon = iconFactory.generateIcon("../assets/gameImage.jpg", 0, 0);
		gameImage = gameIcon.getImage();
		setIconImage(gameImage);
		
//		usernameLabel = new JLabel("Username:");
//		passwordLabel = new JLabel("Password:");
//		usernameField = new JTextField(10);
//		passwordField = new JPasswordField(10);
		lives = new JLabel();
		key = new JLabel();
		loginPanel = new LoginPanel();
//		loginButton = new JButton("Login");
//		loginButton.setFocusable(false);
	}
	
	public void switchLoginView() {
		mainScreen.setVisible(false);
		mainScreen.setEnabled(false);
		remove(mainScreen);
		
		requestFocus();
		
		loginPanel.getLoginButton().addActionListener(buttonHandler);
		add(loginPanel);
		loginPanel.setVisible(true);
		requestFocus();
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
	
	public void showNewLoadGameSelectionView() {
		loginPanel.setVisible(false);
		loginPanel.setEnabled(false);
		remove(loginPanel);
		
		
		requestFocus();
		
		add(loadNewGameScreen);
		
		
		newLoadSelectionScreenHandler = new NewOrLoadGameSelectionHandler(this, client);
		loadNewGameScreen.paintComponent(getGraphics());
		loadNewGameScreen.setVisible(true);
		
		loadNewGameScreen.getNewGameButton().addActionListener(newLoadSelectionScreenHandler);
		loadNewGameScreen.getLoadGameButton().addActionListener(newLoadSelectionScreenHandler);
		
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
	
	public int getNumRow(){return numRow;}

	public int getNumCol(){return numCol;}

	public void switchBuildView() {
//		remove(usernameLabel);
//		remove(usernameField);
//		remove(passwordLabel);
//		remove(passwordField);
//		remove(loginButton);
		loadNewGameScreen.setVisible(false);
		loadNewGameScreen.setEnabled(false);
		remove(loadNewGameScreen);
		requestFocus();
		
		
		setLayout(new BorderLayout());
		//buildPanel = new BuildPanel(this);
		add(buildPanel, BorderLayout.CENTER);
		buildMode = new BuildMode(this, buildPanel);
		objectList = buildMode.getObjectList();
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
		if(buildModeSubmitButton != null){
			remove(buildModeSubmitButton);
			remove(buildPanel);
			buttonPanel.remove(buildModeSubmitButton);
		}
	
		pauseButton = new JButton("II");
		pauseButton.setFocusable(false);
		pauseButton.setSize(50,50);

		resumeButton = new JButton(">");
		resumeButton.setFocusable(false);
		resumeButton.setVisible(false);
		resumeButton.setSize(50,50);
		
		levelTime = 5 * buildPanel.getBuildingObjectCounter();
		GameTime.getInstance().setSeconds(levelTime);
		timer = GameTime.getInstance().getTimer();
		timer.start();

		pauseButton.addActionListener(e -> {
			if(!GameState.getInstance().isPaused()) {
				GameState.getInstance().setPaused(true);
				timer.stop();
				pauseButton.setText(">");
				// Pause Game Screen
				pauseDialog = new PausedGameScreen(client);
				pauseDialog.setLocationRelativeTo(this);
				pauseDialog.setBounds(750, 375, 400, 400);
				pauseDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				pauseDialog.setModal(false);
				pauseDialog.setVisible(true);
				pauseDialog.setEnabled(true);
				//pauseDialog.requestFocus();
				pauseDialog.getReturnToGameButton().addActionListener((ActionListener) new ActionListener () {
					public void actionPerformed(ActionEvent event) {
						GameState.getInstance().setPaused(false);
						pauseDialog.setVisible(false);
						pauseDialog.setEnabled(false);		
						pauseDialog.dispose();
						remove(pauseDialog);
						timer.start();
						pauseButton.setText("II");
					}
				});

				pauseDialog.getSaveGameButton().addActionListener((ActionListener) new ActionListener () {
					public void actionPerformed(ActionEvent event) {
						System.out.println("Game is Saving...");
					}
				});
				
			}else{
				GameState.getInstance().setPaused(false);
				pauseDialog.setVisible(false);
				pauseDialog.setEnabled(false);
				remove(pauseDialog);
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
			Location doorLocation = buildMode.getDoorLocation();
			JLabel[][] gameMap = GamePanel.getGameMap();
			gameMap[doorLocation.getLocationX()][doorLocation.getLocationY()]
					.setIcon(gamePanel.getOpenDoorIcon());
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
	
	public LoginPanel getLoginPanel() {
		return loginPanel;
	}
	
	public ArrayList<RoomObject> getObjectList() {
		return objectList;
	}
	
	public int getLevelTime() {
		return levelTime;
	}

	public BuildPanel getBuildPanel() {return buildPanel;}
}