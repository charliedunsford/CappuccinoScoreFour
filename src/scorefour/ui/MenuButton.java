package scorefour.ui;

import scorefour.common.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuButton {

    private int x, y, row;
    private GameState state;
    private BufferedImage image;
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
        try {
            String button = (row == 0) ? "playButton" : "quitButton";
            image = ImageIO.read(new File("res/" + button + ".png"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load the menu button image.");
        }
    }

    public void initializeBounds() {
        bounds = new Rectangle(x, y,191, 112);
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, 191, 112, null);
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
