package UI;

import javax.swing.*;

import Controllers.TimeWastingAlienHandler;
import Controllers.BlindAlienHandler;
import Controllers.PowerUpHandler;
import Controllers.RoomKeyHandler;
import Controllers.ShooterAlienHandler;
import dataStructures.Location;
import domain.GameInfo;
import domain.powerUps.PowerUp;
import domain.Key;
import domain.powerUps.PowerUpFactory;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.Random;


public class GamePanel extends JPanel {

  private JLabel[][] gameMap;
  private final ImageIcon playerFrontIcon;
  private final ImageIcon playerBackIcon;
  private final ImageIcon playerLeftIcon;
  private final ImageIcon playerRightIcon;
  private final ImageIcon playerFrontWithVestIcon;
  private final ImageIcon playerBackWithVestIcon;
  private final ImageIcon playerLeftWithVestIcon;
  private static ImageIcon playerRightWithVestIcon;
  private static ImageIcon keyIcon;
  private static ImageIcon bagIcon;
  private static ImageIcon timerIcon;
  private static ImageIcon timeWastingAlienIcon;
  private static ImageIcon blindIcon;
  private static ImageIcon shooterIcon;

  private final ImageIcon extraLifeIcon;
  private final ImageIcon extraTimeIcon;
  private final ImageIcon protectionVestIcon;
  private final ImageIcon hintIcon;
  private final ImageIcon plasticBottleIcon;
  private final ImageIcon plasticBottleIconEast;
  private final ImageIcon plasticBottleIconWest;
  private final ImageIcon plasticBottleIconSouth;

  private Location plasticBottleLocation = null;
  private final int numRow;
  private final int numCol;
  private final GameFrame gameFrame;
  private Key key;
  private PowerUp powerUp;
  private boolean isPowerUpActive;
  private Timer powerUpCreatorTimer;
  private Timer powerUpRemoveTimer;
  private javax.swing.Timer alienTimer;
  private javax.swing.Timer wastingTimer;
  private javax.swing.Timer shooterTimer;
  private javax.swing.Timer blindTimer;

  private final ImageIcon openDoorIcon;

  RoomKeyHandler roomKeyHandler;
  PowerUpHandler powerUpHandler;
  TimeWastingAlienHandler timeWastingAlienHandler;
  ShooterAlienHandler shooterAlienHandler;
  BlindAlienHandler blindAlienHandler;
  private int alienTimerSeconds= 0;
  private int wastingTimerSeconds= 0;
  private int blindTimerSeconds= 0;
  private int shooterTimerSeconds= 0;
  private int createSeconds = 0;
  private int removeSeconds = 0;

  public GamePanel(GameFrame gameFrame) {

    this.gameFrame = gameFrame;
    numRow = this.gameFrame.getNumRow();
    numCol = this.gameFrame.getNumCol();

    IconFactory iconFactory = IconFactory.getInstance();
    playerFrontIcon = iconFactory.generateIcon("../assets/playerFrontIcon.png", 50, 50);
    playerBackIcon = iconFactory.generateIcon("../assets/playerBackIcon.png", 50, 50);
    playerLeftIcon = iconFactory.generateIcon("../assets/playerLeftIcon.png", 50, 50);
    playerRightIcon = iconFactory.generateIcon("../assets/playerRightIcon.png", 50, 50);

    playerFrontWithVestIcon = iconFactory.generateIcon("../assets/armoredFront.jpg", 50, 50);
    playerBackWithVestIcon = iconFactory.generateIcon("../assets/armoredBack.jpg", 50, 50);
    playerLeftWithVestIcon = iconFactory.generateIcon("../assets/armoredLeft.jpg", 50, 50);
    playerRightWithVestIcon = iconFactory.generateIcon("../assets/armoredRight.jpg", 50, 50);


    blindIcon = iconFactory.generateIcon("../assets/blind.jpg", 50, 50);
    shooterIcon = iconFactory.generateIcon("../assets/shooter.jpg", 50, 50);

    keyIcon = iconFactory.generateIcon("../assets/keyIcon.png", 50, 50);
    bagIcon = iconFactory.generateIcon("../assets/bagIcon.png", 50, 50);
    timerIcon = iconFactory.generateIcon("../assets/timerIcon.png", 50, 50);

    extraLifeIcon = iconFactory.generateIcon("../assets/extraLifeIcon.png", 50, 50);
    extraTimeIcon = iconFactory.generateIcon("../assets/extraTimeIcon.png", 50, 50);
    protectionVestIcon = iconFactory.generateIcon("../assets/protectionVest.png", 50, 50);
    hintIcon = iconFactory.generateIcon("../assets/hintIcon.png", 50, 50);
    plasticBottleIcon = iconFactory.generateIcon("../assets/plasticbottleNorth.png", 50, 50);

    plasticBottleIconEast = iconFactory.generateIcon("../assets/plasticbottleEast.png", 50, 50);
    plasticBottleIconWest = iconFactory.generateIcon("../assets/plasticbottleWest.png", 50, 50);
    plasticBottleIconSouth = iconFactory.generateIcon("../assets/plasticbottleSouth.png", 50, 50);

    openDoorIcon = iconFactory.generateIcon("../assets/doorIcon2.png", 50, 50);

    timeWastingAlienIcon = iconFactory.generateIcon("../assets/timeWastingAlienIcon.png", 50, 50);


    this.setLayout(new GridLayout(numRow, numCol, 0, 0));
    roomKeyHandler = this.gameFrame.getRoomKeyHandler();

    shooterAlienHandler = this.gameFrame.getShooterAlienHandler();
    blindAlienHandler = this.gameFrame.getBlindAlienHandler();
    powerUpHandler = this.gameFrame.getPowerUpHandler();


  }

  public void setGameMap(JLabel[][] buildModeMap) {
    gameMap = new JLabel[numRow][numCol];


    for (int ii = 0; ii < numRow; ii++) {
      for (int jj = 0; jj < numCol; jj++) {
        gameMap[ii][jj] = buildModeMap[ii][jj];
        gameMap[ii][jj].setBorder(null);
        add(gameMap[ii][jj]);

        gameMap[ii][jj].addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            key = GameInfo.getInstance().getKey();
            if (!GameState.getInstance().isPaused() && key != null && !key.getIsTaken() && !GameState.getInstance().isGameOver()) {
              int locX_key = key.getLocation().getLocationX();
              int locY_key = key.getLocation().getLocationY();
              if (e.getSource() == gameMap[locX_key][locY_key] && key != null) {
                System.out.println("Key taken");
                roomKeyHandler.takeKey(key);
                gameFrame.updateKeyView(true);
              }
            }
          }
        });

        gameMap[ii][jj].addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            powerUp = GameInfo.getInstance().getActivePowerUp();
            if (!GameState.getInstance().isPaused() && powerUp != null && !GameState.getInstance().isGameOver()) {
              int locX = GameInfo.getInstance().getActivePowerUp().getLocation().getLocationX();
              int locY = GameInfo.getInstance().getActivePowerUp().getLocation().getLocationY();
              System.out.println("Location of the active powerUp" + locX + " " + locY);

              if (e.getSource() == gameMap[locX][locY]) {
                gameFrame.getGamePanel().getGameMap()[locX][locY].setIcon(null);
                powerUpHandler.usePowerUp(powerUp);
                GameInfo.getInstance().setActivePowerUp(null);
                gameFrame.getGameController().removePowerUp();
                gameFrame.updatePlayerLivesView(GameInfo.getInstance().getPlayer().getLives());}
              else if (e.getSource() ==
                        gameMap[GameInfo.getInstance().getDoorLocation().getLocationX()][GameInfo.getInstance().getDoorLocation().getLocationY()]){
                  if(roomKeyHandler.nextLevel()){
                    System.out.println((GameInfo.getInstance().getCurrentLevel() + 1) + ". level is starting...");
                    GameInfo.getInstance().levelUp();
                    GameInfo.getInstance().setActivePowerUp(null);
                    gameFrame.getGameController().nextLevel();
                  }
                }


              }

          }
        });
      }
    }

    if (GameInfo.getInstance().getKey() == null) {
      System.out.println("New key generated");
      GameInfo.getInstance().addKey(roomKeyHandler.getRandomKey());
    }
    this.key = GameInfo.getInstance().getKey();

    PowerUpFactory.getInstance().setKey(key);
    System.out.println(key.getLocation().getLocationX() + " " + key.getLocation().getLocationY());

    gameMap[GameInfo.getInstance().getPlayer().getLocation().getLocationX()][GameInfo.getInstance().getPlayer().getLocation().getLocationY()].setIcon(playerFrontIcon);
    timeWastingAlienHandler = new TimeWastingAlienHandler(gameFrame.getGameController(), key);

  }

  public void alienProducer() {
    alienTimer = new Timer(1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(!GameState.getInstance().isPaused() && !GameState.getInstance().isGameOver()) {
          alienTimerSeconds++;
          if(alienTimerSeconds % 10 == 1){
            int alienNumber;
            if (!key.getIsTaken()) {
              alienNumber = new Random().nextInt(3);
            } else {
              alienNumber = new Random().nextInt(2) + 1;
            }
            System.out.println(alienNumber);
            switch (alienNumber) {
              case 0:
                timeWastingAlienHandler.deactivate();
                shooterAlienHandler.deactivate();
                blindAlienHandler.deactivate();
                timeWastingAlienRandomizer();
                break;
              case 1:
                shooterAlienHandler.deactivate();
                timeWastingAlienHandler.deactivate();
                blindAlienHandler.deactivate();
                shooterAlieanRandomizer();
                break;
              case 2:
                blindAlienHandler.deactivate();
                timeWastingAlienHandler.deactivate();
                shooterAlienHandler.deactivate();
                blindAlieanRandomizer();
                break;
            }
          }
        }
      }
    });
    alienTimer.start();
  }

  public void timeWastingAlienRandomizer() {

    timeWastingAlienHandler.getTimeWastingAlien();
    wastingTimer = new Timer(800, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(!GameState.getInstance().isGameOver() && !GameState.getInstance().isPaused()){
          wastingTimerSeconds++;
          boolean b = timeWastingAlienHandler.changeLocationOfKey();
          if (b) {
            System.out.println("changed");
          }
          if(wastingTimerSeconds == 9){
            timeWastingAlienHandler.deactivate();
            wastingTimerSeconds=0;
            wastingTimer.stop();
          }
        }
      }
    });
    wastingTimer.start();
  }


  public void shooterAlieanRandomizer() {

    shooterAlienHandler.getShooterAlien();
    shooterTimer = new Timer(800, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(!GameState.getInstance().isPaused()&& !GameState.getInstance().isGameOver()){
          shooterTimerSeconds++;
          if (shooterAlienHandler.shoot()) {
            System.out.println("Shooted");
          }
          if(shooterTimerSeconds == 9){
            shooterAlienHandler.deactivate();
            shooterTimerSeconds = 0;
            shooterTimer.stop();
          }
        }
      }
    });
    shooterTimer.start();
  }

  public void blindAlieanRandomizer() {

    blindAlienHandler.getBlindAlien();
    blindTimer = new Timer(800, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if(!GameState.getInstance().isGameOver() && !GameState.getInstance().isPaused()){
          blindTimerSeconds++;
          blindAlienHandler.move(plasticBottleLocation);
          blindAlienHandler.shoot();
          if(blindTimerSeconds == 9){
            blindAlienHandler.deactivate();
            blindTimerSeconds = 0;
            blindTimer.stop();
          }
        }
      }
    });
    blindTimer.start();
  }


  public void updatePlayerView(int xPlayerPosition, int yPlayerPosition,
                               int newXPlayerPosition, int newYPlayerPosition, int playerLogoPosition) {
    gameMap[xPlayerPosition][yPlayerPosition].setIcon(null);
    ImageIcon[] playerIcons = {playerFrontIcon, playerBackIcon, playerLeftIcon, playerRightIcon};
    ImageIcon[] protectedPlayerIcons = {playerFrontWithVestIcon, playerBackWithVestIcon, playerLeftWithVestIcon, playerRightWithVestIcon};
    if (gameFrame.getGameController().getPlayer().isProtected()) {
      gameMap[newXPlayerPosition][newYPlayerPosition].setIcon(protectedPlayerIcons[playerLogoPosition]);
    } else {
      gameMap[newXPlayerPosition][newYPlayerPosition].setIcon(playerIcons[playerLogoPosition]);
    }
  }


  public void updateBlindAlienView(int xPosition, int yPosition, int newXPosition, int newYPosition) {
    gameMap[xPosition][yPosition].setIcon(null);
    gameMap[newXPosition][newYPosition].setIcon(blindIcon);
  }


  public boolean updateBottleView(int oldXLoc, int oldYLoc, int newXPlayerPosition, int newYPlayerPosition, int bottleIconPosition) {

    if (oldXLoc != gameFrame.getGameController().getPlayer().getLocation().getLocationX() || oldYLoc != gameFrame.getGameController().getPlayer().getLocation().getLocationY()) {
      gameMap[oldXLoc][oldYLoc].setIcon(null);
    }
    ImageIcon[] plasticBottleIcons = {plasticBottleIcon, plasticBottleIconEast, plasticBottleIconSouth, plasticBottleIconWest};
    if (gameMap[newXPlayerPosition][newYPlayerPosition].getIcon() == null && newXPlayerPosition < numRow - 1 && newYPlayerPosition < numCol - 1
          && newXPlayerPosition > 0 && newYPlayerPosition > 0) {
      gameMap[newXPlayerPosition][newYPlayerPosition].setIcon(plasticBottleIcons[bottleIconPosition % 4]);
      plasticBottleLocation = new Location(newXPlayerPosition, newYPlayerPosition);
      return true;
    } else
      plasticBottleLocation = null;
      return false;
  }


  public void placePowerUp(Location location, String powerUpType) {
    switch (powerUpType) {
      case "ExtraLife":
        gameMap[location.getLocationX()][location.getLocationY()].setIcon(extraLifeIcon);
        break;
      case "ExtraTime":
        gameMap[location.getLocationX()][location.getLocationY()].setIcon(extraTimeIcon);
        break;
      case "ProtectionVest":
        gameMap[location.getLocationX()][location.getLocationY()].setIcon(protectionVestIcon);
        break;
      case "Hint":
        gameMap[location.getLocationX()][location.getLocationY()].setIcon(hintIcon);
        break;
      case "PlasticBottle":
        gameMap[location.getLocationX()][location.getLocationY()].setIcon(plasticBottleIcon);
        break;
    }

  }

  public void placeAlien(Location location, String alienType) {
    switch (alienType) {
      case "TimeWasting":
        gameMap[location.getLocationX()][location.getLocationY()].setIcon(timeWastingAlienIcon);
        break;
      case "Shooter":
        gameMap[location.getLocationX()][location.getLocationY()].setIcon(shooterIcon);
        break;
      case "Blind":
        gameMap[location.getLocationX()][location.getLocationY()].setIcon(blindIcon);
        break;
    }
  }

  public ImageIcon[] getGamePanelIcons() {
    ImageIcon[] icons = {keyIcon, bagIcon, timerIcon, protectionVestIcon};
    return icons;
  }

  public ImageIcon getOpenDoorIcon() {
    return openDoorIcon;
  }

  public JLabel[][] getGameMap() {
    return this.gameMap;
  }

  public void setNullIcon(Location location) {
    gameMap[location.getLocationX()][location.getLocationY()].setIcon(null);
  }

  /**
   * remove borders in the gameMap.
   */
  public void removeBorders() {
    for (int row = 0; row < gameMap.length; row++) {
      for (int col = 0; col < gameMap[0].length; col++) {
        this.gameMap[row][col].setBorder(null);
      }
    }
  }

  /**
   * Sets borders around the key
   *
   * @param keyRow row of the key
   * @param keyCol column of the key
   */
  public void addBordersHint(int keyRow, int keyCol) {
    Random rand = new Random();
    int offsetRow = rand.nextInt(3) - 1;
    int offsetCol = rand.nextInt(3) - 1;

    // Set the border of the labels within the rectangle to indicate the hint
    for (int row = keyRow + offsetRow; row < keyRow + offsetRow + 4; row++) {
      for (int col = keyCol + offsetCol; col < keyCol + offsetCol + 4; col++) {
        // Make sure the label is within the bounds of the game map
        if (row >= 0 && row < this.gameMap.length && col >= 0 && col < this.gameMap[0].length) {
          System.out.println("Border set");
          gameMap[row][col].setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        }
      }
    }
  }

  public Key getKey() {
    return this.key;
  }

  public void setPowerUp(PowerUp powerUp) {
    this.powerUp = powerUp;
  }

  public boolean getIsPowerUpActive() {
    return isPowerUpActive;
  }
  public void powerUpCreateTimer() {
    powerUpCreatorTimer =new Timer(1000, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (!GameState.getInstance().isPaused() && !GameState.getInstance().isGameOver()) {
          createSeconds++;
          removeSeconds++;
          if (createSeconds % 12 == 6) {
            powerUp = powerUpHandler.getRandomPowerUp();
            System.out.println("New powerUp created at location:" + powerUp.getLocation().getLocationX()
                    + " " + powerUp.getLocation().getLocationY());

          }
          if(removeSeconds % 12 == 0){
            if (GameInfo.getInstance().getActivePowerUp() != null && powerUp != null) {
              gameFrame.getGamePanel().getGameMap()[powerUp.getLocation().getLocationX()][powerUp.getLocation().getLocationY()].setIcon(null);
              gameFrame.getGameController().removePowerUp();
              GameInfo.getInstance().setActivePowerUp(null);
            }
          }
        }
      }
    });
    powerUpCreatorTimer.start();
  }


}