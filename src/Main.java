public class Main {
    public static void main(String[] args) {
        GameState gameState = new GameState();

        if (args.length == 0) {
            gameState.setupTestMode();
        } else if (args[0].equals("test")) {
            gameState.setupTestMode();
        } else {
            throw new IllegalArgumentException("Invalid argument");
        }

        gameState.startGame();
    }
}