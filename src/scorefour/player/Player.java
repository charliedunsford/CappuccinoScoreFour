package scorefour.player;

import scorefour.common.BeadColour;

public class Player {

    private final BeadColour colour;

    public Player(BeadColour colour) {
        this.colour = colour;
    }

    public BeadColour getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return colour == BeadColour.WHITE ? "White" : "Black";
    }
}
