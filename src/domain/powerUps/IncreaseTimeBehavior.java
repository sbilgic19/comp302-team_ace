package domain.powerUps;

import UI.GameTime;

public class IncreaseTimeBehavior implements PowerUpBehavior{

    @Override
    public void performBehavior() {
        int newSecond = GameTime.getInstance().getSeconds() + 5;
        GameTime.getInstance().setSeconds(newSecond);

    }

}
