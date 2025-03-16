package scorefour.common;

import scorefour.view.Panel;

import java.awt.event.MouseEvent;

public interface Interactable {

    /**
     * Communicates with controllers when a mouse button has been pressed.
     *
     * @param e the {@link MouseEvent} containing the mouse press status
     */
    void mousePressed(MouseEvent e);

    /**
     * Communicates with controllers to set the {@link GameState} and any additional changes depending on the button
     * released.
     *
     * @param e the {@link MouseEvent} containing the mouse release status
     */
    void mouseReleased(MouseEvent e);

    /**
     * Checks when a mouse has been moved and detects where the mouse is in a {@link Panel}.
     *
     * @param e the {@link MouseEvent} containing the new mouse position
     */
    void mouseMoved(MouseEvent e);

}