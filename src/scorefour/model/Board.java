package scorefour.model;

import scorefour.common.BeadColour;

public class Board {

    private final Peg[][] pegs;

    public Board() {
        this.pegs = new Peg[4][4];
        initializePegs();
    }

    private void initializePegs() {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                int y = 180 + row * 61;
                pegs[row][col] = new Peg(y);
            }
        }
    }

    public void addBead(int[] position, BeadColour colour) {
        pegs[position[0]][position[1]].addBead(colour);
    }

    public void removeBead(int[] position) {
        pegs[position[0]][position[1]].removeBead();
    }

    public Peg[][] getPegs() {
        return pegs;
    }
}
