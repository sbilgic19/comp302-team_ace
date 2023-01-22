package domain.powerUps;
import domain.Player;

import java.io.Serializable;

public class IncreaseLifeBehavior implements PowerUpBehavior, Serializable {

    private final Player player;

    public IncreaseLifeBehavior(Player player) {
        this.player = player;
    }


    /**
     * Increases the player lives by one.
     *
     * REQUIRES:
     * - The player must be in a game level that has power up items.
     * - The player must be able to collect power up items by clicking.
     *
     * MODIFIES:
     * - The lives of the player
     *
     * EFFECTS:
     * - The effect lasts for a whole game.
     */
    @Override
    public void performBehavior() {
        player.increaseLives();
    }




}
