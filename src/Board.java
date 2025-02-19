import java.awt.*;

public class Board {

    private int x, y;

    // For GUI
    public void update() {
        // Updates GUI
        x += 1;
        y = 200;
    }

    // For GUI
    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(x, y, 50,50);
    }
}
