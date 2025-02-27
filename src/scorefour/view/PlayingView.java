package scorefour.view;

import scorefour.common.Viewable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class PlayingView implements Viewable {

    private BufferedImage background;

    public PlayingView() {
        initializeBackground();
    }

    public void initializeBackground() {
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/playingBackground.png")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load in game images.");
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }
}
