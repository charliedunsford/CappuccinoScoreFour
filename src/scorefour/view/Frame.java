package scorefour.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * A {@code Frame} creates a new {@link JFrame} where the contents of a {@link Panel} can be displayed.
 */
public class Frame {
    private final JFrame frame;
    private final BufferedImage[] icons;

    /**
     * Constructs a new {@code Frame} object and displays the given {@link Panel} on it.
     * <p>
     * This constructor also initializes the frames icons and title.
     *
     * @param panel a panel to be displayed
     */
    public Frame(Panel panel) {
        frame = new JFrame();
        icons = new BufferedImage[4];
        initializeIcons();

        frame.setTitle("Score Four");
        frame.setIconImages(List.of(icons));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setAlwaysOnTop(true); // This keeps the app on top! Comment if this isn't wanted

        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void initializeIcons() {
        try {
            icons[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/icons/128x128.png")));
            icons[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/icons/64x64.png")));
            icons[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/icons/32x32.png")));
            icons[3] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/icons/16x16.png")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load the icon images.");
        }
    }

    /**
     * This method releases the {@link Frame}'s resources and terminates it.
     */
    public void dispose() {
        frame.dispose();
    }
}
