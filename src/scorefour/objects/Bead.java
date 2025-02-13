package scorefour.objects;

import scorefour.common.BeadColour;
import scorefour.common.Drawable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bead implements Drawable {

    private final BeadColour colour;
    private BufferedImage bead;
    private int x, y;

    public Bead(BeadColour colour) {
        this.x = 0;
        this.colour = colour;
        initializeColour();
    }

    private void initializeColour() {
        try {
            if (colour == BeadColour.BLACK) {
                bead = ImageIO.read(new File("res/blackbead.png"));
            } else if (colour == BeadColour.WHITE) {
                System.out.println("NO IMAGE YET");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load bead image.");
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bead, x, y, null);
    }

    @Override
    public void update() {
        if (x < 800) {
            x += 6;
        } else {
            x = -50;
        }
        y = 200;
    }
}
