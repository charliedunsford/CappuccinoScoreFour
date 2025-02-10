public class GameState implements Runnable {

    private final Board board;
    private Panel panel;
    private volatile boolean running;

    public GameState(Board board, Panel panel) {
        this.board = board;
        this.panel = panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public void start() {
        running = true;
        new Thread(this).start();
    }

    public void stop() {
        running = false;
    }

    // This is the game loop
    @Override
    public void run() {
        final int FPS = 60;
        final double drawInterval = 1000000000.0 / FPS;
        long lastTime = System.nanoTime();
        double delta = 0;

        double time = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                updateGameState();

                if (panel != null) {
                    panel.repaint();
                }
                delta--;

                frames++;
                if (System.currentTimeMillis() - time >= 1000) {
                    // frames Count Here
                    frames = 0;
                    time += 1000;
                }
            }
        }
    }

    private void updateGameState() {
        board.update();
    }
}