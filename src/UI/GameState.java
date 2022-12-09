package UI;

public class GameState {
    private boolean isPaused = false;
    private static GameState instance;
    public GameState(){}

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public static GameState getInstance(){
        if(instance == null){
            instance = new GameState();
        }

        return instance;
    }
}
