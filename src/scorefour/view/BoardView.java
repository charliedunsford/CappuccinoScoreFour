package scorefour.view;

import scorefour.common.Viewable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * {@code BoardView} initializes a fixed visual board and provides a way to {@code draw} these graphics to a {@link Panel}.
 */
public class BoardView implements Viewable {

    private BufferedImage board;

    /**
     * Constructs a {@code BoardView} which initializes the board image
     *<p>
     * To render this view, call the {@code draw} method with a {@link Graphics} object.
     */
    public BoardView() {
        loadBoardImage();
    }

    private void loadBoardImage() {
        try {
            board =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/board.png")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load board image.");
        }
    }

    /**
     * The {@code draw} method is accessed by a {@link Panel} class to be drawn on a {@link Frame}
     *
     * @param g the {@link Graphics} context used for rendering
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(board, 0, 0, null);
    }
}
