package scorefour.model;

import scorefour.common.GameState;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Button {

    private final GameState state;
    private final Rectangle bounds;
    private int x, y;

    protected boolean mouseOver, mousePressed;

    public Button(Rectangle bounds, int x, int y, GameState state) {
        this.bounds = bounds;
        this.x = x;
        this.y = y;
        this.state = state;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds.setBounds(bounds);
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void applyGameState() {
        GameState.state = state;
    }

    public GameState getState() {
        return state;
    }

    public boolean isIn(MouseEvent e) {
        return bounds.contains(e.getX(), e.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        bounds.setLocation(x, y);
    }

    public int getWidth() {
        return bounds.width;
    }

    public int getHeight() {
        return bounds.height;
    }

    public void resetButton() {
        mouseOver = false;
        mousePressed = false;
    }

}
