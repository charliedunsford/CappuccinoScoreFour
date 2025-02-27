package scorefour.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Window {
    private final JFrame frame;
    private final BufferedImage[] icons;

    public Window(Panel panel) {
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

    public void dispose() {
        frame.dispose();
    }
}
