package scorefour.core;

import scorefour.common.Drawable;
import scorefour.common.GameState;
import scorefour.common.Interactable;
import scorefour.view.MenuButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Objects;

public class Menu extends State implements Interactable, Drawable {

    private final ArrayList<MenuButton> buttons;
    private ImageIcon menuBackground;

    public Menu(Game game) {
        super(game);
        buttons = new ArrayList<>();
        loadButtons();
        loadBackground();
        //lastTime = System.currentTimeMillis();
    }

    public void loadButtons() {
        buttons.add(new MenuButton(160, 336, 0, GameState.PLAYING));
        buttons.add(new MenuButton(448, 336, 1, GameState.QUIT));
    }

    public void loadBackground() {
        try {
            menuBackground = new ImageIcon(Objects.requireNonNull(getClass().getResource("/res/menu.gif")));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load menu background.");
        }
    }

    @Override
    public void update() {
        for (MenuButton button : buttons) {
            button.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(menuBackground.getImage(),0,0,null);
        for (MenuButton button : buttons) {
            button.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton button : buttons) {
            if (button.isIn(e)) {
                button.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton button : buttons) {
            if (button.isIn(e) && button.isMousePressed()) {
                if (button.getState() == GameState.PLAYING) {
                    setGameState(GameState.PLAYING); // Replace with more robust coding
                }
                button.applyGameState();
            }
            button.resetButton();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton button : buttons) {
            button.setMouseOver((button.isIn(e)));
        }
    }
}
