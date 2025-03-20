package scorefour.player;

import scorefour.common.BeadColour;
import scorefour.model.Peg;

public class ComputerPlayer extends Player {

    public ComputerPlayer(BeadColour colour) {
        super(colour);
    }

    public Peg[][] getMove() {
        return null;
    }
}
