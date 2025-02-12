package scorefour.core;

import scorefour.interfaces.Drawable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UI implements Drawable {

    private final BufferedImage ui;

    public UI() {
        try {
            ui = ImageIO.read(new File("res/UI_nobox.png"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load UI image: res/UI_nobox.png");
        }
    }

    public void update() {
        // Track score etc
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(ui, 0, 0, null);
    }
}
