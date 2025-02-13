package scorefour.core;

import scorefour.common.BeadColour;
import scorefour.common.Interactable;
import scorefour.objects.Bead;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameInstance extends State implements Interactable {

    private BufferedImage overlay;
    private Bead blackBead;

    public GameInstance(Game game) {
        super(game);
        initializeClasses();

        try {
            overlay = ImageIO.read(new File("res/overlay.png"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load overlay image: res/overlay.png");
        }
    }

    public void initializeClasses() {
        this.blackBead = new Bead(BeadColour.BLACK);
    }

    @Override
    public void update() {
        blackBead.update();
        // Track score etc
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(overlay, 0, 0, null);
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
