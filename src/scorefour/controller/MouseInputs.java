package scorefour.controller;

import scorefour.common.GameState;
import scorefour.view.Panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * {@code MouseInputs} listens to various user inputs which occur in a designated {@link Panel}.
 */
public class MouseInputs implements MouseListener, MouseMotionListener {

    private final Panel panel;

    /**
     * Constructs a new {@code MouseInput} and assigns it to listen to a {@link Panel}.
     *
     * @param panel to listen to user inputs
     */
    public MouseInputs(Panel panel) {
        this.panel = panel;
    }

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
            case MENU -> panel.getGame().getMenu().mousePressed(e);
            case PLAYING -> panel.getGame().getPlaying().mousePressed(e);
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
            case MENU -> panel.getGame().getMenu().mouseReleased(e);
            case PLAYING -> panel.getGame().getPlaying().mouseReleased(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

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
            case MENU -> panel.getGame().getMenu().mouseMoved(e);
            case PLAYING -> panel.getGame().getPlaying().mouseMoved(e);
        }
    }
}
