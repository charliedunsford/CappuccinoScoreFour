package scorefour.ui;

import scorefour.core.GameState;

import java.awt.*;
import javax.swing.*;

public class Panel extends JPanel {

    private final GameState gameState;

    public Panel(GameState gameState) {
        this.gameState = gameState;
        setPreferredSize(new Dimension(800,500));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        gameState.drawAll(g2d);
    }
}