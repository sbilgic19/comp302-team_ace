package domain.powerUps;

import UI.GameState;
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

    /**
     * Player wears protection vest that protects it for a one shoot.
     *
     * REQUIRES:
     * - The player must be in a game level that has power up items.
     * - The player must be able to collect power up items by clicking.
     * - The player must be click the P button.
     *
     * MODIFIES:
     * - The player isProtected be true.
     * - It creates timer to keep time of the duration of the protection vest
     * - It removes the protection vest power up from the player's bag.
     *
     * EFFECTS:
     * - The effect lasts for 20 seconds.
     */
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
                if (!GameState.getInstance().isPaused())
                {
                    seconds++;
                    if(seconds == 20){
                        player.setIsProtected(false);
                        timer.stop();
                    }
                }
            }
        });
    }
}
