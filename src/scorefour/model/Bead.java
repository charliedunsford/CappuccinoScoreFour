package scorefour.model;

import scorefour.common.BeadColour;

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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}