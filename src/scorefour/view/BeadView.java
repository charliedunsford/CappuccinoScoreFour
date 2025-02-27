package scorefour.view;

import scorefour.common.BeadColour;
import scorefour.common.Viewable;
import scorefour.model.Bead;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

// Views should draw the current state of the object (in our case every game loop)
public class BeadView implements Viewable {

    private final BeadColour colour;
    private final Bead bead;
    private BufferedImage beadSprite;

    public BeadView(Bead bead) {
        this.bead = bead;
        colour = (BeadColour) bead.getColour();
        initializeColour();
    }

    private void initializeColour() {
        try {
            if (colour == BeadColour.BLACK) {
                beadSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/blackbead.png")));
            } else if (colour == BeadColour.WHITE) {
                beadSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/whitebead.png")));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load bead image.");
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(beadSprite, bead.getX(), bead.getY(), null);
    }
}
