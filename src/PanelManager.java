import javax.swing.*;

public class PanelManager {
    private Panel panel;

    public PanelManager(Board board) {
        try {
            SwingUtilities.invokeAndWait(() -> startGui(board));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startGui(Board board) {
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

    public Panel getPanel() {
        return panel;
    }
}
