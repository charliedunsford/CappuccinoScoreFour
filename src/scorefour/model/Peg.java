package scorefour.model;

import scorefour.common.BeadColour;

public class Peg {

    private final int y;
    private final Bead[] beads;

    public Peg(int y) {
        this.y = y;
        this.beads = new Bead[4];
    }

    public void addBead(BeadColour colour) {
        for (int i = 0; i < beads.length; i++) {
            if (beads[i] == null) {
                beads[i] = new Bead(colour);
                return;
            }
        }
    }

    public void removeBead() {
        for (int i = beads.length - 1; i >= 0; i--) {
            if (beads[i] != null) {
                beads[i] = null;
                return;
            }
        }
    }

    public void clearBeads() {
        for (int i = beads.length - 1; i >= 0; i--) {
            beads[i] = null;
        }
    }

    public Bead[] getBeads() {
        return beads;
    }

    public int getY() {
        return y;
    }
}
