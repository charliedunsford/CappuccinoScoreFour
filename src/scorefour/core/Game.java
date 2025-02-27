package scorefour.core;

import scorefour.common.GameState;
import scorefour.controller.AudioController;
import scorefour.controller.MenuController;
import scorefour.controller.PlayingController;
import scorefour.view.MenuView;
import scorefour.view.Panel;
import scorefour.view.PlayingView;
import scorefour.view.Window;

import java.awt.*;

public class Game implements Runnable {

    private Panel panel;
    private Window window;
    private int debugFPS;
    private volatile boolean running;

    private MenuController menuController;
    private PlayingController playingController;
    private AudioController audioController;

    public final static int PANEL_WIDTH = 800;
    public final static int PANEL_HEIGHT = 600;

    public Game() {
        initializeClasses();
        startGame();
    }

    public void initializeClasses() {
        audioController = new AudioController();
        menuController = new MenuController(new MenuView(), audioController);
        playingController = new PlayingController(new PlayingView());
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
            case MENU -> menuController.update();
            case PLAYING -> playingController.update();
            case QUIT -> System.exit(0);
        }
    }

    public void render(Graphics g) {
        switch (GameState.state) {
            case MENU -> menuController.draw(g);
            case PLAYING -> playingController.draw(g);
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

    public MenuController getMenu() {
        return menuController;
    }

    public PlayingController getPlaying() {
        return playingController;
    }

    public AudioController getAudio() {
        return audioController;
    }

    public int getDebugFPS() {
        return debugFPS;
    }
}
