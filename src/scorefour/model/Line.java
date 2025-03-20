package scorefour.model;

public class Line {

    private final Bead[] beads;
    private final String lineType;
    private final String identifier;

    public Line(Bead bead1, Bead bead2, Bead bead3, Bead bead4, String lineType, int row, int col) {
        beads = new Bead[]{bead1, bead2, bead3, bead4};
        this.lineType = lineType;
        this.identifier = parsePosition(row, col);
    }

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

    public String parsePosition(int row, int col) {
        String rowString = (row >= 0) ? String.valueOf((char)('A' + row)) : "";
        String colString = (col >= 0) ? String.valueOf(col + 1) : "";
        return rowString + colString;
    }

    @Override
    public String toString() {
        return lineType + " " + identifier;
    }
}
