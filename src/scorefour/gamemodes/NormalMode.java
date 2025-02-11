package scorefour.gamemodes;

import scorefour.core.GameState;
import scorefour.interfaces.GameMode;

public class NormalMode implements GameMode {

    private final GameState gameState;

    public NormalMode() {
        this.gameState = new GameState();
    }

    @Override
    public void setup() {
        gameState.startGui();
        gameState.start();
    }
}
