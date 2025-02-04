import javax.swing.*;

public class GameState {

    // Sets up the game for a normal runtime
    public void setupNormalMode() {
        startGui();
    }

    // Sets up game for a debugging runtime
    public void setupTestMode() {
        // Do not run GUI
    }

    public void startGame() {
        // Game loop would go here
    }

    public void startGui() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Cappuccino Score Four");
        GamePanel panel = new GamePanel();
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.startPanelThread();
    }
}