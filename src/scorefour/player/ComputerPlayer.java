package scorefour.player;

import scorefour.common.BeadColour;
import scorefour.controller.WinManager;
import scorefour.model.Board;
import scorefour.model.Peg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * The {@link ComputerPlayer} is an artificial player that attempts to win the score four game by simulating
 * an instance of {@link Board} to find ways to place beads so that it can win.
 */
public class ComputerPlayer extends Player {

    private final Peg[][] pegs;
    private final Board board;

    /**
     * Constructs a new {@link ComputerPlayer}.
     *
     * @param colour the {@link BeadColour} to play
     * @param board the {@link Board} for it to simulate
     */
    public ComputerPlayer(BeadColour colour, Board board)
    {
        super(colour);
        this.board=board;
        pegs = board.getPegs();
    }

    /**
     * Returns the best move the {@link ComputerPlayer} generates.
     *
     * @return {@code int[]} representing a peg
     */
    public int[] getMove() {

        List<int[]> validMoves = getValidMoves();
        List<int[]> bestMoves = getBestMoves(validMoves);

        if(!bestMoves.isEmpty())
        {
            Collections.shuffle(bestMoves);
            return bestMoves.getFirst();
        }

        return validMoves.get(new Random().nextInt(validMoves.size()));
    }

    // Generates a list of valid moves.
    private List<int[]> getValidMoves ()
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

    // Generates the best move.
    private List<int[]> getBestMoves(List<int[]> validMoves) {
        BeadColour opponentColour = (getColour() == BeadColour.WHITE) ? BeadColour.BLACK : BeadColour.WHITE;
        List<int[]> bestMoves = new ArrayList<>();

        // Makes win move
        for (int[] firstMove : validMoves) {
            Board simulatedBoard = new Board(board);
            simulatedBoard.addBead(firstMove, getColour());

            if (new WinManager(simulatedBoard).isGameWon()) {
                bestMoves.add(firstMove);
                return bestMoves;
            }
        }

        // Blocks opponent win move
        for (int[] opponentFirstMove : validMoves) {
            Board simulatedBoard = new Board(board);
            simulatedBoard.addBead(opponentFirstMove, opponentColour);

            if (new WinManager(simulatedBoard).isGameWon()) {
                bestMoves.add(opponentFirstMove);
                return bestMoves;
            }
        }

        // Sets up win move
        for (int firstMove = 0; firstMove < validMoves.size(); firstMove++) {
            for (int secondMove = firstMove + 1; secondMove < validMoves.size(); secondMove++) {
                Board simulatedBoard = new Board(board);
                simulatedBoard.addBead(validMoves.get(firstMove), getColour());
                simulatedBoard.addBead(validMoves.get(secondMove), getColour());

                if (new WinManager(simulatedBoard).isGameWon()) {
                    bestMoves.add(validMoves.get(firstMove));
                    return bestMoves;
                }
            }
        }

        // Sets up win move two moves ahead
        for (int firstMove = 0; firstMove < validMoves.size(); firstMove++) {
            for (int secondMove = firstMove + 1; secondMove < validMoves.size(); secondMove++) {
                for (int thirdMove = secondMove + 1; thirdMove < validMoves.size(); thirdMove++) {
                    Board simulatedBoard = new Board(board);
                    simulatedBoard.addBead(validMoves.get(firstMove), getColour());
                    simulatedBoard.addBead(validMoves.get(secondMove), getColour());
                    simulatedBoard.addBead(validMoves.get(thirdMove), getColour());

                    if (new WinManager(simulatedBoard).isGameWon()) {
                        bestMoves.add(validMoves.get(firstMove));
                        return bestMoves;
                    }
                }
            }
        }

        return bestMoves;
    }
}
