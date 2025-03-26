package scorefour.player;

import scorefour.common.BeadColour;

/**
 *  {@link Player} of the game.
 */
public class Player {

    private final BeadColour colour;

    /**
     *  Constructs a new {@link Player}.
     *
     * @param colour the players bead colour
     */
    public Player(BeadColour colour) {
        this.colour = colour;
    }

    /**
     * Returns the {@link Player}'s bead colour.
     *
     * @return a {@link BeadColour}
     */
    public BeadColour getColour() {
        return colour;
    }

    /**
     * Returns a {@link Player}'s string identifier.
     *
     * @return String
     */
    @Override
    public String toString() {
        return colour == BeadColour.WHITE ? "White" : "Black";
    }
}
