package scorefour.view;

import scorefour.common.Viewable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class BoardView implements Viewable {

    private BufferedImage board;

    public BoardView() {
        this.board = initializeBoard();
    }

    public BufferedImage initializeBoard() {
        try {
            board =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/board.png")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load board image.");
        }
        return board;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(board, 0, 0, null);
    }
}
