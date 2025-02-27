package scorefour.core;

import scorefour.common.GameState;
import scorefour.controller.Audio;
import scorefour.view.Panel;
import scorefour.view.Window;

import java.awt.*;

public class Game implements Runnable {

    private Panel panel;
    private Window window;
    private int debugFPS;
    private volatile boolean running;

    private Menu menu;
    private Playing playing;
    private Audio audio;

    public final static int PANEL_WIDTH = 800;
    public final static int PANEL_HEIGHT = 600;

    public Game() {
        initializeClasses();
        startGame();
    }

    public void initializeClasses() {
        audio = new Audio();
        menu = new Menu(this);
        playing = new Playing(this);
    }

    public void startGUI() {
        if (panel != null) {
            return;
        }
        panel = new Panel(this);
        window = new Window(panel);
    }

    public void stopGUI() {
        panel = null;
        window.dispose();
    }

    public void startGame() {
        running = true;
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public void stopGame() {
        if (panel != null) {
            stopGUI();
        }
        running = false;
    }

    public void update() {
        switch (GameState.state) {
            case MENU -> menu.update();
            case PLAYING -> playing.update();
            case QUIT -> System.exit(0);
        }
    }

    public void render(Graphics g) {
        switch (GameState.state) {
            case MENU -> menu.draw(g);
            case PLAYING -> playing.draw(g);
        }
    }

    @Override
    public void run() {
        int FPS = 60;
        final double drawInterval = 1000000000.0 / FPS;
        long previousTime = System.nanoTime();

        int frames = 0;

        long time = System.currentTimeMillis();
        double delta = 0;

        while (running) {
            long currentTime = System.nanoTime();
            delta += (currentTime - previousTime) / drawInterval;
            previousTime = currentTime;

            if (delta >= 1) {
                update();

                if (panel != null) {
                    panel.repaint();
                }

                delta--;
                frames++;

                if (System.currentTimeMillis() - time >= 1000) {
                    debugFPS = frames;
                    // System.out.println(debugFPS); for testing
                    frames = 0;
                    time += 1000;
                }
            }
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Audio getAudio() {
        return audio;
    }

    public int getDebugFPS() {
        return debugFPS;
    }
}
