package scorefour.view;

import scorefour.controller.MouseInputs;
import scorefour.controller.GameController;

import javax.swing.*;
import java.awt.*;

import static scorefour.view.GameView.PANEL_HEIGHT;
import static scorefour.view.GameView.PANEL_WIDTH;

/**
 * A {@code Panel} which acts as the main rendering and input handler for a {@link GameController}
 */
public class Panel extends JPanel {
    private final GameController gameController;

    WidgetsView widgetsView = new WidgetsView();
    WinLoseIndicator winLoseIndicator = new WinLoseIndicator();

    /**
     * Constructs a new {@code Panel}.
     * <p>
     * Accesses a {@link GameController} to render, and creates listeners for user input.
     *
     * @param gameController the {@link GameController} to be rendered
     */
    public Panel(GameController gameController) {
        this.gameController = gameController;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.add(widgetsView);

        MouseInputs mouseInputs = new MouseInputs(this);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    /**
     * Paints the contents of a {@link GameController} to the {@link Panel}
     *
     * @param g the {@link Graphics} context used for rendering
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameController.render(g);
    }

    /**
     * @return {@link GameController} object passed to the {@link Panel}
     */
    public GameController getGame() {
        return gameController;
    }
}
