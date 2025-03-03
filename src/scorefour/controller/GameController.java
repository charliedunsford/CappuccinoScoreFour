package scorefour.controller;

import scorefour.common.GameState;
import scorefour.view.*;

import java.awt.*;

/**
 * The {@code GameController} object manages the core logic of the game.
 * This includes game transitions, rendering, and updating game components.
 * <p>
 * This class initializes various controllers, handles the game loop, and manages the GUI.
 */
public class GameController implements Runnable {

    private int debugFPS;
    private volatile boolean running;

    private final AudioController audioController;
    private final GameView view;
    private MenuController menuController;
    private PlayingController playingController;

    /**
     * Constructs the {@code GameController} object, initializes the required classes, and starts the game.
     */
    public GameController() {
        this.audioController = new AudioController();
        this.view = new GameView(this);
        initializeControllers();
        startGame();
    }

    private void initializeControllers() {
        menuController = new MenuController(view.getMenuView(), audioController);
        playingController = new PlayingController(view.getPlayingView(), audioController);
    }

    private void startGame() {
        running = true;
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Stops the game loop from continuing.
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
            case PLAYING -> playingController.update();
            case QUIT -> System.exit(0);
        }
    }

    /**
     * Renders the graphics drawn from the current {@link GameState}.
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
     * The game loop of the {@link GameController} object.
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

    public MenuController getMenu() {
        return menuController;
    }

    public PlayingController getPlaying() {
        return playingController;
    }

    public int getDebugFPS() {
        return debugFPS;
    }

    public GameView getGameView() {
        return view;
    }
}
