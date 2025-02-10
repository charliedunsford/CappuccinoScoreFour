import java.awt.*;
import javax.swing.*;

public class Panel extends JPanel {

    private Board board;

    public Panel(Board board) {
        this.board = board;
        setPreferredSize(new Dimension(800,500));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        board.draw(g2d);
    }
}