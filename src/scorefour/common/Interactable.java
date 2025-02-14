package scorefour.common;

import java.awt.event.MouseEvent;

public interface Interactable {

    void mouseClicked(MouseEvent e);

    void mousePressed(MouseEvent e);

    void mouseReleased(MouseEvent e);

    void mouseMoved(MouseEvent e);

}