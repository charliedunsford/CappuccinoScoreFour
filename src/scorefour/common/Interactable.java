package scorefour.common;

import java.awt.Graphics;
import java.awt.event.MouseEvent;

public interface Interactable {
    public void update();

    public void draw(Graphics g);

    public void mouseClicked(MouseEvent e);

    public void mousePressed(MouseEvent e);

    public void mouseReleased(MouseEvent e);

    public void mouseMoved(MouseEvent e);

}