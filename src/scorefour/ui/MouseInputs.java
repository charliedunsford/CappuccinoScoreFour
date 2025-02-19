package scorefour.ui;

import scorefour.common.GameState;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private Panel panel;

    public MouseInputs(Panel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameState.state) {
            case MENU -> panel.getGame().getMenu().mouseClicked(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.state) {
            case MENU -> panel.getGame().getMenu().mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.state) {
            case MENU -> panel.getGame().getMenu().mouseReleased(e);
            case PLAYING -> panel.getGame().getPlayingGame().mouseReleased(e);
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

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameState.state) {
            case MENU -> panel.getGame().getMenu().mouseMoved(e);
            case PLAYING -> panel.getGame().getPlayingGame().mouseMoved(e);
        }
    }
}
