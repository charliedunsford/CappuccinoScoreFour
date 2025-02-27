package scorefour.core;

import scorefour.common.Drawable;
import scorefour.common.Interactable;
import scorefour.view.Board;
import scorefour.view.Overlay;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Playing extends State implements Drawable, Interactable {

    private BufferedImage background;
    private Overlay overlay;
    private Board board;

    public Playing(Game game) {
        super(game);
        initializeBackground();
        initializeClasses();
    }

    public void initializeBackground() {
        try {
            background = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/playingBackground.png")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load in game images.");
        }
    }

    public void initializeClasses() {
        this.overlay = new Overlay();
        this.board = new Board();
    }

    @Override
    public void update() {
        overlay.update();
        board.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        board.draw(g);
        overlay.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        overlay.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        overlay.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        overlay.setMouseOver(overlay.isIn(e));
        overlay.mouseMoved(e);
    }
}
