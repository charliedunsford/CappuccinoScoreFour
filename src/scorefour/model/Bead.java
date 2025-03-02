package scorefour.model;

import scorefour.common.BeadColour;

// Models should contain the objects data and logic, provide getter and setters to update its state, and be independent of the GUI.
public class Bead {

    private final BeadColour colour;
    private int x, y;

    public Bead(BeadColour colour) {
        this.colour = colour;
        this.x = 0;
        this.y = 0;
    }

    public Object getColour() {
        return colour;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}