package scorefour.core;

import scorefour.interfaces.Drawable;
import scorefour.ui.Panel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameState implements Runnable {

    private JFrame frame;
    private Panel panel;
    private final List<Drawable> drawables;
    private final Board board;
    private volatile boolean running;

    public GameState() {
        this.drawables = new ArrayList<>();
        this.board = new Board();
    }

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void stop() {
        running = false;
    }

    // This is the game loop
    @Override
    public void run() {
        final int FPS = 60;
        final double drawInterval = 1000000000.0 / FPS;
        long lastTime = System.nanoTime();
        double delta = 0;

        double time = System.currentTimeMillis();
        int frames = 0;

        drawables.add(board);

        while (running) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                updateGameState();

                if (panel != null) {
                    panel.repaint();
                }
                delta--;

                frames++;
                if (System.currentTimeMillis() - time >= 1000) {
                    // debug fps counter
                    // System.out.println(frames);
                    frames = 0;
                    time += 1000;
                }
            }
        }
    }

    private void updateGameState() {
        board.update();
    }

    public void startGui() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("Cappuccino Score Four");

        panel = new Panel(this);

        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void stopGui() {
        panel = null;
        if (frame != null) {
            frame.dispose();
        }
    }

    public void drawAll(Graphics2D g2d) {
        for (Drawable drawable : drawables) {
            drawable.draw(g2d);
        }
    }
}