package scorefour.core;

import scorefour.common.GameState;
import scorefour.common.Interactable;
import scorefour.ui.MenuButton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Menu extends State implements Interactable {

    private ArrayList<MenuButton> buttons;
    private BufferedImage menuBackground;

    public Menu(Game game) {
        super(game);
        buttons = new ArrayList<>();
        loadButtons();
        loadBackground();
    }

    public void loadButtons() {
        buttons.add(new MenuButton(160, 336, 0, GameState.PLAYING));
        buttons.add(new MenuButton(448, 336, 1, GameState.QUIT));
    }

    public void loadBackground() {
        try {
            menuBackground = ImageIO.read(new File("res/menu.png"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load menu image: res/menu.png");
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
        g.drawImage(menuBackground, 0, 0, null);
        for (MenuButton button : buttons) {
            button.draw(g);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton button : buttons) {
            if (isIn(e, button)) {
                button.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton button : buttons) {
            if (isIn(e, button) && button.isMousePressed()) {
                button.applyGameState();
            }
            button.resetButton();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton button : buttons) {
            button.setMouseOver((isIn(e, button)));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
}
