package scorefour.view;

import scorefour.common.Viewable;
import scorefour.player.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * This class displays the win view when the game has been won.
 */
public class WinView implements Viewable {

    private final Player winner;

    private BufferedImage tint;
    private Font font;

    /**
     * Constructs a {@link WinView} object.
     *
     * @param winner the player to be displayed as the winner
     */
    public WinView(Player winner) {
        this.winner = winner;
        loadCustomFont();
        loadTintImage();
    }

    // Loads custom font for text to be typed in
    private void loadCustomFont() {
        try {
            InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("res/fonts/Daydream.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(32f);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException("Failed to load font.");
        }
    }

    // Loads tint which occurs during win view
    private void loadTintImage() {
        try {
            tint = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/tint.png")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load tint image.");
        }
    }

    /**
     * The {@code draw} method is accessed by a {@link Panel} class to be drawn on a {@link Frame}
     *
     * @param g the {@link Graphics} context used for rendering
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(tint, 0, 0, null);

        g.setFont(font);

        FontMetrics metrics = g.getFontMetrics(font);
        String message = winner + " has won the game!";
        int messageWidth = metrics.stringWidth(message);

        int messageX = 400;

        g.setColor(Color.BLACK);
        g.drawString(message, messageX - (messageWidth / 2) - 5, 255);
        g.setColor(Color.YELLOW);
        g.drawString(message, messageX - (messageWidth / 2), 250);
    }
}
