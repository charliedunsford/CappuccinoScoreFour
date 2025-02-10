import java.awt.*;

public class Board {

    private int x, y;

    public Board() {
        this.x = -50;
    }

    // For GUI
    public void update() {
        // Updates GUI
        if (x < 800) {
            x += 4;
        } else {
            x = -50;
        }
        y = 200;
    }

    // For GUI
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(x, y, 50,50);
    }
}
