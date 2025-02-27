package scorefour.view;

import scorefour.common.Drawable;
import scorefour.common.GameState;
import scorefour.controller.Audio;
import scorefour.model.Button;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class GameButton extends Button implements Drawable {

    private BufferedImage[] images;
    private final Audio audio;

    private final int row;
    private int index;
    private boolean hoverSoundPlayed = false;


    public GameButton(int x, int y, int row, GameState state) {
        super(new Rectangle(x, y, 0,0), x, y, state);
        this.row = row;
        this.audio = new Audio();
        loadImages();
    }

    public void loadImages() {
        images = new BufferedImage[2];
        try {
            String button = (row == 0) ? "resetButton" : "smallQuitButton";
            images[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/" + button + ".png")));
            images[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/" + button + "_hover.png")));
            setBounds(new Rectangle(getX(), getY(), images[0].getWidth(), images[0].getHeight()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load the game button images.");
        }
    }

    @Override
    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
            if (!hoverSoundPlayed) {
                hoverSoundPlayed = true;
                audio.playEffect(Audio.MENU_HOVER);
            }
        }
        if (!mouseOver) {
            resetButton();
            hoverSoundPlayed = false;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(images[index], getX(), getY(), getWidth(), getHeight(), null);
    }
}
