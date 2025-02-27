package scorefour.view;

import scorefour.common.Drawable;
import scorefour.common.GameState;
import scorefour.common.Interactable;
import scorefour.controller.Responsive;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Overlay extends Responsive implements Interactable, Drawable {

    private final ArrayList<GameButton> buttons;
    private BufferedImage overlay;
    private boolean mouseOver;

    public Overlay() {
        super(new Rectangle(0, 485, 800, 160), 0, 45);
        buttons = new ArrayList<>();
        loadButtons();
        loadOverlay();
    }

    public void loadButtons() {
        buttons.add(new GameButton(5, 600, 0, GameState.QUIT));
        buttons.add(new GameButton(670, 600, 1, GameState.QUIT));
    }

    private void loadOverlay() {
        try {
            overlay = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/overlay.png")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load in game images.");
        }
    }

    @Override
    public void update() {
        if (mouseOver) {
            if (getY() != 0) {
                setY(getY() - 3);
                for (GameButton button : buttons) {
                    button.setY(button.getY() - 3);
                }
            }
        } else {
            if (getY() != 45) {
                setY(getY() + 3);
                for (GameButton button : buttons) {
                    button.setY(button.getY() + 3);
                }
            }
        }

        for (GameButton button : buttons) {
            button.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(overlay, 0, getY(), null);
        for (GameButton button : buttons) {
            button.draw(g);
        }
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        for (GameButton button : buttons) {
            if (button.isIn(e)) {
                button.setMousePressed(true);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (GameButton button : buttons) {
            if (button.isIn(e) && button.isMousePressed()) {
                // Add Reset button action here
                button.applyGameState();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (GameButton button : buttons) {
            button.setMouseOver((button.isIn(e)));
        }
    }
}
