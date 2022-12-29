import UI.GameTime;
import domain.Player;
import domain.powerUps.ExtraLifePowerUp;
import domain.powerUps.ExtraTimePowerUp;
import org.junit.Assert;
import org.junit.Test;

public class PowerUpTriggerEffectTest {

    @Test
    public void testExtraLifePowerUpGlassBox() {
        // Set up the test scenario
        int initialLives = 3;
        Player player = new Player(initialLives);
        ExtraLifePowerUp powerup = new ExtraLifePowerUp(player);

        // Collect the powerup
        powerup.triggerEffect();

        // Check that the player's life count has increased by 1
        Assert.assertEquals(initialLives + 1, player.getLives());
    }

    @Test
    public void testExtraLifePowerUpBlackBox() {
        // Set up the test scenario
        Player player = new Player();
        ExtraLifePowerUp powerup = new ExtraLifePowerUp(player);

        int playerLife = player.getLives();

        // Collect the powerup
        powerup.triggerEffect();

        // Check that the player's life count has increased by 1
        Assert.assertEquals(playerLife + 1, player.getLives());
    }

    @Test
    public void testExtraTimePowerUpGlassBox() {
        // Set up the test scenario
        int initialTime = 25;
        GameTime.getInstance().setSeconds(initialTime);
        ExtraTimePowerUp powerup = new ExtraTimePowerUp();

        // Collect the powerup
        powerup.triggerEffect();

        // Check that the game time has increased by 5
        Assert.assertEquals(initialTime + 5, GameTime.getInstance().getSeconds());
    }

    @Test
    public void testExtraTimePowerUpBlackBox() {
        // Set up the test scenario
        ExtraTimePowerUp powerup = new ExtraTimePowerUp();

        int gameTime = GameTime.getInstance().getSeconds();
        // Collect the powerup
        powerup.triggerEffect();

        // Check that the game time has increased by 5
        Assert.assertEquals(gameTime + 5, GameTime.getInstance().getSeconds());
    }



}
