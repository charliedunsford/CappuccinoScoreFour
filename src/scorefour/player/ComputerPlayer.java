package scorefour.player;

import scorefour.common.BeadColour;
import scorefour.controller.WinManager;
import scorefour.model.Board;
import scorefour.model.Peg;

import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player {

    private final Peg[][] pegs;
    Board board;

    public ComputerPlayer(BeadColour colour, Board board)
    {
        super(colour);
        this.board=board;
        pegs = board.getPegs();
    }

    public int[] getMove() {
        Random rand = new Random();

        ArrayList<int[]> ValidMoves = getValidMoves();
        ArrayList<int[]> WinningMoves = getWinningMoves();
        int randomPlace = rand.nextInt(ValidMoves.size());
        //return ValidMoves.get(randomPlace);

        if(!WinningMoves.isEmpty())
        {
            return WinningMoves.getFirst();
        }
        else
        {
            return ValidMoves.get(randomPlace);
        }
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

    public ArrayList<int[]> getWinningMoves ()
    {
        ArrayList<int[]> ValidMoves = getValidMoves();

        ArrayList<int[]> WinningMoves = new ArrayList<>();

        for (int[] validMove : ValidMoves) {
            board.addBead(validMove, super.getColour());

            if (new WinManager(board).isGameWon()) {
                WinningMoves.add(validMove);
                board.removeBead(validMove);
            } else {
                board.removeBead(validMove);
            }

        }
        return WinningMoves;
    }



}
