package domain.powerUps;

import UI.GamePanel;
import domain.Key;
import domain.Player;

import java.awt.*;
import java.util.Random;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowHintBehavior implements PowerUpBehavior{

  private int seconds = 0;
  Timer timer;
  Player player;

  int keyRow;
  int keyCol;

  JLabel[][] gameMap;

  Random rand;

  public ShowHintBehavior(Player player, Key key){
    this.player = player;
    this.rand = new Random();
    keyRow = key.getLocation().getLocationX();
    keyCol = key.getLocation().getLocationY();
  }

  @Override
  public void performBehavior() {
    player.removePowerUp("Hint");
  }


}
