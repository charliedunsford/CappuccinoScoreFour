package scorefour.controller;

import scorefour.common.GameState;
import scorefour.view.*;

import java.awt.*;

/**
 * The {@code Game} object manages the core logic of the game. This includes game transitions,
 * rendering, and updating game components.
 * <p>
 * This class initializes various controllers, handles the game loop, and manages the GUI.
 */
public class ProgramController implements Runnable {
    private int debugFPS;
    private volatile boolean running;

    private final AudioController audioController;
    private final ProgramView view;
    private MenuController menuController;
    private GameController gameController;

    /**
     * Constructs the {@code Game} object, initializes the required classes, and starts the game.
     */
    public ProgramController() {
        this.audioController = new AudioController();
        this.view = new ProgramView(this);
        initializeControllers();
        startGame();
    }

    private void initializeControllers() {
        menuController = new MenuController(view.getMenuView(), audioController);
        gameController = new GameController(view.getGameView(), audioController);
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
        if (view.getPanel() != null) {
            view.stopGUI();
        }
        running = false;
    }

    private void update() {
        switch (GameState.state) {
            case MENU -> menuController.update();
            case GAME -> gameController.update();
            case QUIT -> System.exit(0);
        }
    }

    /**
     * Renders the graphics drawn from the current {@link GameState} to a {@code Panel}'s graphics component.
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
        int FPS = 60;
        final double drawInterval = 1000000000.0 / FPS;
        int frames = 0;
        long previousTime = System.nanoTime();
        long time = System.currentTimeMillis();
        double delta = 0;

        while (running) {
            long currentTime = System.nanoTime();
            delta += (currentTime - previousTime) / drawInterval;
            previousTime = currentTime;

            if (delta >= 1) {
                update();
                view.repaint();
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
     * @return {@link MenuController} of the {@link ProgramController} object
     */
    public MenuController getMenu() {
        return menuController;
    }

    /**
     * The {@link GameController} handles all interactions, visuals, and logic of the games {@code PLAY} state.
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

    public ProgramView getGameView() {
        return view;
    }
}
