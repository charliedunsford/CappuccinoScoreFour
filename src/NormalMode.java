public class NormalMode extends GameMode {

    private PanelManager panelManager;
    private GameState gameState;
    private final Board board;

    public NormalMode() {
        super.gameMode = "NORMAL";
        this.board = new Board();
    }

    @Override
    public void setup() {
        panelManager = new PanelManager(board);
        Panel panel = panelManager.getPanel();
        gameState = new GameState(board, panel);
        gameState.start();
    }
}
