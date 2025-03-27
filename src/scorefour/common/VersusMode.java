package scorefour.common;

/**
 * {@link VersusMode} dictates what kinds of {@link scorefour.player.Player}'s will
 * take part in the game.
 */
public enum VersusMode {
    PVP, PVC, CVC;

    /**
     * Allows the modification and reading of {@link VersusMode}
     */
    public static VersusMode mode = PVP;

}
