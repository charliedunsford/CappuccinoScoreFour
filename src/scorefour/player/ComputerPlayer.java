package scorefour.player;

import scorefour.common.BeadColour;

import java.util.Random;

public class ComputerPlayer extends Player {

    public ComputerPlayer(BeadColour colour) {
        super(colour);
    }

    public int[] getMove() {
        Random rand = new Random();

        int row = rand.nextInt(4);
        int col = rand.nextInt(4);

        return new int[]{row, col};
    }
}
