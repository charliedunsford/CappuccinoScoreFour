package scorefour.player;

import scorefour.common.BeadColour;
import scorefour.model.Board;
import scorefour.model.Peg;

import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player {

    private final Peg[][] pegs;

    public ComputerPlayer(BeadColour colour, Board board)
    {
        super(colour);
        pegs = board.getPegs();
    }

    public int[] getMove() {
        Random rand = new Random();

        ArrayList<int[]> ValidMoves = getValidMoves();
        int randomPlace = rand.nextInt(ValidMoves.size());
        return ValidMoves.get(randomPlace);

    }

    public ArrayList<int[]> getValidMoves ()
    {
        ArrayList<int[]> ValidMoves = new ArrayList<>();
        for (int r=0; r<4; r++)
        {
            for(int c=0; c<4; c++)
            {
                int[] position = new int[]{r,c};
                if(pegs[r][c].getBeads()[3]==null)
                {
                    ValidMoves.add(position);
                }

            }
        }
        return  ValidMoves;
    }

}
