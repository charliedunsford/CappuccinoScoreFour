package scorefour.controller;

import scorefour.model.Board;
import scorefour.model.Line;
import scorefour.model.Peg;

/**
 * A {@link WinManager} checks potential lines of the {@link Board} for wins.
 */
public class WinManager {

    private Line[] lines;
    private final Peg[][] pegs;

    /**
     * Constructs a new {@link WinManager}.
     *
     * @param board the {@link Board} to check for potential win states.
     */
    public WinManager(Board board) {
        pegs = board.getPegs();
    }

    /**
     * Checks whether the game has been won.
     *
     * @return true if game has been won; false otherwise.
     */
    public boolean isGameWon() {
        checkLines();

        for (Line line : lines) {
            if (line.isWin()) {
                return true;
            }
        }
        return false;

    }

    // Checks all lines of the game for a win.
    private void checkLines() {
        lines = new Line[76];
        int currentLine = 0;

        // Vertical lines (16)
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                this.lines[currentLine++] = new Line(pegs[row][col]);
            }
        }

        // Horizontal across rows (16)
        for (int height = 0; height < 4; height++) {
            for (int row = 0; row < 4; row++) {
                lines[currentLine++] = new Line(
                        pegs[row][0].getBeads()[height],
                        pegs[row][1].getBeads()[height],
                        pegs[row][2].getBeads()[height],
                        pegs[row][3].getBeads()[height]);
            }
        }

        // Horizontal across columns (16)
        for (int height = 0; height < 4; height++) {
            for (int col = 0; col < 4; col++) {
                lines[currentLine++] = new Line(
                        pegs[0][col].getBeads()[height],
                        pegs[1][col].getBeads()[height],
                        pegs[2][col].getBeads()[height],
                        pegs[3][col].getBeads()[height]);
            }
        }

        // Diagonal across board/single height lines (8)
        for (int height = 0; height < 4; height++) {
            lines[currentLine++] = new Line(
                    pegs[0][0].getBeads()[height],
                    pegs[1][1].getBeads()[height],
                    pegs[2][2].getBeads()[height],
                    pegs[3][3].getBeads()[height]);

            lines[currentLine++] = new Line(
                    pegs[3][0].getBeads()[height],
                    pegs[2][1].getBeads()[height],
                    pegs[1][2].getBeads()[height],
                    pegs[0][3].getBeads()[height]);
        }

        // Diagonal column lines (8)
        for (int col = 0; col < 4; col++) {
            lines[currentLine++] = new Line(
                    pegs[0][col].getBeads()[0],
                    pegs[1][col].getBeads()[1],
                    pegs[2][col].getBeads()[2],
                    pegs[3][col].getBeads()[3]);

            lines[currentLine++] = new Line(
                    pegs[0][col].getBeads()[3],
                    pegs[1][col].getBeads()[2],
                    pegs[2][col].getBeads()[1],
                    pegs[3][col].getBeads()[0]);
        }

        // Diagonal row lines (8)
        for (int row = 0; row < 4; row++) {
            lines[currentLine++] = new Line(
                    pegs[row][0].getBeads()[0],
                    pegs[row][1].getBeads()[1],
                    pegs[row][2].getBeads()[2],
                    pegs[row][3].getBeads()[3]);

            lines[currentLine++] = new Line(
                    pegs[row][3].getBeads()[0],
                    pegs[row][2].getBeads()[1],
                    pegs[row][1].getBeads()[2],
                    pegs[row][0].getBeads()[3]);
        }

        // Diagonal across board/diagonal height lines (4)
        lines[currentLine++] = new Line(
                pegs[0][0].getBeads()[0],
                pegs[1][1].getBeads()[1],
                pegs[2][2].getBeads()[2],
                pegs[3][3].getBeads()[3]);

        lines[currentLine++] = new Line(
                pegs[3][0].getBeads()[0],
                pegs[2][1].getBeads()[1],
                pegs[1][2].getBeads()[2],
                pegs[0][3].getBeads()[3]);

        lines[currentLine++] = new Line(
                pegs[0][3].getBeads()[0],
                pegs[1][2].getBeads()[1],
                pegs[2][1].getBeads()[2],
                pegs[3][0].getBeads()[3]);

        lines[currentLine] = new Line(
                pegs[0][0].getBeads()[3],
                pegs[1][1].getBeads()[2],
                pegs[2][2].getBeads()[1],
                pegs[3][3].getBeads()[0]);
    }
}