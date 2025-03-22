package scorefour.view;

import scorefour.common.Viewable;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * {@link MenuView} initializes a fixed visual overlay and provides a way to {@code draw} these graphics to a {@link Panel}.
 */
public class MenuView implements Viewable {

    private ImageIcon menuBackground;

    /**
     * Constructs a {@code MenuView} which stores a menu background image.
     * <p>
     * To render this view, call the {@code draw} method with a {@link Graphics} object.
     */
    public MenuView() {
        loadBackgroundImage();
    }

    // Loads the gif which plays in the menu background.
    private void loadBackgroundImage() {
        try {
            menuBackground = new ImageIcon(Objects.requireNonNull(getClass().getResource("/res/menu.gif")));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load menu background.");
        }
    }

    /**
     * The {@code draw} method is accessed by a {@link Panel} class to be drawn on a {@link Frame}
     *
     * @param g the {@link Graphics} context used for rendering
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(menuBackground.getImage(),0,0,null);
    }
}
