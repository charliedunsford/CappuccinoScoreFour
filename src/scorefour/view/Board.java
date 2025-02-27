package scorefour.view;

import scorefour.common.Drawable;
import scorefour.common.Interactable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Board implements Drawable, Interactable {
    private BufferedImage board;
    public Board() {
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
    public void update() {
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(board, 0, 0, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
