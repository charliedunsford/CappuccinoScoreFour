public class Main {
    public static void main(String[] args) {
        GameMode gameMode;

        if (args.length == 0) {
            gameMode = new NormalMode();
        } else if (args[0].equals("test")) {
            gameMode = new TestMode();
        } else {
            throw new IllegalArgumentException("Invalid argument");
        }

        GameState gameState = gameMode.setMode();

        gameState.startGame();
    }
}