package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import Controllers.BlindAlienHandler;
import Controllers.BuildModeButtonHandler;
import Controllers.KeyHandler;
import Controllers.PlayerHandler;
import Controllers.PowerUpHandler;
import Controllers.RoomKeyHandler;
import Controllers.ShooterAlienHandler;
import Database.Client;
import dataStructures.Location;
import domain.GameInfo;
import domain.Player;
import domain.RoomObject;
import domain.powerUps.PowerUp;
import domain.powerUps.PowerUpFactory;
import domain.powerUps.ProtectionVestPowerUp;

public class GameController {

	private static final int frameWidth = 1500;
	private static final int frameHeight = 750;

	private final GameFrame gameFrame;
	//private GamePanel gamePanel;

	private final Client client;
	private final String serviceUsed;

	private Player player;
	private RoomKeyHandler roomKeyHandler;
	private PlayerHandler playerHandler;
	private KeyHandler keyHandler;
	private ShooterAlienHandler shooterAlienHandler;
	private BlindAlienHandler blindAlienHandler;
	private PowerUpHandler powerUpHandler;
	private PowerUp activePowerUp;
	private final String[] levels = {"Student Center", "CASE Building", "SOS Building", "SCI Building", "ENG Building", "SNA Building"};

	public GameController() {

		serviceUsed = "MongoDB";
		client = new Client(serviceUsed);
		gameFrame = new GameFrame(this);
		//gamePanel = new GamePanel(this);
		if (GameInfo.getInstance().getPlayer() == null) {
			player = new Player();
		} else {
			player = GameInfo.getInstance().getPlayer();
		}
		player.setLocation(GameInfo.getInstance().getPlayerLocation());
		this.roomKeyHandler = new RoomKeyHandler(this, player);


	}
	public void createAndShowGUI(){
		//Authorization.addUserToRecord(new User("nsavran", "123456"));


		RoomKeyHandler roomKeyHandler = new RoomKeyHandler(this, this.player);
		PlayerHandler playerHandler = new PlayerHandler(this.player, this);

		KeyHandler keyHandler = new KeyHandler(playerHandler);
		ShooterAlienHandler shooterAlienHandler = new ShooterAlienHandler(this.player, this);
		BlindAlienHandler blindAlienHandler = new BlindAlienHandler(this.player,this);
		PowerUpHandler powerUpHandler = new PowerUpHandler(this, this.player);



		GameInfo.getInstance().setPlayer(player);
		PowerUpFactory.getInstance().setPlayer(player);

		this.gameFrame.setShooterAlienHandler(shooterAlienHandler);
		this.gameFrame.setBlindAlienHandler(blindAlienHandler);
		this.gameFrame.setKeyHandler(keyHandler);
		this.gameFrame.setRoomKeyHandler(roomKeyHandler);
		this.gameFrame.setPowerUpHandler(powerUpHandler);
		this.gameFrame.setPlayerHandler(playerHandler);
		this.gameFrame.showMainView();
		setPlayer(player);

		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setSize(frameWidth, frameHeight);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setResizable(false);
		gameFrame.setVisible(true);
	}

	public GameFrame getGameFrame() {
		return this.gameFrame;
	}
	public Player getPlayer() {
		return this.player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public void switchBuildView() {


		this.gameFrame.getLoadOrNewScreen().setVisible(false);
		this.gameFrame.getLoadOrNewScreen().setEnabled(false);
		this.gameFrame.remove(gameFrame.getLoadOrNewScreen());
		this.gameFrame.requestFocus();


		this.gameFrame.setLayout(new BorderLayout());
		//buildPanel = new BuildPanel(this);
		this.gameFrame.add(gameFrame.getBuildPanel(), BorderLayout.CENTER);
		this.gameFrame.setBuildMode(new BuildMode(this.gameFrame, gameFrame.getBuildPanel()));
		this.gameFrame.setObjectList(gameFrame.getBuildMode().getObjectList());
		gameFrame.setButtonPanel(new JPanel());
		gameFrame.add(gameFrame.getButtonPanel(),BorderLayout.SOUTH);


		gameFrame.setBuildModeSubmitButton(new JButton("Submit"));
		gameFrame.getBuildModeSubmitButton().setFocusable(false);
		gameFrame.getBuildModeSubmitButton().setPreferredSize(new Dimension(200,30));
		this.updateLevelView();
		gameFrame.getButtonPanel().add(gameFrame.getLevelLabel());

		gameFrame.getButtonPanel().add(gameFrame.getBuildModeSubmitButton());
		BuildModeButtonHandler buildModeButtonHandler = new BuildModeButtonHandler(this,gameFrame.getBuildMode(), gameFrame.getBuildPanel());
		gameFrame.getBuildModeSubmitButton().addActionListener(buildModeButtonHandler);
	}
	public void switchGameView(JLabel[][] buildModeMap) {
		if(gameFrame.getBuildModeSubmitButton() != null){
			gameFrame.remove(gameFrame.getLevelLabel());
			gameFrame.remove(gameFrame.getBuildModeSubmitButton());
			gameFrame.remove(gameFrame.getBuildPanel());
			gameFrame.getButtonPanel().remove(gameFrame.getBuildModeSubmitButton());
			gameFrame.getButtonPanel().remove(gameFrame.getLevelLabel());
		}
		gameFrame.setPauseButton(new JButton("II"));
		gameFrame.getPauseButton().setFocusable(false);
		gameFrame.getPauseButton().setSize(50,50);

		gameFrame.setResumeButton(new JButton(">"));
		gameFrame.getResumeButton().setFocusable(false);
		gameFrame.getResumeButton().setVisible(false);
		gameFrame.getResumeButton().setSize(50,50);


		player = GameInfo.getInstance().getPlayer();
		roomKeyHandler = new RoomKeyHandler(this, player);
		playerHandler = new PlayerHandler(player, this);

		keyHandler = new KeyHandler(playerHandler);
		shooterAlienHandler = new ShooterAlienHandler(player, this);
		blindAlienHandler = new BlindAlienHandler(player, this);
		powerUpHandler = new PowerUpHandler(this, player);

		int time = (GameInfo.getInstance().getCurrentObjects().size() - 1)* 5;
		time = 100;
		GameTime.getInstance().setSeconds(time);
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
				gameFrame.getPauseDialog().getReturnToGameButton().addActionListener(new ActionListener () {
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

				gameFrame.getPauseDialog().getSaveGameButton().addActionListener(new ActionListener () {
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

		gameFrame.setGamePanel(new GamePanel(gameFrame));
		gameFrame.add(gameFrame.getGamePanel(),BorderLayout.CENTER);

		gameFrame.getGamePanel().setBorder(BorderFactory.createLineBorder(Color.RED, 2));
		gameFrame.getGamePanel().setGameMap(buildModeMap);
		gameFrame.getGamePanel().requestFocus();
		gameFrame.getGamePanel().addKeyListener(this.keyHandler);
		gameFrame.getGamePanel().alienProducer();

		JPanel livesPanel = new JPanel();
		gameFrame.add(livesPanel, BorderLayout.NORTH);
		livesPanel.add(gameFrame.getLives(), BorderLayout.CENTER);
		gameFrame.getLives().setText("Remaining lives: " + player.getLives());
		gameFrame.getLives().setVisible(true);

		JPanel keyPanel = new JPanel();
		gameFrame.add(keyPanel, BorderLayout.WEST);
		keyPanel.setLayout(new BorderLayout());
		JLabel bagLabel = new JLabel();
		bagLabel.setIcon(gameFrame.getGamePanel().getGamePanelIcons()[1]);
		keyPanel.add(bagLabel, BorderLayout.NORTH);
		keyPanel.add(gameFrame.getKey(), BorderLayout.CENTER);
		keyPanel.add(gameFrame.getPowerUp(), BorderLayout.SOUTH);
		gameFrame.setButtonPanel(new JPanel());
		gameFrame.getButtonPanel().add(gameFrame.getPauseButton());
		gameFrame.getPauseButton().setPreferredSize(new Dimension(200,30));
		gameFrame.add(gameFrame.getButtonPanel(), BorderLayout.SOUTH);

		gameFrame.setTimerAsSecond(GameTime.getInstance().getTimerAsSecond());

		JPanel timerPanel = new JPanel();
		JLabel timerLabel = new JLabel();
		timerLabel.setIcon(gameFrame.getGamePanel().getGamePanelIcons()[2]);
		gameFrame.add(timerPanel,BorderLayout.EAST);
		timerPanel.setLayout(new BorderLayout());
		timerPanel.add(timerLabel, BorderLayout.NORTH);
		timerPanel.add(gameFrame.getTimerAsSecond(), BorderLayout.CENTER);
	}

	public void updatePlayerLivesView(int life) {
        gameFrame.getLives().setText("Remaining lives: " + life);
        if(life == 0) {
        	GameState.getInstance().setGameOver(true);
        	GameState.getInstance().setPaused(true);
        	System.out.println("game over!");
        }
    }

	public void updateKeyView(Boolean is_taken) {
		if(is_taken) {
			gameFrame.getKey().setIcon(gameFrame.getGamePanel().getGamePanelIcons()[0]);
			Location doorLocation = gameFrame.getBuildMode().getDoorLocation();
			JLabel[][] gameMap = this.getGameFrame().getGamePanel().getGameMap();
			gameMap[doorLocation.getLocationX()][doorLocation.getLocationY()]
					.setIcon(gameFrame.getGamePanel().getOpenDoorIcon());
		}
	}

	public void updateBagView(PowerUp powerUp) {
		if (powerUp instanceof ProtectionVestPowerUp) {
			gameFrame.getPowerUp().setIcon(gameFrame.getGamePanel().getGamePanelIcons()[3]);
		}
			else{
				gameFrame.getPowerUp().setIcon(null);

		}
	}

	public void increaseSecond(int second) {
		int newSecond = GameTime.getInstance().getSeconds() + second;
		GameTime.getInstance().setSeconds(newSecond);
	}

	public void setPowerUp(PowerUp powerUp){
		this.activePowerUp = powerUp;
		gameFrame.getGamePanel().setPowerUp(powerUp);
	}
	public void removePowerUp(){
		this.activePowerUp = null;
		gameFrame.getGamePanel().setPowerUp(null);
	}
	public PowerUp getActivePowerUp(){
		return this.activePowerUp;
	}

	public Client getClient(){
		return this.client;
	}
	public void updateLevelView(){
		gameFrame.setCurrentLevelText(levels[GameInfo.getInstance().getCurrentLevel() - 1]);
	}
	public JLabel[][] arrayToMatrix(ArrayList<RoomObject> objectList){
		int numRow = gameFrame.getNumRow();
		int numCol = gameFrame.getNumCol();
		JLabel[][] buildModeMap = new JLabel[numRow][numCol];
		for (int ii = 0; ii < numRow; ii++) {
			for (int jj = 0; jj < numCol; jj++) {
				buildModeMap[ii][jj] = new JLabel();

			}
		}
		for (RoomObject r:objectList){
			int x, y;
			x = r.getLocation().getLocationX();
			y = r.getLocation().getLocationY();
			buildModeMap[x][y].setIcon(gameFrame.getIcons()[r.getTypeID()]);
		}


		return buildModeMap;
	}

	public void nextLevel(){
		System.out.println("CurrentLevel: " + GameInfo.getInstance().getCurrentLevel());
		if (GameInfo.getInstance().getCurrentLevel() > 6) {
			JOptionPane.showMessageDialog(null, "You Won",
						"Congrats!", JOptionPane.ERROR_MESSAGE);
		} else {
			JLabel[][] newMap = gameFrame.getGameController().arrayToMatrix(GameInfo.getInstance().getCurrentObjects());
			GameInfo.getInstance().getPlayer().setLocation(new Location(0,5));
			GameInfo.getInstance().getPlayer().setKeyTaken(false);
			GameInfo.getInstance().setActivePowerUp(null);
			this.switchGameView(newMap);
			System.out.println(GameInfo.getInstance().getPlayer().isContains("PlasticBottle"));
		}
	}
}

