package scorefour.view;

import scorefour.controller.MouseInputs;
import scorefour.core.Game;

import javax.swing.*;
import java.awt.*;

import static scorefour.core.Game.PANEL_WIDTH;
import static scorefour.core.Game.PANEL_HEIGHT;

/**
 * A {@code Panel} which acts as the main rendering and input handler for a {@link Game}
 */
public class Panel extends JPanel {
    private final Game game;

    /**
     * Constructs a new {@code Panel}.
     * <p>
     * Accesses a {@link Game} to render, and creates listeners for user input.
     *
     * @param game the {@link Game} to be rendered
     */
    public Panel(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        MouseInputs mouseInputs = new MouseInputs(this);

        // Add additional listeners here
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    /**
     * Paints a {@link Game} to a {@link Panel}
     *
     * @param g the {@link Graphics} context used for rendering
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        game.render(g2d);
    }

    /**
     * @return {@link Game} object passed to the {@link Panel}
     */
    public Game getGame() {
        return game;
    }
}
