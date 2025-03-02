package scorefour.view;

import scorefour.common.Viewable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * {@code OverlayView} initializes a non-fixed visual overlay and provides a way to {@code draw} these graphics to a {@code Panel}.
 */
public class OverlayView implements Viewable {

    private BufferedImage overlay;
    private int y;

    /**
     * Constructs a {@code OverlayView} which stores a overlay image.
     * <p>
     * To render this view, call the {@code draw} method with a {@code Graphics} object.
     * <p>
     * This class can be accessed to view and change the {@code y} offset.
     *
     * @param y the {@code y} amount the overlay should be offset
     */
    public OverlayView(int y) {
        this.y = y;
        loadOverlay();
    }

    private void loadOverlay() {
        try {
            overlay = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/overlay.png")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load in game images.");
        }
    }

    /**
     * @return the {@code y} amount the {@code OverlayView} object is offset
     */
    public int getY() {
        return y;
    }

    /**
     * @param y integer amount to offset the {@code OverlayView}
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * The {@code draw} method is accessed by a {@link Panel} class to be drawn on a {@link Frame}
     *
     * @param g the {@link Graphics} context used for rendering
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(overlay, 0, y, null);
    }
}
