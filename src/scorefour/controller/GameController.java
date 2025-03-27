package scorefour.controller;

import scorefour.common.BeadColour;
import scorefour.common.Updatable;
import scorefour.common.Interactable;
import scorefour.common.VersusMode;
import scorefour.model.Board;
import scorefour.player.ComputerPlayer;
import scorefour.player.HumanPlayer;
import scorefour.player.Player;
import scorefour.view.BoardView;
import scorefour.view.OverlayView;
import scorefour.view.GameView;

import java.awt.*;
import java.awt.event.*;

/**
 * {@link GameController} manages the play state interactions, updates, drawing, and
 * user input handling.
 * <p>
 * It coordinates the {@link GameView}, {@link OverlayController},
 * and {@link BoardController} to render and control the in game interface.
 */
public class GameController implements Updatable, Interactable {

    private final GameView view;
    private final AudioController audioController;
    private GameManager gameManager;
    private OverlayController overlayController;
    private BoardController boardController;
    private Board board;

    Player whitePlayer, blackPlayer;

    /**
     * Constructs a {@link GameController} object with the given {@link GameView}.
     * <p>
     * Initializes the overlay and board components for gameplay.
     *
     * @param view the playing view used to render gameplay to the panel.
     */
    public GameController(GameView view, AudioController audioController) {
        this.view = view;
        this.audioController = audioController;
        initializeClasses();
    }

    // Initializes all classes required for the game.
    private void initializeClasses() {
        board = new Board();

        Player whitePlayer, blackPlayer;

        whitePlayer = new HumanPlayer(BeadColour.WHITE);
        blackPlayer = new HumanPlayer(BeadColour.BLACK);

        gameManager = new GameManager(board, whitePlayer, blackPlayer, audioController);

        boardController = new BoardController(new BoardView(board), gameManager);

        Rectangle overlayBounds = new Rectangle(0, 485, 800, 160);
        overlayController = new OverlayController(overlayBounds, new OverlayView(45), boardController, gameManager);
    }

    public void updatePlayers() {

        if (VersusMode.mode == VersusMode.PVC) {
            whitePlayer = new HumanPlayer(BeadColour.WHITE);
            blackPlayer = new ComputerPlayer(BeadColour.BLACK, board);
        } else if (VersusMode.mode == VersusMode.CVC) {
            whitePlayer = new ComputerPlayer(BeadColour.WHITE, board);
            blackPlayer = new ComputerPlayer(BeadColour.BLACK, board);
        } else {
            whitePlayer = new HumanPlayer(BeadColour.WHITE);
            blackPlayer = new HumanPlayer(BeadColour.BLACK);
        }

        gameManager.updatePlayers(whitePlayer, blackPlayer);
    }

    /**
     * Updates the overlay used by the {@code PlayingController}.
     */
    @Override
    public void update() {
        overlayController.update();
        boardController.update();
        gameManager.update();
    }

    /**
     * Draws the {@link GameView}, {@link OverlayController}, and {@link BoardController}.
     *
     * @param g the {@link Graphics} context used for rendering
     */
    public void draw(Graphics g) {
        view.draw(g);
        overlayController.draw(g);
        boardController.draw(g);
        gameManager.draw(g);
    }

    /**
     * Communicates with {@link OverlayController} and {@link BoardController} when a mouse button has been pressed.
     *
     * @param e the {@link MouseEvent} containing the mouse press status
     */
    @Override
    public void mousePressed(MouseEvent e) {
        overlayController.mousePressed(e);
        boardController.mousePressed(e);
    }

    /**
     * Communicates with {@link OverlayController} when the mouse has been released.
     *
     * @param e the {@link MouseEvent} containing the mouse release status
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        overlayController.mouseReleased(e);
        boardController.mouseReleased(e);
    }

    /**
     * Checks when a mouse has been moved and detects where the mouse is in a {@link Panel}.
     *
     * @param e the {@link MouseEvent} containing the new mouse position
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        overlayController.setMouseOver(overlayController.inBounds(e));
        overlayController.mouseMoved(e);
        boardController.mouseMoved(e);
    }

    /**
     * @return the {@link AudioController} used by the game.
     */
    public AudioController getAudioController() {
        return audioController;
    }

    /**
     * @return the {@link BoardController} used by the game.
     */
    public BoardController getBoardController() {
        return boardController;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
