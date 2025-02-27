package scorefour.controller;

import scorefour.common.Controllable;
import scorefour.common.GameState;
import scorefour.view.ButtonView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ButtonController implements Controllable {

    private final Rectangle bounds;
    private final ButtonView view;
    private final AudioController audioController;
    private final GameState state;

    private boolean hoverSoundPlayed = false;
    protected boolean mouseOver, mousePressed;

    public ButtonController(Rectangle bounds, ButtonView view, GameState state) {
        this.bounds = bounds;
        this.view = view;
        this.state = state;
        this.audioController = new AudioController();
    }

    @Override
    public void update() {
        view.setIndex(0);
        if (mouseOver) {
            view.setIndex(1);
            if (!hoverSoundPlayed) {
                hoverSoundPlayed = true;
                audioController.playEffect(AudioController.MENU_HOVER);
            }
        }
        if (!mouseOver) {
            resetButton();
            hoverSoundPlayed = false;
        }
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

    public boolean isIn(MouseEvent e) {
        return bounds.contains(e.getX(), e.getY());
    }

    public GameState getState() {
        return state;
    }

    public void resetButton() {
        mouseOver = false;
        mousePressed = false;
    }

    public void draw(Graphics g) {
        view.draw(g);
    }

    public void setY(int y) {
        bounds.setLocation(bounds.x, y);
    }

    public int getY() {
        return bounds.y;
    }
}
