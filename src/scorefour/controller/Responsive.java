package scorefour.controller;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Responsive {

    private final Rectangle bounds;
    private int y;

    protected boolean mouseOver;

    public Responsive(Rectangle bounds, int x, int y) {
        this.bounds = bounds;
        this.y = y;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isIn(MouseEvent e) {
        return bounds.contains(e.getX(), e.getY());
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
