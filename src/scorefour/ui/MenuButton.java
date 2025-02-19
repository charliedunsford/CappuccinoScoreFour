package scorefour.ui;

import scorefour.common.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuButton {

    private int x, y, row, index;
    private GameState state;
    private BufferedImage[] images;
    private Rectangle bounds;
    private boolean mouseOver, mousePressed;

    public MenuButton(int x, int y, int row, GameState state) {
        this.x = x;
        this.y = y;
        this.row = row;
        this.state = state;
        loadImages();
        initializeBounds();
    }

    public void loadImages() {
        images = new BufferedImage[2];
        try {
            String button = (row == 0) ? "playButton" : "quitButton";
            images[0] = ImageIO.read(new File("res/" + button + ".png"));
            images[1] = ImageIO.read(new File("res/" + button + "_hover.png"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load the menu button images.");
        }
    }

    public void initializeBounds() {
        bounds = new Rectangle(x, y,191, 112);
    }

    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
        }
    }

    public void draw(Graphics g) {
        g.drawImage(images[index], x, y, 191, 112, null);
    }

    public boolean isMouseOver() {
        return mouseOver;
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

    public void resetButton() {
        mouseOver = false;
        mousePressed = false;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void applyGameState() {
        GameState.state = state;
    }

    public GameState getState() {
        return state;
    }
}
