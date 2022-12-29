package domain.powerUps;
import domain.Player;

public class IncreaseLifeBehavior implements PowerUpBehavior{

    private Player player;

    public IncreaseLifeBehavior(Player player) {
        this.player = player;
    }

    @Override
    public void performBehavior() {
        player.increaseLives();
    }




}
