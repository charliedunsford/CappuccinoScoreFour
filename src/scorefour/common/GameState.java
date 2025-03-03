package scorefour.common;

/**
 * {@code GameState} allows the game to gracefully switch between various modes.
 */
public enum GameState {
    MENU, PLAYING, QUIT;

    /**
     * A variable where the state of the game can be changed.
     */
    public static GameState state;
}