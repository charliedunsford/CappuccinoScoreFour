package scorefour.model;

import scorefour.common.BeadColour;

/**
 * A {@link Peg} contains 4 empty spaces for {@link Bead}'s which
 * can be added or removed.
 */
public class Peg {

    private final int y;
    private final Bead[] beads;

    /**
     * Constructs a new {@link Peg} at a specified {@code y} location.
     *
     * @param y location for the {@link Peg} on the {@link Board}.
     */
    public Peg(int y) {
        this.y = y;
        this.beads = new Bead[4];
    }

    /**
     * Constructs a copy of a {@link Peg} into a new peg.
     *
     * @param peg to be copied
     */
    public Peg(Peg peg) {
        this.beads = new Bead[4];

        for (int i = 0; i < 3; i++) {
            if (peg.beads[i] != null) {
                this.beads[i] = new Bead(peg.beads[i]);
            }
        }

        this.y = peg.y;
    }

    /**
     * Adds a {@link Bead} to the {@link Peg}.
     *
     * @param colour the colour of the bead
     */
    public void addBead(BeadColour colour) {
        for (int i = 0; i < beads.length; i++) {
            if (beads[i] == null) {
                beads[i] = new Bead(colour);
                return;
            }
        }
    }

    /**
     * Removes a {@link Bead} from the top position of the {@link Peg}.
     */
    public void removeBead() {
        for (int i = beads.length - 1; i >= 0; i--) {
            if (beads[i] != null) {
                beads[i] = null;
                return;
            }
        }
    }

    /**
     * Clears all {@link Bead}'s from the {@link Peg} from top to bottom.
     */
    public void clearBeads() {
        for (int i = beads.length - 1; i >= 0; i--) {
            beads[i] = null;
        }
    }

    /**
     * Checks whether the {@link Peg} contains any {@link Bead}'s.
     *
     * @return boolean
     */
    public boolean isFull() {
        return beads[beads.length - 1] != null;
    }

    /**
     * Gets an array of all the {@link Bead}'s on the {@link Peg}.
     *
     * @return an array of {@link Bead}'s
     */
    public Bead[] getBeads() {
        return beads;
    }

    /**
     * Returns the {@code y} location of the {@link Peg}.
     *
     * @return integer
     */
    public int getY() {
        return y;
    }
}
