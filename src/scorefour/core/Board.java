package scorefour.core;

import scorefour.interfaces.Drawable;

import java.awt.*;

public class Board implements Drawable {

    private int x, y;

    public Board() {
        this.x = 0;
    }

    // Updates every game loop (60FPS)
    public void update() {
        // Updates GUI
        if (x < 800) {
            x += 6;
        } else {
            x = -50;
        }
        y = 200;
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillOval(x, y, 50, 50);
    }

    @Override
    public String toString() {
        // Implement an ascii representation of the board to be used in Testing Mode.
        return "";
    }
}
