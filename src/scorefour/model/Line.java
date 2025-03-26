package scorefour.model;

/**
 * A {@link Line} represents a potential row of {@link Bead}'s on the {@link Board}.
 */
public class Line {

    private final Bead[] beads;
    private final String lineType;
    private final String identifier;

    /**
     * Constructs a {@link Line}.
     *
     * @param bead1 potential bead location
     * @param bead2 potential bead location
     * @param bead3 potential bead location
     * @param bead4 potential bead location
     * @param lineType type of line
     * @param row lines row
     * @param col lines column
     */
    public Line(Bead bead1, Bead bead2, Bead bead3, Bead bead4, String lineType, int row, int col) {
        beads = new Bead[]{bead1, bead2, bead3, bead4};
        this.lineType = lineType;
        this.identifier = parsePosition(row, col);
    }

    /**
     * Constructs a {@link Line}.
     *
     * @param peg potential peg which contains a line
     * @param row lines row
     * @param col lines column
     */
    public Line(Peg peg, int row, int col) {
        beads = peg.getBeads();
        this.lineType = "peg";
        this.identifier = parsePosition(row, col);
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

    // Parses the integer location into a legible format.
    private String parsePosition(int row, int col) {
        String rowString = (row >= 0) ? String.valueOf((char)('A' + row)) : "";
        String colString = (col >= 0) ? String.valueOf(col + 1) : "";
        return rowString + colString;
    }

    /**
     * Returns a string of the {@link Line} type and identifier.
     *
     * @return String
     */
    @Override
    public String toString() {
        return lineType + " " + identifier;
    }
}
