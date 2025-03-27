package scorefour.model;

/**
 * A {@link Line} represents a potential row of {@link Bead}'s on the {@link Board}.
 */
public class Line {

    private final Bead[] beads;

    /**
     * Constructs a {@link Line}.
     *
     * @param bead1 potential bead location
     * @param bead2 potential bead location
     * @param bead3 potential bead location
     * @param bead4 potential bead location
     */
    public Line(Bead bead1, Bead bead2, Bead bead3, Bead bead4) {
        beads = new Bead[]{bead1, bead2, bead3, bead4};
    }

    /**
     * Constructs a {@link Line}.
     *
     * @param peg potential peg which contains a line
     */
    public Line(Peg peg) {
        beads = peg.getBeads();
    }

    public boolean isWin() // returns true if the line has 4 beads of the same colour
    {
        for(int i = 0; i <= 3; i++) {
            if (beads[i] == null) {
                return false;
            }
        }

        return beads[0].getColour() == beads[1].getColour() && beads[1].getColour() == beads[2].getColour() && beads[2].getColour() == beads[3].getColour();
    }
}
