package scorefour.model;

import scorefour.common.BeadColour;

/**
 * A {@link Board} is where the main interaction with the game takes place.
 * <p>
 * Contains {@link Peg}'s.
 */
public class Board {

    private final Peg[][] pegs;

    /**
     * Constructs a new {@link Board} and initializes its {@link Peg}'s.
     */
    public Board() {
        this.pegs = new Peg[4][4];
        initializePegs();
    }

    /**
     * Constructs a new copy of a {@link Board}
     *
     * @param board the board to be copied
     */
    public Board(Board board) {
        this.pegs = new Peg[4][4];

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                this.pegs[row][col] = new Peg(board.pegs[row][col]);
            }
        }
    }

    // Populates each peg in a set location on the board. (LOCATION CRUCIAL FOR GUI!)
    private void initializePegs() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                int y = 180 + row * 61; // for bead use!
                pegs[row][col] = new Peg(y);
            }
        }
    }

    /**
     * Adds a {@link Bead} to a specified {@link Peg}.
     *
     * @param position the {@link Peg} position for the {@link Bead} to be added to.
     * @param colour the {@link BeadColour} for the {@link Bead} to be.
     */
    public void addBead(int[] position, BeadColour colour) {
        pegs[position[0]][position[1]].addBead(colour);
    }

    /**
     * Removes a {@link Bead} from the top of the specified {@link Peg}.
     *
     * @param position location of the {@link Peg}.
     */
    public void removeBead(int[] position) {
        pegs[position[0]][position[1]].removeBead();
    }

    /**
     * Returns an array of all {@link Peg}'s on the {@link Board}.
     *
     * @return an array of {@link Peg}'s
     */
    public Peg[][] getPegs() {
        return pegs;
    }

    public Peg getPeg(int[] position) {
        return pegs[position[0]][position[1]];
    }

    /**
     * Clears all the {@link Peg}'s on the {@link Board}.
     */
    public void clearBoard() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                pegs[row][col].clearBeads();
            }
        }
    }

    /**
     * Constructs a {@code String} representation of the {@link Board} using
     * {@link StringBuilder}.
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        char[] rows = {'A', 'B', 'C', 'D'};

        for (int row = 0; row < pegs.length; row++) {
            for (int col = 0; col < pegs[row].length; col++) {
                stringBuilder.append(rows[row]).append(col + 1).append(": ");

                for (Bead bead : pegs[row][col].getBeads()) {
                    if (bead != null) {
                        BeadColour colour = bead.getColour();
                        stringBuilder.append(colour == BeadColour.WHITE ? "W" : "B");
                    }
                }

                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
