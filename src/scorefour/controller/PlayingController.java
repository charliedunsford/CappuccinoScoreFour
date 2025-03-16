package scorefour.controller;

import scorefour.common.Updatable;
import scorefour.common.Interactable;
import scorefour.model.Board;
import scorefour.view.BoardView;
import scorefour.view.OverlayView;
import scorefour.view.PlayingView;

import java.awt.*;
import java.awt.event.*;

/**
 * {@code PlayingController} manages the play state interactions, updates, drawing, and
 * user input handling.
 * <p>
 * It coordinates the {@link PlayingView}, {@link OverlayController},
 * and {@link BoardController} to render and control the in game interface.
 */
public class PlayingController implements Updatable, Interactable {

    private final PlayingView view;
    private final AudioController audioController;
    private OverlayController overlayController;
    private BoardController boardController;

    /**
     * Constructs a {@code PlayingController} object with the given {@link PlayingView}.
     * <p>
     * Initializes the overlay and board components for gameplay.
     *
     * @param view the playing view used to render gameplay to the panel.
     */
    public PlayingController(PlayingView view, AudioController audioController) {
        this.view = view;
        this.audioController = audioController;
        initializeClasses();
    }

    private void initializeClasses() {
        boardController = new BoardController(new Board(), new BoardView());

        Rectangle overlayBounds = new Rectangle(0, 485, 800, 160);
        overlayController = new OverlayController(overlayBounds, new OverlayView(45), boardController);
    }

    /**
     * Updates the overlay used by the {@code PlayingController}.
     */
    @Override
    public void update() {
        overlayController.update();
        boardController.update();
    }

    /**
     * Draws the {@link PlayingView}, {@link OverlayController}, and {@link BoardController}.
     *
     * @param g the {@link Graphics} context used for rendering
     */
    public void draw(Graphics g) {
        view.draw(g);
        overlayController.draw(g);
        boardController.draw(g);
    }

    /**
     * Communicates with {@link OverlayController} when a mouse button has been pressed.
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
        overlayController.setMouseOver(overlayController.isIn(e));
        overlayController.mouseMoved(e);
        boardController.mouseMoved(e);
    }

    /**
     * @return the {@link AudioController} used by the playing state
     */
    public AudioController getAudioController() {
        return audioController;
    }

    public BoardController getBoardController() {
        return boardController;
    }
}
