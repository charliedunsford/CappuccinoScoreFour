package scorefour.view;

import scorefour.common.Viewable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * {@code ButtonView} initializes the visual aspects of a button and provides a way to {@code draw} these graphics to a {@code Panel} and
 * edit the state of a graphic using the {@code setIndex} method.
 */
public class ButtonView implements Viewable {

    private final Rectangle bounds;
    private BufferedImage[] images;
    private int row;
    private int index;

    /**
     * Constructs a {@code ButtonView} object which stores a pair of images which are defined by the {@code row} integer passed to it.
     * <p>
     * {@code ButtonView} is made up of two images, hover and not hover, which can be switched between using the {@code setIndex} method.
     * <p>
     * The {@code bounds} dictate the size which the images will take up when drawn on the {@link Panel}.
     *
     * @param bounds a {@link Rectangle} object defining the size of the visual button
     * @param row the type of button to be displayed
     */
    public ButtonView(Rectangle bounds, int row) {
        this.bounds = bounds;
        this.row = row;
        loadButtonImages();
    }

    private void loadButtonImages() {
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
            case 2 -> "clearButton";
            case 3 -> "smallQuitButton";
            case 4 -> "peg";
            case 5 -> "pvp";
            case 6 -> "pvc";
            case 7 -> "cvc";
            default -> throw new IllegalStateException("Button row " + row + " does not exist.");
        };
    }

    /**
     * {@code ButtonView} is made up of two images, hover and not hover, which can be switched between using the setIndex method.
     *
     * @param index switch between not hover (0) and hover (1)
     */
    public void setIndex(int index) {
        this.index = index;
    }

    public void setRow(int row) {
        this.row = row;
        loadButtonImages();
    }

    /**
     * The {@code draw} method is accessed by a {@link Panel} class to be drawn on a {@link Frame}
     *
     * @param g the {@link Graphics} context used for rendering
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(images[index], bounds.x, bounds.y, bounds.width, bounds.height, null);
    }
}
