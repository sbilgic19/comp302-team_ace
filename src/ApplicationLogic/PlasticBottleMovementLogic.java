package ApplicationLogic;

import UI.GamePanel;
import domain.powerUps.PlasticBottlePowerUp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlasticBottleMovementLogic {

    private PlasticBottlePowerUp powerUp;
    Timer timer;
    boolean isMoving = true;
    public PlasticBottleMovementLogic(PlasticBottlePowerUp powerUp){
        this.powerUp = powerUp;
    }
    public void updateBottlePosition(){
        timer = createTimer();
        timer.start();
    }

    private Timer createTimer(){
        return new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isMoving) {
                    int oldXLoc = powerUp.getLocation().getLocationX();
                    int oldYLoc = powerUp.getLocation().getLocationY();
                    powerUp.triggerEffect();
                    powerUp.setBottleIconPosition(powerUp.getBottleIconPosition()+1);
                    isMoving = GamePanel.updateBottleView(oldXLoc,oldYLoc,powerUp.getLocation().getLocationX(), powerUp.getLocation().getLocationY(), powerUp.getBottleIconPosition());
                }else
                    timer.stop();
            }

        });
    }

}
