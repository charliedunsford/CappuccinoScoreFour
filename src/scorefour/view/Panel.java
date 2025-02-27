package scorefour.view;

import scorefour.controller.MouseInputs;
import scorefour.core.Game;

import javax.swing.*;
import java.awt.*;

import static scorefour.core.Game.PANEL_WIDTH;
import static scorefour.core.Game.PANEL_HEIGHT;

public class Panel extends JPanel {
    private final MouseInputs mouseInputs;
    private final Game game;

    public Panel(Game game) {
        mouseInputs = new MouseInputs(this);
        this.game = game;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));

        // Add additional listeners here
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        game.render(g2d);
    }

    public Game getGame() {
        return game;
    }
}
