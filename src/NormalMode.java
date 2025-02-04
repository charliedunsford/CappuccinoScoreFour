public class NormalMode extends GameMode {
    @Override
    public GameState setMode() {
        GameState gameState = new GameState();
        gameState.setupNormalMode();
        return gameState;
    }
}