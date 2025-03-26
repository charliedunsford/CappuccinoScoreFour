package scorefour.player;

import scorefour.common.BeadColour;
import scorefour.model.Board;
import scorefour.model.Peg;
import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player {

    Board board = new Board();
    private Peg[][] pegs;

    public ComputerPlayer(BeadColour colour)
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

    public ArrayList getValidMoves ()
    {
        ArrayList<int[]> ValidMoves = new ArrayList<int[]>();
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
