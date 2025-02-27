package scorefour.controller;

import scorefour.common.GameState;
import scorefour.common.Interactable;
import scorefour.view.ButtonView;
import scorefour.view.MenuView;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MenuController implements Interactable {

    private final ArrayList<ButtonController> buttons;
    private final MenuView view;
    private final AudioController audioController;

    public MenuController(MenuView view, AudioController audioController) {
        this.view = view;
        this.audioController = audioController;
        buttons = new ArrayList<>();
        loadButtons();
    }

    public void loadButtons() {
        Rectangle playBounds = new Rectangle(160, 336, 0, 0);
        ButtonView playButtonView = new ButtonView(playBounds, 0);
        buttons.add(new ButtonController(playBounds, playButtonView, GameState.PLAYING));

        Rectangle quitBounds = new Rectangle(448, 336, 0, 0);
        ButtonView quitButtonView = new ButtonView(quitBounds, 1);
        buttons.add(new ButtonController(quitBounds, quitButtonView, GameState.QUIT));
    }

    //@Override
    public void update() {
        for (ButtonController button : buttons) {
            button.update();
        }
    }

    // @Override
    public void draw(Graphics g) {
        view.draw(g);
        for (ButtonController button : buttons) {
            button.draw(g);
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
                if (button.getState() == GameState.PLAYING) {
                    GameState.state = GameState.PLAYING;// Replace with more robust coding
                    audioController.playSong(AudioController.GAME);
                }
            }
            button.resetButton();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (ButtonController button : buttons) {
            button.setMouseOver((button.isIn(e)));
        }
    }
}
