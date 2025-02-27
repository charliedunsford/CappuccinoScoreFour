package scorefour.controller;

import scorefour.common.Controllable;
import scorefour.common.GameState;
import scorefour.common.Interactable;
import scorefour.view.ButtonView;
import scorefour.view.OverlayView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class OverlayController implements Controllable, Interactable {

    private final ArrayList<ButtonController> buttons;
    private final Rectangle bounds;
    private final OverlayView view;

    protected boolean mouseOver;

    public OverlayController(Rectangle bounds, OverlayView view) {
        this.bounds = bounds;
        this.view = view;
        buttons = new ArrayList<>();
        loadButtons();
    }

    public void loadButtons() {
        Rectangle resetButtonBounds = new Rectangle(5, 600, 0, 0);
        ButtonView resetButtonView = new ButtonView(resetButtonBounds, 2);
        buttons.add(new ButtonController(resetButtonBounds, resetButtonView, GameState.QUIT));

        Rectangle quitButtonBounds = new Rectangle(670, 600, 0, 0);
        ButtonView quitButtonView = new ButtonView(quitButtonBounds, 3);
        buttons.add(new ButtonController(quitButtonBounds, quitButtonView, GameState.QUIT));
    }

    //@Override
    public void update() {
        if (mouseOver) {
            if (view.getY() != 0) {
                view.setY(view.getY() - 3);
                for (ButtonController button : buttons) {
                    button.setY(button.getY() - 3);
                }
            }
        } else {
            if (view.getY() != 45) {
                view.setY(view.getY() + 3);
                for (ButtonController button : buttons) {
                    button.setY(button.getY() + 3);
                }
            }
        }

        for (ButtonController button : buttons) {
            button.update();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        for (ButtonController button : buttons) {
            if (button.isIn(e)) {
                button.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (ButtonController button : buttons) {
            if (button.isIn(e) && button.isMousePressed()) {
                // Add Reset button action here
                button.applyGameState();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (ButtonController button : buttons) {
            button.setMouseOver((button.isIn(e)));
        }
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isIn(MouseEvent e) {
        return bounds.getBounds().contains(e.getX(), e.getY());
    }

    public void draw(Graphics g) {
        view.draw(g);
        for (ButtonController button : buttons) {
            button.draw(g);
        }
    }
}
