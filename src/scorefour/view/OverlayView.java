package scorefour.view;

import scorefour.common.BeadColour;
import scorefour.common.Viewable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * {@code OverlayView} initializes a non-fixed visual overlay and provides a way to {@code draw} these graphics to a {@link Panel}.
 */
public class OverlayView implements Viewable {

    private BufferedImage overlay;
    private BufferedImage turnIndicator;
    private BeadColour currentPlayerColour;
    private Font font;

    private int y, blackScore, whiteScore;

    /**
     * Constructs a {@link OverlayView} which stores an overlay image.
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

    // Loads overlay image to be displayed in game.
    private void loadOverlayImages() {
        try {
            overlay = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/overlay.png")));
            turnIndicator = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/arrow.png")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load overlay images.");
        }
    }

    // Loads custom font for score board.
    private void loadCustomFont() {
        try {
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("res/fonts/Daydream.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(48f);
        } catch (IOException|FontFormatException e) {
            throw new RuntimeException("Failed to load font.");
        }
    }

    /**
     * @return the {@code y} amount the {@link OverlayView} object is offset.
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
     * Sets the bead for the turn indicator to point towards.
     *
     * @param colour a {@link BeadColour}
     */
    public void setCurrentPlayerColour(BeadColour colour) {
        this.currentPlayerColour = colour;
    }

    /**
     * Sets the score to be displayed on the {@link OverlayView}.
     *
     * @param score integer array of each player's score
     */
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

        g.setFont(font);

        FontMetrics metrics = g.getFontMetrics(font);
        String whiteScoreString = String.valueOf(blackScore);
        String blackScoreString = String.valueOf(whiteScore);
        int whiteScoreWidth = metrics.stringWidth(whiteScoreString);
        int blackScoreWidth = metrics.stringWidth(blackScoreString);

        int whiteScoreX = 233;
        int blackScoreX = 568;

        g.setColor(new Color(102, 57, 49));
        g.drawString(whiteScoreString, whiteScoreX - (whiteScoreWidth / 2) + 5, y + 525);
        g.drawString(blackScoreString, blackScoreX - (blackScoreWidth / 2) + 5, y + 525);

        g.setColor(new Color(49, 27, 23));
        g.drawString(whiteScoreString, whiteScoreX - (whiteScoreWidth / 2), y + 520);
        g.drawString(blackScoreString, blackScoreX - (blackScoreWidth / 2), y + 520);


        if (currentPlayerColour == BeadColour.WHITE) {
            g.drawImage(turnIndicator, 385, y + 495, 32, 36, null);
        } else {
            g.drawImage(turnIndicator, 385 + 32, y + 495, -32, 36, null);
        }
    }
}
