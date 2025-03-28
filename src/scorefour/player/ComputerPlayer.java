package scorefour.player;

import scorefour.common.BeadColour;
import scorefour.controller.WinManager;
import scorefour.model.Board;
import scorefour.model.Peg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The {@link ComputerPlayer} is an artificial player that attempts to win the score four game by simulating
 * an instance of {@link Board} to find ways to place beads so that it can win.
 */
public class ComputerPlayer extends Player {

    private final Peg[][] pegs;
    private final Board board;
    private final Random random = new Random();

    /**
     * Constructs a new {@link ComputerPlayer}.
     *
     * @param colour the {@link BeadColour} to play
     * @param board the {@link Board} for it to simulate
     */
    public ComputerPlayer(BeadColour colour, Board board)
    {
        super(colour);
        this.board = board;
        pegs = board.getPegs();
    }

    /**
     * Returns the best move the {@link ComputerPlayer} generates.
     *
     * @return {@code int[]} representing a peg
     */
    public int[] getMove() {

        List<int[]> validMoves = getValidMoves();
        List<int[]> winningMoves = getWinningMoves(getColour());
        List<int[]> blockingMoves = getWinningMoves(getOpponentColour());

        if(!winningMoves.isEmpty()) {
            return winningMoves.getFirst();
        }

        if(!blockingMoves.isEmpty()) {
            return blockingMoves.getFirst();
        }

        return validMoves.get(random.nextInt(validMoves.size()));
    }

    // Creates a list of valid moves.
    private List<int[]> getValidMoves () {
        ArrayList<int[]> validMoves = new ArrayList<>();

        for (int r=0; r<4; r++) {
            for(int c=0; c<4; c++) {
                if(pegs[r][c].getBeads()[3]==null) {
                    validMoves.add(new int[]{r, c});
                }
            }
        }
        return  validMoves;
    }

    // Gets opponents bead colour.
    private BeadColour getOpponentColour() {
        return (getColour() == BeadColour.WHITE) ? BeadColour.BLACK : BeadColour.WHITE;
    }

    // Gets winning move for passed colour.
    private List<int[]> getWinningMoves (BeadColour colour) {
        List<int[]> validMoves = getValidMoves();
        List<int[]> winningMoves = new ArrayList<>();

        for (int[] validMove : validMoves) {
            board.addBead(validMove, colour);

            if (new WinManager(board).isGameWon()) {
                winningMoves.add(validMove);
                board.removeBead(validMove);
                break;
            }
            board.removeBead(validMove);
        }
        return winningMoves;
    }
}
