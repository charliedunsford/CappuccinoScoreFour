import javax.swing.*;

public class NormalMode implements GameMode {

    private Panel panel;
    private GameState gameState;
    private final Board board;

    public NormalMode() {
        this.board = new Board();
    }

    @Override
    public void setup() {
        startGui();
        gameState = new GameState(board, panel);
        gameState.start();
    }

    public void startGui() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Cappuccino Score Four");

        panel = new Panel(board);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
