package scorefour.ui;

import scorefour.common.Drawable;
import scorefour.common.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MenuButton implements Drawable {

    private GameState state;
    private BufferedImage[] images;
    private Rectangle bounds;
    private AudioPlayer audioPlayer;

    private int x, y, row, index;
    private boolean mouseOver, mousePressed;
    private boolean hoverSoundPlayed = false;

    public MenuButton(int x, int y, int row, GameState state, AudioPlayer audioPlayer) {
        this.x = x;
        this.y = y;
        this.row = row;
        this.state = state;
        this.audioPlayer = audioPlayer;
        loadImages();
        initializeBounds();
    }

    public void loadImages() {
        images = new BufferedImage[2];
        try {
            String button = (row == 0) ? "playButton" : "quitButton";
            images[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/" + button + ".png")));
            images[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/" + button + "_hover.png")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load the menu button images.");
        }
    }

    public void initializeBounds() {
        bounds = new Rectangle(x, y,191, 112);
    }

    @Override
    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
            if (!hoverSoundPlayed) {
                audioPlayer.playEffect(AudioPlayer.MENU_HOVER);
                hoverSoundPlayed = true;
            }
        }
        if (!mouseOver) {
            resetButton();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(images[index], x, y, 191, 112, null);
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
        hoverSoundPlayed = false;
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
