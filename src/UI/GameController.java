package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controllers.BlindAlienHandler;
import Controllers.BuildModeButtonHandler;
import Controllers.KeyHandler;
import Controllers.LoginAuthorizationHandler;
import Controllers.PlayerHandler;
import Controllers.PowerUpHandler;
import Controllers.RoomKeyHandler;
import Controllers.ShooterAlienHandler;
import Database.Client;
import domain.GameInfo;
import domain.Player;
import domain.powerUps.PowerUpFactory;

public class GameController {

	private static int frameWidth = 1500;
	private static int frameHeight = 750;
	
	private GameFrame gameFrame;
	private GamePanel gamePanel;
	
	private Client client;
	private String serviceUsed;
	
	private Player player;
	private RoomKeyHandler roomKeyHandler;
	private PlayerHandler playerHandler;
	private KeyHandler keyHandler;
	private ShooterAlienHandler shooterAlienHandler;
	private BlindAlienHandler blindAlienHandler;
	private PowerUpHandler powerUpHandler;
	
	public GameController() {
		
		serviceUsed = "MongoDB";
		client = new Client(serviceUsed);
		gameFrame = new GameFrame(this);
		gamePanel = new GamePanel(gameFrame);
		player = new Player();
		roomKeyHandler = new RoomKeyHandler(gameFrame, player);
		
		
	}
	public void createAndShowGUI(){
		//Authorization.addUserToRecord(new User("nsavran", "123456"));

		Player player = new Player();
		RoomKeyHandler roomKeyHandler = new RoomKeyHandler(gameFrame, player);
		PlayerHandler playerHandler = new PlayerHandler(player, gameFrame);

		KeyHandler keyHandler = new KeyHandler(playerHandler);
		ShooterAlienHandler shooterAlienHandler = new ShooterAlienHandler(player, gameFrame);
		BlindAlienHandler blindAlienHandler = new BlindAlienHandler(player,gameFrame);
		PowerUpHandler powerUpHandler = new PowerUpHandler(gameFrame, player);



		GameInfo.getInstance().setPlayer(player);
		PowerUpFactory.getInstance().setPlayer(player);
		
		gameFrame.setShooterAlienHandler(shooterAlienHandler);
		gameFrame.setBlindAlienHandler(blindAlienHandler);
		gameFrame.setKeyHandler(keyHandler);
		gameFrame.setRoomKeyHandler(roomKeyHandler);
		gameFrame.setPowerUpHandler(powerUpHandler);
		gameFrame.showMainView();
		GamePanel.setPlayer(player);
		
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(frameWidth, frameHeight);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(false);
		gameFrame.setVisible(true);
	}
	
	public GameFrame getGameFrame() {
		return this.gameFrame;
	}
	
	public void switchBuildView() {

		
		gameFrame.getLoadOrNewScreen().setVisible(false);
		gameFrame.getLoadOrNewScreen().setEnabled(false);
		gameFrame.remove(gameFrame.getLoadOrNewScreen());
		gameFrame.requestFocus();
		
		
		gameFrame.setLayout(new BorderLayout());
		//buildPanel = new BuildPanel(this);
		gameFrame.add(gameFrame.getBuildPanel(), BorderLayout.CENTER);
		gameFrame.setBuildMode(new BuildMode(this.gameFrame, gameFrame.getBuildPanel()));
		gameFrame.setObjectList(gameFrame.getBuildMode().getObjectList());
		gameFrame.setButtonPanel(new JPanel());
		gameFrame.add(gameFrame.getButtonPanel(),BorderLayout.SOUTH);
		
		gameFrame.setBuildModeSubmitButton(new JButton("Submit"));
		gameFrame.getBuildModeSubmitButton().setFocusable(false);
		gameFrame.getBuildModeSubmitButton().setPreferredSize(new Dimension(200,30));
		
		gameFrame.getButtonPanel().add(gameFrame.getBuildModeSubmitButton());
		BuildModeButtonHandler buildModeButtonHandler = new BuildModeButtonHandler(this,gameFrame.getBuildMode(), gameFrame.getBuildPanel());
		gameFrame.getBuildModeSubmitButton().addActionListener(buildModeButtonHandler);
	}
	public void switchGameView(JLabel[][] buildModeMap) {
		if(gameFrame.getBuildModeSubmitButton() != null){
			gameFrame.remove(gameFrame.getBuildModeSubmitButton());
			gameFrame.remove(gameFrame.getBuildPanel());
			gameFrame.getButtonPanel().remove(gameFrame.getBuildModeSubmitButton());
		}
		
		
		
		player = new Player();
		roomKeyHandler = new RoomKeyHandler(gameFrame, player);
		playerHandler = new PlayerHandler(player, gameFrame);

		keyHandler = new KeyHandler(playerHandler);
		shooterAlienHandler = new ShooterAlienHandler(player, gameFrame);
		blindAlienHandler = new BlindAlienHandler(player, gameFrame);
		powerUpHandler = new PowerUpHandler(gameFrame, player);
	
		gameFrame.setPauseButton(new JButton("II"));
		gameFrame.getPauseButton().setFocusable(false);
		gameFrame.getPauseButton().setSize(50,50);

		gameFrame.setResumeButton(new JButton(">"));
		gameFrame.getResumeButton().setFocusable(false);
		gameFrame.getResumeButton().setVisible(false);
		gameFrame.getResumeButton().setSize(50,50);
		
		gameFrame.setLevelTime(5 * gameFrame.getBuildPanel().getBuildingObjectCounter());
		GameTime.getInstance().setSeconds(gameFrame.getLevelTime());
		gameFrame.setTimer(GameTime.getInstance().getTimer());
		gameFrame.getTimer().start();

		gameFrame.getPauseButton().addActionListener(e -> {
			if(!GameState.getInstance().isPaused()) {
				GameState.getInstance().setPaused(true);
				gameFrame.getTimer().stop();
				gameFrame.getPauseButton().setText(">");
				// Pause Game Screen
				gameFrame.setPauseDialog(new PausedGameScreen(client));
				gameFrame.getPauseDialog().setLocationRelativeTo(gameFrame);
				gameFrame.getPauseDialog().setBounds(750, 375, 400, 400);
				gameFrame.getPauseDialog().setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
				gameFrame.getPauseDialog().setModal(false);
				gameFrame.getPauseDialog().setVisible(true);
				gameFrame.getPauseDialog().setEnabled(true);
				//pauseDialog.requestFocus();
				gameFrame.getPauseDialog().getReturnToGameButton().addActionListener((ActionListener) new ActionListener () {
					public void actionPerformed(ActionEvent event) {
						GameState.getInstance().setPaused(false);
						gameFrame.getPauseDialog().setVisible(false);
						gameFrame.getPauseDialog().setEnabled(false);		
						gameFrame.getPauseDialog().dispose();
						gameFrame.remove(gameFrame.getPauseDialog());
						gameFrame.getTimer().start();
						gameFrame.getPauseButton().setText("II");
					}
				});

				gameFrame.getPauseDialog().getSaveGameButton().addActionListener((ActionListener) new ActionListener () {
					public void actionPerformed(ActionEvent event) {
						System.out.println("Game is Saving...");
					}
				});
				
			}else{
				GameState.getInstance().setPaused(false);
				gameFrame.getPauseDialog().setVisible(false);
				gameFrame.getPauseDialog().setEnabled(false);
				gameFrame.remove(gameFrame.getPauseDialog());
				gameFrame.getTimer().start();
				gameFrame.getPauseButton().setText("II");}
		});

		gamePanel = new GamePanel(gameFrame);
		gameFrame.add(gamePanel,BorderLayout.CENTER);
		
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		gamePanel.setGameMap(buildModeMap);
		gamePanel.requestFocus();
		gamePanel.addKeyListener(this.keyHandler);
		gamePanel.alienProducer();
		
		JPanel livesPanel = new JPanel();
		gameFrame.add(livesPanel, BorderLayout.NORTH);
		livesPanel.add(gameFrame.getLives(), BorderLayout.CENTER);
		gameFrame.getLives().setText("Remaining lives: 3");
		gameFrame.getLives().setVisible(true);
		
		JPanel keyPanel = new JPanel();
		gameFrame.add(keyPanel, BorderLayout.WEST);
		keyPanel.setLayout(new BorderLayout());
		JLabel bagLabel = new JLabel();
		bagLabel.setIcon(gamePanel.getGamePanelIcons()[1]);
		keyPanel.add(bagLabel, BorderLayout.NORTH);
		keyPanel.add(gameFrame.getKey(), BorderLayout.CENTER);
		keyPanel.add(gameFrame.getPowerUp(), BorderLayout.SOUTH);
		
		gameFrame.getButtonPanel().add(gameFrame.getPauseButton());
		gameFrame.getPauseButton().setPreferredSize(new Dimension(200,30));
		
		gameFrame.setTimerAsSecond(GameTime.getInstance().getTimerAsSecond());
		
		JPanel timerPanel = new JPanel();
		JLabel timerLabel = new JLabel();
		timerLabel.setIcon(gamePanel.getGamePanelIcons()[2]);
		gameFrame.add(timerPanel,BorderLayout.EAST);
		timerPanel.setLayout(new BorderLayout());
		timerPanel.add(timerLabel, BorderLayout.NORTH);
		timerPanel.add(gameFrame.getTimerAsSecond(), BorderLayout.CENTER);
	}
	
}
