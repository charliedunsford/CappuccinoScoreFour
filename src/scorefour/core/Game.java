package scorefour.core;

import scorefour.common.GameState;
import scorefour.controller.MenuController;
import scorefour.controller.PlayingController;
import scorefour.view.MenuView;
import scorefour.view.Panel;
import scorefour.view.PlayingView;
import scorefour.view.Frame;

import java.awt.*;

/**
 * The {@code Game} object manages the core logic of the game. This includes game transitions,
 * rendering, and updating game components.
 * <p>
 * This class initializes various controllers, handles the game loop, and manages the GUI.
 */
public class Game implements Runnable {

    private Panel panel;
    private Frame frame;
    private int debugFPS;
    private volatile boolean running;

    private MenuController menuController;
    private PlayingController playingController;

    /**
     * {@code PANEL_WIDTH} defines the width of the game panel.
     */
    public final static int PANEL_WIDTH = 800;
    /**
     * {@code PANEL_HEIGHT} defines the height of the game panel.
     */
    public final static int PANEL_HEIGHT = 600;

    /**
     * Constructs the {@code Game} object, initializes the required classes, and starts the game.
     */
    public Game() {
        initializeClasses();
        startGame();
    }

    private void initializeClasses() {
        menuController = new MenuController(new MenuView());
        playingController = new PlayingController(new PlayingView());
    }

    /**
     * Starts the graphical user interface if a {@link Panel} object does not already exist.
     */
    public void startGUI() {
        if (panel != null) {
            return;
        }
        panel = new Panel(this);
        frame = new Frame(panel);
    }

    /**
     * Stops the graphical user interface.
     */
    public void stopGUI() {
        panel = null;
        frame.dispose();
    }

    private void startGame() {
        running = true;
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Stops the game loop from continuing and closes the graphical user interface.
     */
    public void stopGame() {
        if (panel != null) {
            stopGUI();
        }
        running = false;
    }

    private void update() {
        switch (GameState.state) {
            case MENU -> menuController.update();
            case PLAYING -> playingController.update();
            case QUIT -> System.exit(0);
        }
    }

    /**
     * Renders the graphics drawn from the current {@link GameState} to a {@link Panel}'s graphics component.
     *
     * @param g the {@link Graphics} context used for rendering
     */
    public void render(Graphics g) {
        switch (GameState.state) {
            case MENU -> menuController.draw(g);
            case PLAYING -> playingController.draw(g);
        }
    }

    /**
     * The game loop of the {@link Game} object, this method repaints if a panel exists and checks for object
     * updates at a set frame rate.
     */
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
                    frames = 0;
                    time += 1000;
                }
            }
        }
    }

    /**
     * The {@link MenuController} handles all interactions, visuals, and logic of the games {@code MENU} state.
     *
     * @return {@link MenuController} of the {@link Game} object
     */
    public MenuController getMenu() {
        return menuController;
    }

    /**
     * The {@link PlayingController} handles all interactions, visuals, and logic of the games {@code PLAY} state.
     *
     * @return {@link PlayingController} of the {@link Game} object
     */
    public PlayingController getPlaying() {
        return playingController;
    }

    /**
     * {@code debugFPS} tracks the current frames per second the program is running.
     *
     * @return a integer value of the current frames per second
     */
    public int getDebugFPS() {
        return debugFPS;
    }
}
