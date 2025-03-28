package scorefour.player;

import scorefour.common.BeadColour;

/**
 * Represents a {@link Player} who is human.
 */
public class HumanPlayer extends Player {

    /**
     * Constructs a new {@link HumanPlayer} for the game.
     *
     * @param colour the colour for the {@link Player}
     */
    public HumanPlayer(BeadColour colour) {
        super(colour);
    }
}
