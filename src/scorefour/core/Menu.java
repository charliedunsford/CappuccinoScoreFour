package scorefour.core;

import scorefour.common.Drawable;
import scorefour.common.GameState;
import scorefour.common.Interactable;
import scorefour.ui.MenuButton;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Menu extends State implements Interactable, Drawable {

    private final ArrayList<MenuButton> buttons;
    private final BufferedImage[] menuBackground;
    private int index;
    private long lastTime;

    public Menu(Game game) {
        super(game);
        buttons = new ArrayList<>();
        menuBackground = new BufferedImage[4];
        loadButtons();
        loadBackground();
        //lastTime = System.currentTimeMillis();
    }

    public void loadButtons() {
        buttons.add(new MenuButton(160, 336, 0, GameState.PLAYING, game.getAudioPlayer()));
        buttons.add(new MenuButton(448, 336, 1, GameState.QUIT, game.getAudioPlayer()));
    }

    public void loadBackground() {
        try {
            menuBackground[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/menu_1.png")));
            menuBackground[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/menu_2.png")));
            menuBackground[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/menu_3.png")));
            menuBackground[3] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/menu_4.png")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load menu images.");
        }
    }

    @Override
    public void update() {
        long currentTime = System.currentTimeMillis();
        long delay = 200;
        if (currentTime - lastTime >= delay) {
            index = (index + 1) % menuBackground.length;
            lastTime = currentTime;
        }
        for (MenuButton button : buttons) {
            button.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(menuBackground[index], 0, 0, null);
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
            button.setMouseOver((isIn(e, button)));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
}
