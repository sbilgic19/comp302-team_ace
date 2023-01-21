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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;


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
  private Timer alienTimer;

  private final ImageIcon openDoorIcon;

  RoomKeyHandler roomKeyHandler;
  PowerUpHandler powerUpHandler;
  TimeWastingAlienHandler timeWastingAlienHandler;
  ShooterAlienHandler shooterAlienHandler;
  BlindAlienHandler blindAlienHandler;


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
                gameFrame.updatePlayerLivesView(GameInfo.getInstance().getPlayer().getLives());


              }
            }
          }
        });
      }
    }
    int x, y;
    x = GameInfo.getInstance().getDoorLocation().getLocationX();
    y = GameInfo.getInstance().getDoorLocation().getLocationY();
    key = GameInfo.getInstance().getKey();
    gameMap[x][y].addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        System.out.println("isKeyTaken " + key.getIsTaken() );
        if (!GameState.getInstance().isPaused() && !GameState.getInstance().isGameOver() && GameInfo.getInstance().getKey().getIsTaken()
              && Location.distance(GameInfo.getInstance().getPlayer().getLocation(),GameInfo.getInstance().getDoorLocation()) < 2) {
          if (e.getSource() == gameMap[GameInfo.getInstance().getDoorLocation().getLocationX()][GameInfo.getInstance().getDoorLocation().getLocationY()]) {
            System.out.println((GameInfo.getInstance().getCurrentLevel() + 1) + ". level is starting...");
            GameInfo.getInstance().levelUp();
            GameInfo.getInstance().setActivePowerUp(null);
            powerUpCreatorTimer.cancel();
            powerUpRemoveTimer.cancel();
            alienTimer.cancel();
            gameFrame.getGameController().nextLevel();

          }
        }
      }
    });
    if (GameInfo.getInstance().getKey() == null) {
      System.out.println("New key generated");
      GameInfo.getInstance().addKey(roomKeyHandler.getRandomKey());
    }
    this.key = GameInfo.getInstance().getKey();

    PowerUpFactory.getInstance().setKey(key);
    System.out.println(key.getLocation().getLocationX() + " " + key.getLocation().getLocationY());

    gameMap[GameInfo.getInstance().getPlayer().getLocation().getLocationX()][GameInfo.getInstance().getPlayer().getLocation().getLocationY()].setIcon(playerFrontIcon);
    powerUpCreatorTimer = new Timer();
    powerUpCreatorTimer.schedule(new TimerTask() {
      @Override
      public void run() {
        if (!GameState.getInstance().isPaused() && !GameState.getInstance().isGameOver()) {
          powerUp = powerUpHandler.getRandomPowerUp();
          System.out.println("New powerUp created at location:" + powerUp.getLocation().getLocationX()
                + " " + powerUp.getLocation().getLocationY());
        }
      }
    }, 6 * 1000, 12000);

    powerUpRemoveTimer = new Timer();
    powerUpRemoveTimer.schedule(new TimerTask() {
      @Override
      public void run() {

        if (GameInfo.getInstance().getActivePowerUp() != null && powerUp != null && !GameState.getInstance().isPaused() && !GameState.getInstance().isGameOver()) {
          gameFrame.getGamePanel().getGameMap()[powerUp.getLocation().getLocationX()][powerUp.getLocation().getLocationY()].setIcon(null);
          gameFrame.getGameController().removePowerUp();
          GameInfo.getInstance().setActivePowerUp(null);
        }
      }
    }, 0, 12 * 1000);

    timeWastingAlienHandler = new TimeWastingAlienHandler(gameFrame.getGameController(), key);

  }

  public void alienProducer() {

    alienTimer = new Timer();
    TimerTask tt = new TimerTask() {
      @Override
      public void run() {
          int alienNumber;
          if (!key.getIsTaken()) {
            alienNumber = new Random().nextInt(3);
          } else {
            alienNumber = new Random().nextInt(2) + 1;
          }
          System.out.println(alienNumber);
          if (!GameState.getInstance().isPaused()){
            switch (alienNumber) {
              case 0:
                timeWastingAlienHandler.deactivate();
                timeWastingAlienRandomizer();
                shooterAlienHandler.deactivate();
                blindAlienHandler.deactivate();
                break;
              case 1:
                shooterAlienHandler.deactivate();
                shooterAlieanRandomizer();
                timeWastingAlienHandler.deactivate();
                blindAlienHandler.deactivate();
                break;
              case 2:
                blindAlienHandler.deactivate();
                blindAlieanRandomizer();
                timeWastingAlienHandler.deactivate();
                shooterAlienHandler.deactivate();
                break;

            }

          } /*else {


            Timer tryTimer = new Timer();
            TimerTask tt = new TimerTask() {

              @Override
              public void run() {
                if (!GameState.getInstance().isPaused()) {
                  switch (alienNumber) {
                    case 0:
                      timeWastingAlienHandler.deactivate();
                      timeWastingAlienRandomizer();
                      shooterAlienHandler.deactivate();
                      blindAlienHandler.deactivate();
                      break;
                    case 1:
                      shooterAlienHandler.deactivate();
                      shooterAlieanRandomizer();
                      timeWastingAlienHandler.deactivate();
                      blindAlienHandler.deactivate();
                      break;
                    case 2:
                      blindAlienHandler.deactivate();
                      blindAlieanRandomizer();
                      timeWastingAlienHandler.deactivate();
                      shooterAlienHandler.deactivate();
                      break;
                  }

                  tryTimer.cancel();


                }
              }
            };

          tryTimer.scheduleAtFixedRate(tt, 1000, 1000);


          Timer stopTimer = new Timer();
          stopTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

              if (!GameState.getInstance().isPaused()) {
                tryTimer.cancel();
                tt.cancel();
                stopTimer.cancel();
              }


            }
          }, 250, 250);
        }*/


        if (GameState.getInstance().isGameOver()) {
          alienTimer.cancel();
        }


      }
    };

    alienTimer.scheduleAtFixedRate(tt, 1000, 10000);

    Timer stopTimer = new Timer();
    stopTimer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {

        if (GameState.getInstance().isGameOver()) {
          tt.cancel();
          stopTimer.cancel();
        }


      }
    }, 250, 250);


  }

  public void timeWastingAlienRandomizer() {

    int minDelay = 7000; // 7 seconds
    int maxDelay = 10000; // 10 seconds
    int randomDelay = new Random().nextInt(maxDelay - minDelay + 1) + minDelay;
    timeWastingAlienHandler.getTimeWastingAlien();
    Timer wastingTimer = new Timer();

    wastingTimer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        if (!GameState.getInstance().isPaused()) {

          boolean b = timeWastingAlienHandler.changeLocationOfKey();
          if (b) {
            System.out.println("changed");
          }

        }

        if (GameState.getInstance().isGameOver()) {
          wastingTimer.cancel();              //System.out.println("heyyo");
        }


      }
    }, 1000, 1000);

    Timer deactivateTimer = new Timer();
    deactivateTimer.schedule(new TimerTask() {
      @Override
      public void run() {
        wastingTimer.cancel();
        if (!GameState.getInstance().isGameOver() && !GameState.getInstance().isPaused()) {
          timeWastingAlienHandler.deactivate();
        }


      }
    }, randomDelay);

  }


  public void shooterAlieanRandomizer() {

    int minDelay = 7000; // 7 seconds
    int maxDelay = 10000; // 10 seconds
    int randomDelay = new Random().nextInt(maxDelay - minDelay + 1) + minDelay;

    shooterAlienHandler.getShooterAlien();
    Timer shooterTimer = new Timer();
    shooterTimer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        if (!GameState.getInstance().isPaused()) {
          if (shooterAlienHandler.shoot()) {
            System.out.println("Shooted");
          }
        }

        if (GameState.getInstance().isGameOver()) {
          shooterTimer.cancel();
          //System.out.println("heyyo");
        }


      }
    }, 1000, 1000);

    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        shooterTimer.cancel();
        if (GameState.getInstance().isPaused() && !GameState.getInstance().isGameOver()) {
          Timer tryTimer = new Timer();
          TimerTask tt = new TimerTask() {

            @Override
            public void run() {
              if (!GameState.getInstance().isPaused()) {
                shooterAlienHandler.deactivate();

                tryTimer.cancel();

              }

            }

          };

          tryTimer.scheduleAtFixedRate(tt, 1000, 1000);


          Timer stopTimer = new Timer();
          stopTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

              if (!GameState.getInstance().isPaused()) {
                shooterAlienHandler.deactivate();
                tt.cancel();
                stopTimer.cancel();
              }


            }
          }, 250, 250);
        }


      }
    }, randomDelay);

  }

  public void blindAlieanRandomizer() {

    int minDelay = 7000; // 5 seconds
    int maxDelay = 10000; // 10 seconds
    int randomDelay = new Random().nextInt(maxDelay - minDelay + 1) + minDelay;

    blindAlienHandler.getBlindAlien();
    Timer blindTimer = new Timer();
    Timer blindshootTimer = new Timer();
    blindTimer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        if (!GameState.getInstance().isPaused()) {
          blindAlienHandler.move(plasticBottleLocation);
        }


        if (GameState.getInstance().isGameOver()) {
          blindTimer.cancel();
        }
      }
    }, 500, 500);


    blindTimer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        if (!GameState.getInstance().isPaused()) {
          blindAlienHandler.shoot();
        }

        if (GameState.getInstance().isGameOver()) {
          blindshootTimer.cancel();
        }


      }
    }, 450, 450);

    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        blindTimer.cancel();
        blindshootTimer.cancel();
        if (GameState.getInstance().isPaused() && !GameState.getInstance().isGameOver()) {
          Timer tryTimer = new Timer();
          TimerTask tt = new TimerTask() {

            @Override
            public void run() {
              if (!GameState.getInstance().isPaused()) {
                blindAlienHandler.deactivate();

                tryTimer.cancel();

              }

            }

          };

          tryTimer.scheduleAtFixedRate(tt, 1000, 1000);


          Timer stopTimer = new Timer();
          stopTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

              if (!GameState.getInstance().isPaused()) {
                blindAlienHandler.deactivate();
                tt.cancel();
                stopTimer.cancel();
              }


            }
          }, 250, 250);
        }
      }
    }, randomDelay);

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


  public ImageIcon getTimeWastingAlienIcon() {
    return timeWastingAlienIcon;
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


}