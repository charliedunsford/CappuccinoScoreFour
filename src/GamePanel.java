import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {

    private Thread panelThread;
    private final GameBoard gameBoard;

    public GamePanel() {
        this.gameBoard = new GameBoard();
        this.setPreferredSize(new Dimension(500,500));
    }

    public void startPanelThread() {
        panelThread = new Thread(this);
        panelThread.start();
    }

    @Override
    public void run() {

        int FPS = 60;
        double drawInterval =  (double) 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (panelThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        gameBoard.update();
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        gameBoard.draw(g2d);

        g2d.dispose();
    }
}