package scorefour.model;

import scorefour.common.BeadColour;

/**
 * A {@link Bead} is a game piece for a score four game.
 */
public class Bead {

    private final BeadColour colour;

    private int x, y;

    /**
     * Constructs a new {@link Bead} given a {@link BeadColour}.
     *
     * @param colour the {@link BeadColour}
     */
    public Bead(BeadColour colour) {
        this.colour = colour;
        this.x = 0;
        this.y = 0;
    }

    /**
     * Returns the {@link BeadColour} of the {@link Bead}.
     *
     * @return {@link BeadColour}
     */
    public BeadColour getColour() {
        return colour;
    }

    /**
     * Returns the {@code x} location of the {@link Bead}.
     *
     * @return integer
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the {@code x} location of the {@link Bead}.
     *
     * @param x integer
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the {@code y} location of the {@link Bead}.
     *
     * @return integer
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the {@code y} location of the {@link Bead}.
     *
     * @param y integer
     */
    public void setY(int y) {
        this.y = y;
    }
}