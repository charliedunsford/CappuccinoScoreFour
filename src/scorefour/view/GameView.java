package scorefour.view;

import scorefour.common.Viewable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * {@code PlayingView} initializes fixed in-game visuals and provides a way to {@code draw} these graphics to a {@link Panel}.
 */
public class GameView implements Viewable {

    private BufferedImage background;

    /**
     * Constructs a {@code PlayingView} which initializes a background image.
     */
    public GameView() {
        initializeBackgroundImage();
    }

    private void initializeBackgroundImage() {
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/playingBackground.png")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load in game images.");
        }
    }

    /**
     * This method is accessed by a {@link Panel} class to be drawn on a {@link Frame}
     *
     * @param g the {@link Graphics} context used for rendering
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }
}
