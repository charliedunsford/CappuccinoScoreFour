public class TestMode extends GameMode {
    @Override
    public GameState setMode() {
        GameState gameState = new GameState();
        gameState.setupTestMode();
        return gameState;
    }
}