package scorefour.controller;

import scorefour.common.GameState;
import scorefour.view.*;
import scorefour.view.Panel;

import java.awt.*;

/**
 * The {@link ProgramController} manages the core logic of the program. This includes game transitions,
 * rendering, and updating components.
 * <p>
 * This class initializes various controllers and handles the game loop.
 */
public class ProgramController implements Runnable {

    private final AudioController audioController;
    private final ProgramView view;
    private MenuController menuController;
    private GameController gameController;

    private volatile boolean running;
    private int debugFPS;

    /**
     * Constructs the {@link ProgramController} object, initializes the required classes, and starts the game.
     */
    public ProgramController() {
        this.audioController = new AudioController();
        this.view = new ProgramView(this);
        initializeControllers();
        startGame();
    }

    // Initializes required controllers.
    private void initializeControllers() {
        menuController = new MenuController(new MenuView(), audioController);
        gameController = new GameController(new GameView(), audioController);
    }

    // Starts the game thread.
    private void startGame() {
        running = true;
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Stops the game loop from continuing and closes the graphical user interface.
     */
    public void stopGame() {
        if (view.getPanel() != null) {
            view.stopGUI();
        }
        running = false;
    }

    // Calls for the current game state to be updated.
    private void update() {
        switch (GameState.state) {
            case MENU -> menuController.update();
            case GAME -> gameController.update();
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
            case GAME -> gameController.draw(g);
        }
    }

    /**
     * The game loop of the {@link ProgramController} object, this method repaints if a panel exists and checks for object
     * updates at a set frame rate.
     */
    @Override
    public void run() {
        final double targetFPS = 60.0;
        final double drawInterval = 1000000000.0 / targetFPS;

        long lastTime = System.nanoTime();
        long timer = System.nanoTime();
        double unprocessedTime = 0;
        int frames = 0;

        while (running) {
            long currentTime = System.nanoTime();
            unprocessedTime += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            while (unprocessedTime >= 1) {
                update();
                unprocessedTime--;
            }

            view.repaint();
            frames++;

            long elapsed = System.nanoTime() - currentTime;
            long sleepTime = (long) (drawInterval - elapsed) / 1000000;
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            if (System.nanoTime() - timer >= drawInterval) {
                debugFPS = frames;
                frames = 0;
                timer += (long) 1000000000.0;
            }
        }
    }

    /**
     * The {@link MenuController} handles all interactions, visuals, and logic of the games {@code MENU} state.
     *
     * @return {@link MenuController} of the {@link ProgramController} object
     */
    public MenuController getMenu() {
        return menuController;
    }

    /**
     * The {@link GameController} handles all interactions, visuals, and logic of the games {@code GAME} state.
     *
     * @return {@link GameController} of the {@link ProgramController} object
     */
    public GameController getPlaying() {
        return gameController;
    }

    /**
     * {@code debugFPS} returns the current FPS.
     *
     * @return a integer value of the current frames per second
     */
    public int getDebugFPS() {
        return debugFPS;
    }

    /**
     * Returns the {@link ProgramController} view.
     *
     * @return {@link ProgramView}
     */
    public ProgramView getProgramView() {
        return view;
    }
}
