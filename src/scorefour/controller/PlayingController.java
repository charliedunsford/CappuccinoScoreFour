package scorefour.controller;

import scorefour.common.Viewable;
import scorefour.common.Interactable;
import scorefour.view.BoardView;
import scorefour.view.OverlayView;
import scorefour.view.PlayingView;

import java.awt.*;
import java.awt.event.*;

public class PlayingController implements Viewable, Interactable {

    private OverlayController overlay;
    private final PlayingView view;
    private BoardView board; // no good

    public PlayingController(PlayingView view) {
        this.view = view;
        initializeClasses();
    }

    public void initializeClasses() {
        Rectangle overlayBounds = new Rectangle(0, 485, 800, 160);
        overlay = new OverlayController(overlayBounds, new OverlayView());

        // BoardController will go here!
        board = new BoardView();
    }

    //@Override
    public void update() {
        overlay.update();
    }

    @Override
    public void draw(Graphics g) {
        view.draw(g);
        overlay.draw(g);
        board.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        overlay.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        overlay.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        overlay.setMouseOver(overlay.isIn(e));
        overlay.mouseMoved(e);
    }
}
