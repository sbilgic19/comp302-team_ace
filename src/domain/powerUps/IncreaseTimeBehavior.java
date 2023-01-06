package domain.powerUps;

import UI.GameTime;

public class IncreaseTimeBehavior implements PowerUpBehavior{

    /**
     * Increases the game time by five.
     *
     * REQUIRES:
     * - The player must be in a game level that has power up items.
     * - The player must be able to collect power up items by clicking.
     *
     * MODIFIES:
     * - The game time of the game.
     *
     * EFFECTS:
     * - The effect lasts for a whole game.
     */
    @Override
    public void performBehavior() {
        int newSecond = GameTime.getInstance().getSeconds() + 5;
        GameTime.getInstance().setSeconds(newSecond);

    }

}
