package scorefour.view;

import scorefour.controller.ProgramController;

public class ProgramView {

    private Panel panel;
    private Frame frame;
    private final MenuView menuView;
    private final GameView gameView;
    private final ProgramController programController;

    /**
     * {@code PANEL_WIDTH} defines the width of the game panel.
     */
    public static final int PANEL_WIDTH = 800;
    /**
     * {@code PANEL_HEIGHT} defines the height of the game panel.
     */
    public static final int PANEL_HEIGHT = 600;

    public ProgramView(ProgramController programController) {
        this.programController = programController;
        this.menuView = new MenuView();
        this.gameView = new GameView();
    }

    /**
     * Starts the graphical user interface if a {@link Panel} object does not already exist.
     */
    public void startGUI() {
        if (panel != null) {
            return;
        }
        panel = new Panel(programController);
        frame = new Frame(panel);
    }

    /**
     * Stops the graphical user interface.
     */
    public void stopGUI() {
        panel = null;
        if (frame != null) {
            frame.dispose();
        }
    }

    public void repaint() {
        if (panel != null) {
            panel.repaint();
        }
    }

    public MenuView getMenuView() {
        return menuView;
    }

    public GameView getGameView() {
        return gameView;
    }

    public Panel getPanel() {
        return panel;
    }
}
