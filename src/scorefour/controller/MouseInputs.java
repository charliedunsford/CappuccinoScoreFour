package scorefour.controller;

import scorefour.common.GameState;
import scorefour.view.Panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * {@link MouseInputs} listens to various user inputs which occur in a designated {@link Panel}.
 */
public class MouseInputs implements MouseListener, MouseMotionListener {

    private final Panel panel;

    /**
     * Constructs a new {@link MouseInputs} and assigns it to listen to a {@link Panel}.
     *
     * @param panel to listen to user inputs
     */
    public MouseInputs(Panel panel) {
        this.panel = panel;
    }

    // Not used.
    @Override
    public void mouseClicked(MouseEvent e) {}

    /**
     * Communicates the current mouse press status with the different {@link GameState}'s
     *
     * @param e the {@link MouseEvent} containing the mouse press status
     */
    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.state) {
            case MENU -> panel.getProgramController().getMenuController().mousePressed(e);
            case GAME -> panel.getProgramController().getGameController().mousePressed(e);
        }
    }

    /**
     * Communicates the current mouse release status with the different {@link GameState}'s
     *
     * @param e the {@link MouseEvent} containing the mouse release status
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.state) {
            case MENU -> panel.getProgramController().getMenuController().mouseReleased(e);
            case GAME -> panel.getProgramController().getGameController().mouseReleased(e);
        }
    }

    // Not used.
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    // Not used.
    @Override
    public void mouseExited(MouseEvent e) {

    }

    // Not used.
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Communicates the current mouse position status with the different {@link GameState}'s
     *
     * @param e the {@link MouseEvent} containing the new mouse position
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameState.state) {
            case MENU -> panel.getProgramController().getMenuController().mouseMoved(e);
            case GAME -> panel.getProgramController().getGameController().mouseMoved(e);
        }
    }
}
