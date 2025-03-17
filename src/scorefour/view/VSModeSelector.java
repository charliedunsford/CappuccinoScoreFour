package scorefour.view;

import scorefour.common.Viewable;
import scorefour.common.GameMode;
import scorefour.common.VSMode;
import scorefour.controller.AudioController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * A selector component for choosing game modes (PVP, PVC, CVC).
 * Follows the same design patterns as other UI components.
 */
public class VSModeSelector implements Viewable {

    private final AudioController audio;
    private BufferedImage background;
    private BufferedImage[] modeImages;
    private BufferedImage[] arrowImages;

    private final Rectangle leftArrowBounds;
    private final Rectangle rightArrowBounds;

    private boolean leftArrowHover;
    private boolean rightArrowHover;
    private boolean leftArrowPressed;
    private boolean rightArrowPressed;

    private int currentModeIndex;
    private final VSMode[] modes = {VSMode.PVP, VSMode.PVC, VSMode.CVC};

    private final int x;
    private final int y;
    private boolean hoverSoundPlayed;

    /**
     * Constructor for the VS Mode Selector.
     *
     * @param x X-coordinate for selector placement
     * @param y Y-coordinate for selector placement
     */
    public VSModeSelector(int x, int y) {
        this.x = x;
        this.y = y;
        this.audio = new AudioController();
        this.currentModeIndex = 0; // Default to PVP
        this.hoverSoundPlayed = false;
        loadImages();

        // Create bounds for arrow click detection
        int arrowWidth = arrowImages[0].getWidth();
        int arrowHeight = arrowImages[0].getHeight();
        int selectorWidth = modeImages[0].getWidth();

        leftArrowBounds = new Rectangle(x - 10 - arrowWidth, y + 20, arrowWidth, arrowHeight);
        rightArrowBounds = new Rectangle(x + selectorWidth + 10, y + 20, arrowWidth, arrowHeight);
    }

    /**
     * Loads the selector images from resources.
     */
    private void loadImages() {
        try {
            // Load the background/frame for the selector
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("")));

            // Load the mode images
            modeImages = new BufferedImage[3];
            modeImages[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("")));
            modeImages[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("")));
            modeImages[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("")));

            // Load the arrow images (normal and hover states)
            arrowImages = new BufferedImage[4];
            arrowImages[0] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("")));
            arrowImages[1] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("")));
            arrowImages[2] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("")));
            arrowImages[3] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("")));

        } catch (IOException e) {
            throw new RuntimeException("Failed to load VS mode selector images.");
        }
    }

    /**
     * Updates the selector state based on user interaction.
     */
    public void update() {
        // Reset hover sound if no arrows are being hovered
        if (!leftArrowHover && !rightArrowHover) {
            hoverSoundPlayed = false;
        }

        // Play hover sound if hovering over arrows
        if ((leftArrowHover || rightArrowHover) && !hoverSoundPlayed) {
            audio.playEffect(AudioController.MENU);
            hoverSoundPlayed = true;
        }
    }

    /**
     * Draws the VS mode selector.
     *
     * @param g Graphics context
     */
    @Override
    public void draw(Graphics g) {
        // Draw background/frame
        g.drawImage(background, x, y, null);

        // Draw current mode image
        g.drawImage(modeImages[currentModeIndex], x + 10, y + 10, null);

        // Draw arrows with appropriate hover state
        int leftArrowIndex = leftArrowHover ? 1 : 0;
        int rightArrowIndex = rightArrowHover ? 3 : 2;

        g.drawImage(arrowImages[leftArrowIndex], leftArrowBounds.x, leftArrowBounds.y, null);
        g.drawImage(arrowImages[rightArrowIndex], rightArrowBounds.x, rightArrowBounds.y, null);
    }

    /**
     * Checks if the mouse is over the left arrow.
     *
     * @param e MouseEvent to check
     * @return true if mouse is over left arrow
     */
    public boolean isInLeftArrow(MouseEvent e) {
        return leftArrowBounds.contains(e.getPoint());
    }

    /**
     * Checks if the mouse is over the right arrow.
     *
     * @param e MouseEvent to check
     * @return true if mouse is over right arrow
     */
    public boolean isInRightArrow(MouseEvent e) {
        return rightArrowBounds.contains(e.getPoint());
    }

    /**
     * Sets the hover state for the left arrow.
     *
     * @param hover true if mouse is hovering over left arrow
     */
    public void setLeftArrowHover(boolean hover) {
        this.leftArrowHover = hover;
    }

    /**
     * Sets the hover state for the right arrow.
     *
     * @param hover true if mouse is hovering over right arrow
     */
    public void setRightArrowHover(boolean hover) {
        this.rightArrowHover = hover;
    }

    /**
     * Sets the pressed state for the left arrow.
     *
     * @param pressed true if left arrow is being pressed
     */
    public void setLeftArrowPressed(boolean pressed) {
        this.leftArrowPressed = pressed;
    }

    /**
     * Sets the pressed state for the right arrow.
     *
     * @param pressed true if right arrow is being pressed
     */
    public void setRightArrowPressed(boolean pressed) {
        this.rightArrowPressed = pressed;
    }

    /**
     * Handles selection of the previous game mode.
     */
    public void previousMode() {
        if (leftArrowPressed) {
            currentModeIndex = (currentModeIndex - 1 + modes.length) % modes.length;
            audio.playEffect(AudioController.MENU);
            leftArrowPressed = false;
        }
    }

    /**
     * Handles selection of the next game mode.
     */
    public void nextMode() {
        if (rightArrowPressed) {
            currentModeIndex = (currentModeIndex + 1) % modes.length;
            audio.playEffect(AudioController.MENU);
            rightArrowPressed = false;
        }
    }

    /**
     * Gets the currently selected game mode.
     *
     * @return The current GameMode
     */
    public VSMode getCurrentMode() {
        return modes[currentModeIndex];
    }
}