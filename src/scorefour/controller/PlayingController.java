package scorefour.controller;

import scorefour.common.Controllable;
import scorefour.common.Interactable;
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
public class PlayingController implements Controllable, Interactable {

    private final PlayingView view;
    private final AudioController audioController;
    private OverlayController overlay;
    private BoardView board;

    /**
     * Constructs a {@code PlayingController} object with the given {@link PlayingView}.
     * <p>
     * Initializes the overlay and board components for gameplay.
     *
     * @param view the playing view used to render gameplay to the panel.
     */
    public PlayingController(PlayingView view) {
        this.view = view;
        this.audioController = new AudioController();
        initializeClasses();
    }

    private void initializeClasses() {
        Rectangle overlayBounds = new Rectangle(0, 485, 800, 160);
        overlay = new OverlayController(overlayBounds, new OverlayView(45));

        // BoardController will go here!
        board = new BoardView();
    }

    /**
     * Updates the overlay used by the {@code PlayingController}.
     */
    @Override
    public void update() {
        overlay.update();
    }

    /**
     * Draws the {@link PlayingView}, {@link OverlayController}, and {@link BoardController}.
     *
     * @param g the {@link Graphics} context used for rendering
     */
    public void draw(Graphics g) {
        view.draw(g);
        overlay.draw(g);
        board.draw(g);
    }

    /**
     * Communicates with {@link OverlayController} when a mouse button has been pressed.
     *
     * @param e the {@link MouseEvent} containing the mouse press status
     */
    @Override
    public void mousePressed(MouseEvent e) {
        overlay.mousePressed(e);
    }

    /**
     * Communicates with {@link OverlayController} when the mouse has been released.
     *
     * @param e the {@link MouseEvent} containing the mouse release status
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        overlay.mouseReleased(e);
    }

    /**
     * Checks when a mouse has been moved and detects where the mouse is in a {@link Panel}.
     *
     * @param e the {@link MouseEvent} containing the new mouse position
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        overlay.setMouseOver(overlay.isIn(e));
        overlay.mouseMoved(e);
    }

    /**
     * @return the {@link AudioController} used by the playing state
     */
    public AudioController getAudioController() {
        return audioController;
    }
}
