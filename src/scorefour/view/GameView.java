package scorefour.view;

import scorefour.controller.GameController;

public class GameView {

    private Panel panel;
    private Frame frame;
    private final MenuView menuView;
    private final PlayingView playingView;
    private final GameController gameController;

    /**
     * {@code PANEL_WIDTH} defines the width of the game panel.
     */
    public static final int PANEL_WIDTH = 800;
    /**
     * {@code PANEL_HEIGHT} defines the height of the game panel.
     */
    public static final int PANEL_HEIGHT = 600;

    public GameView(GameController gameController) {
        this.gameController = gameController;
        this.menuView = new MenuView();
        this.playingView = new PlayingView();
    }

    /**
     * Starts the graphical user interface if a {@link Panel} object does not already exist.
     */
    public void startGUI() {
        if (panel != null) {
            return;
        }
        panel = new Panel(gameController);
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

    public PlayingView getPlayingView() {
        return playingView;
    }

    public Panel getPanel() {
        return panel;
    }
}
