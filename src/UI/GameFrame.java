package UI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Database.Client;
import dataStructures.Location;
import domain.Player;
import domain.RoomObject;
import Controllers.*;
import domain.powerUps.PowerUp;
import domain.powerUps.ProtectionVestPowerUp;

public class GameFrame extends JFrame {
	private int numRow = 15;
	private int numCol = 29;
	private GamePanel gamePanel;
	private JButton pauseButton;
	private JButton resumeButton;
	private IconFactory iconFactory;
	private ImageIcon gameIcon;
	private Image gameImage;
	
	private JLabel lives;
	private JLabel key;
	private JLabel powerUp = new JLabel();
	
	private KeyHandler keyHandler;
	private RoomKeyHandler roomKeyHandler;

	private ShooterAlienHandler shooterAlienHandler;
	private BlindAlienHandler blindAlienHandler;
//
	private PowerUpHandler powerUpHandler;

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
	
	//Player player;
	BuildModeButtonHandler buildModeButtonHandler;
	PlayerHandler playerHandler;

	private GameController gameController;
	public GameFrame(GameController gameController) {
		
		super("Escape From Koc");

		
		this.gamePanel = new GamePanel(this);
		this.gameController = gameController;
		serviceUsed = "MongoDB";
		client = new Client(serviceUsed);

		loadNewGameScreen = new LoadOrNewGameSelectionScreen();
		iconFactory = IconFactory.getInstance();
		gameIcon = iconFactory.generateIcon("../assets/gameImage.jpg", 0, 0);
		gameImage = gameIcon.getImage();
		setIconImage(gameImage);
		
		lives = new JLabel();
		key = new JLabel();
		loginPanel = new LoginPanel();
		
	}
	
	public void switchLoginView() {
		mainScreen.setVisible(false);
		mainScreen.setEnabled(false);
		remove(mainScreen);
		
		requestFocus();
		
		buttonHandler = new LoginAuthorizationHandler(this);
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
		
		
		newLoadSelectionScreenHandler = new NewOrLoadGameSelectionHandler(gameController, client);
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
	
	public void setBuildModeButtonHandler(BuildModeButtonHandler handler) {
		this.buildModeButtonHandler = handler;
	}
	public void setPlayerHandler(PlayerHandler handler) {
		this.playerHandler = handler;
	}
	public GameController getGameController() {
		return gameController;
	}
public void switchBuildView() {

		
		this.getLoadOrNewScreen().setVisible(false);
		this.getLoadOrNewScreen().setEnabled(false);
		this.remove(this.loadNewGameScreen);
		this.requestFocus();
		
		
		this.setLayout(new BorderLayout());
		//buildPanel = new BuildPanel(this);
		this.add(buildPanel, BorderLayout.CENTER);
		this.setBuildMode(new BuildMode(this, buildPanel));
		this.setObjectList(buildMode.getObjectList());
		setButtonPanel(new JPanel());
		add(buttonPanel,BorderLayout.SOUTH);
		
		setBuildModeSubmitButton(new JButton("Submit"));
		buildModeSubmitButton.setFocusable(false);
		buildModeSubmitButton.setPreferredSize(new Dimension(200,30));
		
		buttonPanel.add(buildModeSubmitButton);
		BuildModeButtonHandler buildModeButtonHandler = new BuildModeButtonHandler(gameController,buildMode, buildPanel);
		buildModeSubmitButton.addActionListener(buildModeButtonHandler);
	}
	public void switchGameView(JLabel[][] buildModeMap) {
		if(this.getBuildModeSubmitButton() != null){
			this.remove(buildModeSubmitButton);
			this.remove(buildPanel);
			this.getButtonPanel().remove(buildModeSubmitButton);
		}
		
		
		
//		player = new Player();
//		roomKeyHandler = new RoomKeyHandler(this, player);
//		playerHandler = new PlayerHandler(player, this);
//
//		keyHandler = new KeyHandler(playerHandler);
//		shooterAlienHandler = new ShooterAlienHandler(player, this);
//		blindAlienHandler = new BlindAlienHandler(player, this);
//		powerUpHandler = new PowerUpHandler(this, player);
	
		this.setPauseButton(new JButton("II"));
		this.getPauseButton().setFocusable(false);
		this.getPauseButton().setSize(50,50);

		this.setResumeButton(new JButton(">"));
		this.getResumeButton().setFocusable(false);
		this.getResumeButton().setVisible(false);
		this.getResumeButton().setSize(50,50);
		
		this.setLevelTime(5 * this.getBuildPanel().getBuildingObjectCounter());
		//GameTime.getInstance().setSeconds(gameFrame.getLevelTime());
		
		GameTime.getInstance().setSeconds(10000000);
		this.setTimer(GameTime.getInstance().getTimer());
		this.getTimer().start();

		this.getPauseButton().addActionListener(e -> {
			if(!GameState.getInstance().isPaused()) {
				GameState.getInstance().setPaused(true);
				this.getTimer().stop();
				this.getPauseButton().setText(">");
				// Pause Game Screen
				this.setPauseDialog(new PausedGameScreen(client));
				this.getPauseDialog().setLocationRelativeTo(this);
				this.getPauseDialog().setBounds(750, 375, 400, 400);
				this.getPauseDialog().setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				this.getPauseDialog().setModal(false);
				this.getPauseDialog().setVisible(true);
				this.getPauseDialog().setEnabled(true);
				//pauseDialog.requestFocus();
				this.getPauseDialog().getReturnToGameButton().addActionListener((ActionListener) new ActionListener () {
					public void actionPerformed(ActionEvent event) {
						GameState.getInstance().setPaused(false);
						pauseDialog.setVisible(false);
						pauseDialog.setEnabled(false);		
						pauseDialog.dispose();
						remove(pauseDialog);
						getTimer().start();
						pauseButton.setText("II");
					}
				});

				this.getPauseDialog().getSaveGameButton().addActionListener((ActionListener) new ActionListener () {
					public void actionPerformed(ActionEvent event) {
						System.out.println("Game is Saving...");
					}
				});
				
			}else{
				GameState.getInstance().setPaused(false);
				this.getPauseDialog().setVisible(false);
				this.getPauseDialog().setEnabled(false);
				this.remove(pauseDialog);
				this.getTimer().start();
				this.getPauseButton().setText("II");}
		});

		this.setGamePanel(new GamePanel(this));
		this.add(this.getGamePanel(),BorderLayout.CENTER);
		
		this.getGamePanel().setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		this.getGamePanel().setGameMap(buildModeMap);
		this.getGamePanel().requestFocus();
		this.getGamePanel().addKeyListener(this.keyHandler);
		this.getGamePanel().alienProducer();
		
		JPanel livesPanel = new JPanel();
		this.add(livesPanel, BorderLayout.NORTH);
		livesPanel.add(this.getLives(), BorderLayout.CENTER);
		this.getLives().setText("Remaining lives: 3");
		this.getLives().setVisible(true);
		
		JPanel keyPanel = new JPanel();
		this.add(keyPanel, BorderLayout.WEST);
		keyPanel.setLayout(new BorderLayout());
		JLabel bagLabel = new JLabel();
		bagLabel.setIcon(this.getGamePanel().getGamePanelIcons()[1]);
		keyPanel.add(bagLabel, BorderLayout.NORTH);
		keyPanel.add(this.getKey(), BorderLayout.CENTER);
		keyPanel.add(this.getPowerUp(), BorderLayout.SOUTH);
		
		this.getButtonPanel().add(this.getPauseButton());
		this.getPauseButton().setPreferredSize(new Dimension(200,30));
		
		this.setTimerAsSecond(GameTime.getInstance().getTimerAsSecond());
		
		JPanel timerPanel = new JPanel();
		JLabel timerLabel = new JLabel();
		timerLabel.setIcon(this.getGamePanel().getGamePanelIcons()[2]);
		this.add(timerPanel,BorderLayout.EAST);
		timerPanel.setLayout(new BorderLayout());
		timerPanel.add(timerLabel, BorderLayout.NORTH);
		timerPanel.add(this.getTimerAsSecond(), BorderLayout.CENTER);
	}

//	public void switchBuildView() {
//
//		
//		loadNewGameScreen.setVisible(false);
//		loadNewGameScreen.setEnabled(false);
//		remove(loadNewGameScreen);
//		requestFocus();
//		
//		
//		setLayout(new BorderLayout());
//		//buildPanel = new BuildPanel(this);
//		add(buildPanel, BorderLayout.CENTER);
//		buildMode = new BuildMode(this, buildPanel);
//		objectList = buildMode.getObjectList();
//		buttonPanel = new JPanel();
//		add(buttonPanel,BorderLayout.SOUTH);
//		
//		buildModeSubmitButton = new JButton("Submit");
//		buildModeSubmitButton.setFocusable(false);
//		buildModeSubmitButton.setPreferredSize(new Dimension(200,30));
//		
//		buttonPanel.add(buildModeSubmitButton);
//		BuildModeButtonHandler buildModeButtonHandler = new BuildModeButtonHandler(buildMode, buildPanel);
//		buildModeSubmitButton.addActionListener(buildModeButtonHandler);
//	}

//	public void switchGameView(JLabel[][] buildModeMap) {
//		if(buildModeSubmitButton != null){
//			remove(buildModeSubmitButton);
//			remove(buildPanel);
//			buttonPanel.remove(buildModeSubmitButton);
//		}
//		
//		
//		
//		player = new Player();
//		roomKeyHandler = new RoomKeyHandler(this, player);
//		playerHandler = new PlayerHandler(player, this);
//
//		keyHandler = new KeyHandler(playerHandler);
//		shooterAlienHandler = new ShooterAlienHandler(player, this);
//		blindAlienHandler = new BlindAlienHandler(player,this);
//		powerUpHandler = new PowerUpHandler(this, player);
//	
//		pauseButton = new JButton("II");
//		pauseButton.setFocusable(false);
//		pauseButton.setSize(50,50);
//
//		resumeButton = new JButton(">");
//		resumeButton.setFocusable(false);
//		resumeButton.setVisible(false);
//		resumeButton.setSize(50,50);
//		
//		levelTime = 5 * buildPanel.getBuildingObjectCounter();
//		GameTime.getInstance().setSeconds(levelTime);
//		timer = GameTime.getInstance().getTimer();
//		timer.start();
//
//		pauseButton.addActionListener(e -> {
//			if(!GameState.getInstance().isPaused()) {
//				GameState.getInstance().setPaused(true);
//				timer.stop();
//				pauseButton.setText(">");
//				// Pause Game Screen
//				pauseDialog = new PausedGameScreen(client);
//				pauseDialog.setLocationRelativeTo(this);
//				pauseDialog.setBounds(750, 375, 400, 400);
//				pauseDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
//				pauseDialog.setModal(false);
//				pauseDialog.setVisible(true);
//				pauseDialog.setEnabled(true);
//				//pauseDialog.requestFocus();
//				pauseDialog.getReturnToGameButton().addActionListener((ActionListener) new ActionListener () {
//					public void actionPerformed(ActionEvent event) {
//						GameState.getInstance().setPaused(false);
//						pauseDialog.setVisible(false);
//						pauseDialog.setEnabled(false);		
//						pauseDialog.dispose();
//						remove(pauseDialog);
//						timer.start();
//						pauseButton.setText("II");
//					}
//				});
//
//				pauseDialog.getSaveGameButton().addActionListener((ActionListener) new ActionListener () {
//					public void actionPerformed(ActionEvent event) {
//						System.out.println("Game is Saving...");
//					}
//				});
//				
//			}else{
//				GameState.getInstance().setPaused(false);
//				pauseDialog.setVisible(false);
//				pauseDialog.setEnabled(false);
//				remove(pauseDialog);
//				timer.start();
//				pauseButton.setText("II");}
//		});
//
//		gamePanel = new GamePanel(this);
//		add(gamePanel,BorderLayout.CENTER);
//		
//		gamePanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
//		gamePanel.setGameMap(buildModeMap);
//		gamePanel.requestFocus();
//		gamePanel.addKeyListener(keyHandler);
//		gamePanel.alienProducer();
//		
//		JPanel livesPanel = new JPanel();
//		add(livesPanel, BorderLayout.NORTH);
//		livesPanel.add(lives, BorderLayout.CENTER);
//		lives.setText("Remaining lives: 3");
//		lives.setVisible(true);
//		
//		JPanel keyPanel = new JPanel();
//		add(keyPanel, BorderLayout.WEST);
//		keyPanel.setLayout(new BorderLayout());
//		JLabel bagLabel = new JLabel();
//		bagLabel.setIcon(gamePanel.getGamePanelIcons()[1]);
//		keyPanel.add(bagLabel, BorderLayout.NORTH);
//		keyPanel.add(key, BorderLayout.CENTER);
//		keyPanel.add(powerUp, BorderLayout.SOUTH);
//		
//		buttonPanel.add(pauseButton);
//		pauseButton.setPreferredSize(new Dimension(200,30));
//		
//		timerAsSecond = GameTime.getInstance().getTimerAsSecond();
//		
//		JPanel timerPanel = new JPanel();
//		JLabel timerLabel = new JLabel();
//		timerLabel.setIcon(gamePanel.getGamePanelIcons()[2]);
//		add(timerPanel,BorderLayout.EAST);
//		timerPanel.setLayout(new BorderLayout());
//		timerPanel.add(timerLabel, BorderLayout.NORTH);
//		timerPanel.add(timerAsSecond, BorderLayout.CENTER);
//	}

	public void showPopUpOnScreen(String message, String popUpType, int MessageType) {
		JOptionPane.showMessageDialog(new JFrame(), message, popUpType,
				MessageType);
	}
	
//	public void updatePlayerLivesView(int life) {
//        lives.setText("Remaining lives: " + life);
//        if(life == 0) {
//        	GameState.getInstance().setGameOver(true);
//        	GameState.getInstance().setPaused(true);
//        	System.out.println("game over!");
//        }
//    }
//	
//	public void updateKeyView(Boolean is_taken) {
//		if(is_taken) {
//			key.setIcon(gamePanel.getGamePanelIcons()[0]);
//			Location doorLocation = buildMode.getDoorLocation();
//			JLabel[][] gameMap = GamePanel.getGameMap();
//			gameMap[doorLocation.getLocationX()][doorLocation.getLocationY()]
//					.setIcon(gamePanel.getOpenDoorIcon());
//		}
//	}
//
//	public void updateBagView(PowerUp powerUp) {
//		if (powerUp instanceof ProtectionVestPowerUp) {
//			this.powerUp.setIcon(gamePanel.getGamePanelIcons()[3]);
//		}
//			else{
//				this.powerUp.setIcon(null);
//
//		}
//	}
//	
//	public void increaseSecond(int second) {
//		int newSecond = GameTime.getInstance().getSeconds() + second;
//		GameTime.getInstance().setSeconds(newSecond);
//	}
	public void updatePlayerLivesView(int life) {
        this.getLives().setText("Remaining lives: " + life);
        if(life == 0) {
        	GameState.getInstance().setGameOver(true);
        	GameState.getInstance().setPaused(true);
        	System.out.println("game over!");
        }
    }
	
	public void updateKeyView(Boolean is_taken) {
		if(is_taken) {
			this.getKey().setIcon(this.getGamePanel().getGamePanelIcons()[0]);
			Location doorLocation = this.getBuildMode().getDoorLocation();
			JLabel[][] gameMap = this.gamePanel.getGameMap();
			gameMap[doorLocation.getLocationX()][doorLocation.getLocationY()]
					.setIcon(this.getGamePanel().getOpenDoorIcon());
		}
	}

	public void updateBagView(PowerUp powerUp) {
		if (powerUp instanceof ProtectionVestPowerUp) {
			this.getPowerUp().setIcon(this.getGamePanel().getGamePanelIcons()[3]);
		}
			else{
				this.getPowerUp().setIcon(null);

		}
	}
	
	public void increaseSecond(int second) {
		int newSecond = GameTime.getInstance().getSeconds() + second;
		GameTime.getInstance().setSeconds(newSecond);
	}
	public LoadOrNewGameSelectionScreen getLoadOrNewScreen() {
		return loadNewGameScreen;
	}
	public GamePanel getGamePanel() {
		return this.gamePanel;
	}
	public void setGamePanel(GamePanel panel) {
		this.gamePanel = panel;
	}
	
	public RoomKeyHandler getRoomKeyHandler() {
		return roomKeyHandler;
	}

	public PowerUpHandler getPowerUpHandler(){
		return this.powerUpHandler;
	}

	public Location getDoorLocation() {
		return buildMode.getDoorLocation();
	}
	
	public void setRoomKeyHandler(RoomKeyHandler roomKeyHandler) {
		this.roomKeyHandler = roomKeyHandler;
	}
	public void setPowerUpHandler(PowerUpHandler powerUpHandler) {
		this.powerUpHandler = powerUpHandler;
	}
	
	public LoginPanel getLoginPanel() {
		return loginPanel;
	}
	
	public ArrayList<RoomObject> getObjectList() {
		return objectList;
	}
	public void setObjectList(ArrayList<RoomObject> list) {
		this.objectList = list;
	}
	public int getLevelTime() {
		return levelTime;
	}
	public void setLevelTime(int levelTime) {
		this.levelTime = levelTime;
	}
	
	public ShooterAlienHandler getShooterAlienHandler() {
		return this.shooterAlienHandler;
	}

	
	public void setShooterAlienHandler(ShooterAlienHandler shooterAlienHandler) {
		this.shooterAlienHandler = shooterAlienHandler;
	}
	
	
	public BlindAlienHandler getBlindAlienHandler() {
		return this.blindAlienHandler;
	}

	
	public void setBlindAlienHandler(BlindAlienHandler blindAlienHandler) {
		this.blindAlienHandler = blindAlienHandler;
	}
	
	public void setPauseButton(JButton button) {
		this.pauseButton = button;
	}
	public void setResumeButton(JButton button) {
		this.resumeButton = button;
	}
	public JButton getPauseButton() {
		return this.pauseButton;
	}
	public JButton getResumeButton() {
		return this.resumeButton;
	}
	public BuildMode getBuildMode() {
		return buildMode;
	}
	
	public JButton getBuildModeSubmitButton() {
		return buildModeSubmitButton;
	}
	public PausedGameScreen getPauseDialog() {
		return this.pauseDialog;
	}
	public void setPauseDialog(PausedGameScreen screen) {
		this.pauseDialog = screen;
	}
	public JPanel getButtonPanel() {
		return buttonPanel;
	}
	public void setBuildMode(BuildMode buildMode) {
		this.buildMode = buildMode;
	}
	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}
	public JLabel getLives() {
		return this.lives;
	}
	public JLabel getTimerAsSecond() {
		return timerAsSecond;
	}
	public void setTimerAsSecond(JLabel label) {
		this.timerAsSecond = label;
	}
	public JLabel getKey() {
		return this.key;
	}
	public JLabel getPowerUp() {
		return this.powerUp;
	}
	public BuildPanel getBuildPanel() {return buildPanel;}
	public void setBuildModeSubmitButton(JButton button) {
		this.buildModeSubmitButton = button;
	}
	public void setButtonPanel(JPanel panel) {
		this.buttonPanel = panel;
	}
	
	
}