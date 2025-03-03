package scorefour.common;

import java.awt.*;

/**
 * Classes which render images to a {@link Panel} are considered {@code Viewable}.
 */
public interface Viewable {

    /**
     * The {@code draw} method is accessed by the {@code Panel} class to be drawn on the {@code Frame}
     *
     * @param g the {@link Graphics} context used for rendering
     */
    void draw(Graphics g);

}
