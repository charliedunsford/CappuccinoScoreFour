import java.util.Scanner;

public class TestMode extends GameMode {

    private GameState gameState;
    private final Board board;
    private final Scanner scanner;

    public TestMode() {
        super.gameMode = "TEST";
        this.board = new Board();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void setup() {
        gameState = new GameState(board, null);
        gameState.start();
        startCommands();
    }

    // Test commands
    public void startCommands() {
        System.out.println("TESTING MODE (type help for all commands)");
        while (true) {
            System.out.print(">");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                System.exit(0);
            }

            if (input.equals("gui")) {
                PanelManager panelManager = new PanelManager(board);
                Panel panel = panelManager.getPanel();
                gameState.setPanel(panel);
                System.out.println("GUI working"); // Temp
            }
        }
    }
}