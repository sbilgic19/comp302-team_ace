package domain.powerUps;

import domain.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WearVestBehavior implements PowerUpBehavior{
    private Player player;
    private int seconds = 0;
    Timer timer;
    public WearVestBehavior(Player player){
        this.player = player;
    }

    @Override
    public void performBehavior() {
        player.setIsProtected(true);
        timer = createTimer();
        timer.start();
        player.removePowerUp("ProtectionVest");

    }
    public Timer createTimer(){
        return new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                if(seconds == 20){
                    player.setIsProtected(false);
                    timer.stop();
                }
            }
        });
    }
}
