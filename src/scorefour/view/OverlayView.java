package scorefour.view;

import scorefour.common.BeadColour;
import scorefour.common.Viewable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * {@code OverlayView} initializes a non-fixed visual overlay and provides a way to {@code draw} these graphics to a {@code Panel}.
 */
public class OverlayView implements Viewable {

    private BufferedImage overlay;
    private BufferedImage turnIndicator;
    private int y, blackScore, whiteScore;
    private BeadColour currentPlayerColour;

    private Font font;

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
        loadOverlayImages();
        loadCustomFont();
    }

    private void loadOverlayImages() {
        try {
            overlay = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/overlay.png")));
            turnIndicator = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/arrow.png")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load overlay images.");
        }
    }

    private void loadCustomFont() {
        try {
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("res/fonts/Daydream.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(48f);
        } catch (IOException|FontFormatException e) {
            throw new RuntimeException("Failed to load font.");
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

    public void setCurrentPlayerColour(BeadColour colour) {
        this.currentPlayerColour = colour;
    }

    public void setScore(int[] score) {
        this.blackScore = score[0];
        this.whiteScore = score[1];
    }

    /**
     * The {@code draw} method is accessed by a {@link Panel} class to be drawn on a {@link Frame}
     *
     * @param g the {@link Graphics} context used for rendering
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(overlay, 0, y, null);

        g.setColor(Color.black);
        g.setFont(font);
        g.drawString(String.valueOf(blackScore), 210, y + 520);
        g.drawString(String.valueOf(whiteScore), 545, y + 520);


        if (currentPlayerColour == BeadColour.WHITE) {
            g.drawImage(turnIndicator, 385, y + 495, 32, 36, null);
        } else {
            g.drawImage(turnIndicator, 385 + 32, y + 495, -32, 36, null);
        }
    }
}
