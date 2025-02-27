package scorefour.view;

import scorefour.common.Viewable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ButtonView implements Viewable {

    private final Rectangle bounds;
    private BufferedImage[] images;
    private final int row;
    private int index;

    public ButtonView(Rectangle bounds, int row) {
        this.bounds = bounds;
        this.row = row;
        loadImages();
    }

    public void loadImages() {
        images = new BufferedImage[2];
        try {
            String buttonImage = getButtonImage();
            images[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/buttons/" + buttonImage + ".png")));
            images[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/buttons/" + buttonImage + "_hover.png")));
            bounds.setBounds(new Rectangle(bounds.x, bounds.y, images[0].getWidth(), images[0].getHeight()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load the menu button images.");
        }
    }

    private String getButtonImage() {
        return switch (row) {
            case 0 -> "playButton";
            case 1 -> "quitButton";
            case 2 -> "resetButton";
            case 3 -> "smallQuitButton";
            default -> throw new IllegalStateException("Unexpected button value row " + row);
        };
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(images[index], bounds.x, bounds.y, bounds.width, bounds.height, null);
    }
}
