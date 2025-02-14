package scorefour.core;

import scorefour.common.BeadColour;
import scorefour.common.Drawable;
import scorefour.common.Interactable;
import scorefour.objects.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class PlayingGame extends State implements Interactable, Drawable {

    private BufferedImage background;
    private BufferedImage overlay;
    private Board board;

    public PlayingGame(Game game) {
        super(game);
        initializeClasses();

        try {
            overlay = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/overlay.png")));
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/playingBackground.png")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load in game images.");
        }
    }

    public void initializeClasses() {
        this.board = new Board();
    }

    @Override
    public void update() {
        board.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(overlay, 0, 0, null);
        board.draw(g);
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
