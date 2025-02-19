package scorefour.core;

import scorefour.common.GameState;
import scorefour.ui.Panel;
import scorefour.ui.Window;

import java.awt.*;

public class Game implements Runnable {

    private Panel panel;
    private Window window;
    private Thread gameThread;
    private int debugFPS;
    private final int FPS = 60;
    private boolean running;

    private Menu menu;
    private GameInstance gameInstance;

    public final static int PANEL_WIDTH = 800;
    public final static int PANEL_HEIGHT = 600;

    public Game() {
        initializeClasses();
        startGameLoop();
    }

    public void initializeClasses() {
        menu = new Menu(this);
        gameInstance = new GameInstance(this);
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

    public void startGameLoop() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void stopGameThread() {
        running = false;
    }

    public void update() {
        switch (GameState.state) {
            case MENU -> menu.update();
            case PLAYING -> gameInstance.update();
            case QUIT -> System.exit(0);
        }
    }

    public void render(Graphics g) {
        switch (GameState.state) {
            case MENU -> menu.draw(g);
            case PLAYING -> gameInstance.draw(g);
        }
    }

    @Override
    public void run() {
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
                    frames = 0;
                    time += 1000;
                }
            }
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public GameInstance getActiveGame() {
        return gameInstance;
    }

    public int getDebugFPS() {
        return debugFPS;
    }
}
