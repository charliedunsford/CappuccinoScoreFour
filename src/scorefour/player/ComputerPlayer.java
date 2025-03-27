package scorefour.player;

import scorefour.common.BeadColour;
import scorefour.controller.WinManager;
import scorefour.model.Board;
import scorefour.model.Peg;

import java.util.ArrayList;
import java.util.Random;

public class ComputerPlayer extends Player {

    private final Peg[][] pegs;
    private final Board board;

    public ComputerPlayer(BeadColour colour, Board board)
    {
        super(colour);
        this.board=board;
        pegs = board.getPegs();
    }

    public int[] getMove() {
        Random rand = new Random();

        ArrayList<int[]> validMoves = getValidMoves();
        ArrayList<int[]> winningMoves = getWinningMoves();

        if(!winningMoves.isEmpty())
        {
            return winningMoves.getFirst();
        }

        return validMoves.get(rand.nextInt(validMoves.size()));
    }

    public ArrayList<int[]> getValidMoves ()
    {
        ArrayList<int[]> validMoves = new ArrayList<>();
        for (int r=0; r<4; r++)
        {
            for(int c=0; c<4; c++)
            {
                if(pegs[r][c].getBeads()[3]==null)
                {
                    validMoves.add(new int[]{r,c});
                }

            }
        }
        return validMoves;
    }

    public ArrayList<int[]> getWinningMoves () {
        ArrayList<int[]> validMoves = getValidMoves();
        ArrayList<int[]> winningMoves = new ArrayList<>();

        for (int[] move : validMoves) {
            Board simulatedBoard = new Board(board);
            BeadColour opponentColour = (getColour() == BeadColour.WHITE) ? BeadColour.BLACK : BeadColour.WHITE;
            simulatedBoard.addBead(move, opponentColour);

            if (new WinManager(simulatedBoard).isGameWon()) {
                winningMoves.add(move);
            }
        }

        for (int[] move : validMoves) {
            Board simulatedBoard = new Board(board);
            simulatedBoard.addBead(move, getColour());

            if (new WinManager(simulatedBoard).isGameWon()) {
                winningMoves.add(move);
            }
        }

        if (winningMoves.isEmpty()) {
            for (int firstMove = 0; firstMove < validMoves.size(); firstMove++) {
                for (int secondMove = firstMove + 1; secondMove < validMoves.size(); secondMove++) {
                    Board simulatedBoard = new Board(board);
                    simulatedBoard.addBead(validMoves.get(firstMove), getColour());
                    simulatedBoard.addBead(validMoves.get(secondMove), getColour());

                    if (new WinManager(simulatedBoard).isGameWon()) {
                        winningMoves.add(validMoves.get(firstMove));
                        break;
                    }
                }

                if (!winningMoves.isEmpty()) {
                    break;
                }
            }
        }

        if (winningMoves.isEmpty()) {
            for (int firstMove = 0; firstMove < validMoves.size(); firstMove++) {
                for (int secondMove = firstMove + 1; secondMove < validMoves.size(); secondMove++) {
                    for (int thirdMove = secondMove + 1; thirdMove < validMoves.size(); thirdMove++) {
                        Board simulatedBoard = new Board(board);
                        simulatedBoard.addBead(validMoves.get(firstMove), getColour());
                        simulatedBoard.addBead(validMoves.get(secondMove), getColour());
                        simulatedBoard.addBead(validMoves.get(thirdMove), getColour());

                        if (new WinManager(simulatedBoard).isGameWon()) {
                            winningMoves.add(validMoves.get(firstMove));
                            break;
                        }
                    }
                }

                if (!winningMoves.isEmpty()) {
                    break;
                }
            }
        }

        return winningMoves;

    }
}
