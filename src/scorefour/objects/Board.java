package scorefour.objects;

import scorefour.common.BeadColour;
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
    private Bead blackBead;

    public Board() {
        this.board = initializeBoard();
        this.blackBead = new Bead(BeadColour.BLACK);
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
        blackBead.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(board, 0, 0, null);
        blackBead.draw(g);
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
