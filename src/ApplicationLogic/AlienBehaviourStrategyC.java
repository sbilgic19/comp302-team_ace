package ApplicationLogic;

public class AlienBehaviourStrategyC implements AlienBehaviourStrategy {

	private int totalTime = 0;;
	@Override
	public boolean changeLocationOfTheKey() {
		totalTime++;
		if (totalTime == 2) {
			return false;
		}
		return true;
	}
}