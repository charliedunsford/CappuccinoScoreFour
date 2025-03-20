package scorefour.controller;

import scorefour.model.Board;
import scorefour.model.Line;
import scorefour.model.Peg;

public class WinManager {

    private Line[] lines;
    private final Peg[][] pegs;

    public WinManager(Board board) {
        pegs = board.getPegs();
    }

    public boolean isGameWon() {
        addLines();

        for (Line line : lines) {
            if (line.isWin()) {
                System.out.println("Win on " + line);
                return true;
            }
        }
        return false;

    }

    private void addLines() {
        lines = new Line[76];
        int currentLine = 0;

        // Vertical lines (16)
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                this.lines[currentLine++] = new Line(pegs[row][col], row, col);
            }
        }

        // Horizontal across rows (16)
        for (int height = 0; height < 4; height++) {
            for (int row = 0; row < 4; row++) {
                lines[currentLine++] = new Line(
                        pegs[row][0].getBeads()[height],
                        pegs[row][1].getBeads()[height],
                        pegs[row][2].getBeads()[height],
                        pegs[row][3].getBeads()[height],
                        "row", row, -1);
            }
        }

        // Horizontal across columns (16)
        for (int height = 0; height < 4; height++) {
            for (int col = 0; col < 4; col++) {
                lines[currentLine++] = new Line(
                        pegs[0][col].getBeads()[height],
                        pegs[1][col].getBeads()[height],
                        pegs[2][col].getBeads()[height],
                        pegs[3][col].getBeads()[height],
                        "column", -1, col);
            }
        }

        // Diagonal across board/single height lines (8)
        for (int height = 0; height < 4; height++) {
            lines[currentLine++] = new Line(
                    pegs[0][0].getBeads()[height],
                    pegs[1][1].getBeads()[height],
                    pegs[2][2].getBeads()[height],
                    pegs[3][3].getBeads()[height],
                    "diagonal across board", -1, -1);

            lines[currentLine++] = new Line(
                    pegs[3][0].getBeads()[height],
                    pegs[2][1].getBeads()[height],
                    pegs[1][2].getBeads()[height],
                    pegs[0][3].getBeads()[height],
                    "diagonal across board", -1, -1);
        }

        // Diagonal column lines (8)
        for (int col = 0; col < 4; col++) {
            lines[currentLine++] = new Line(
                    pegs[0][col].getBeads()[0],
                    pegs[1][col].getBeads()[1],
                    pegs[2][col].getBeads()[2],
                    pegs[3][col].getBeads()[3],
                    "diagonal column", -1, col);

            lines[currentLine++] = new Line(
                    pegs[0][col].getBeads()[3],
                    pegs[1][col].getBeads()[2],
                    pegs[2][col].getBeads()[1],
                    pegs[3][col].getBeads()[0],
                    "diagonal column", -1, col);
        }

        // Diagonal row lines (8)
        for (int row = 0; row < 4; row++) {
            lines[currentLine++] = new Line(
                    pegs[row][0].getBeads()[0],
                    pegs[row][1].getBeads()[1],
                    pegs[row][2].getBeads()[2],
                    pegs[row][3].getBeads()[3],
                    "diagonal row", row, -1);

            lines[currentLine++] = new Line(
                    pegs[row][3].getBeads()[0],
                    pegs[row][2].getBeads()[1],
                    pegs[row][1].getBeads()[2],
                    pegs[row][0].getBeads()[3],
                    "diagonal row", row, -1);
        }

        // Diagonal across board/diagonal height lines (4)
        lines[currentLine++] = new Line(
                pegs[0][0].getBeads()[0],
                pegs[1][1].getBeads()[1],
                pegs[2][2].getBeads()[2],
                pegs[3][3].getBeads()[3],
                "cross 3D diagonal 1", -1, -1);

        lines[currentLine++] = new Line(
                pegs[3][0].getBeads()[0],
                pegs[2][1].getBeads()[1],
                pegs[1][2].getBeads()[2],
                pegs[0][3].getBeads()[3],
                "cross 3D diagonal 2", -1, -1);

        lines[currentLine++] = new Line(
                pegs[0][3].getBeads()[0],
                pegs[1][2].getBeads()[1],
                pegs[2][1].getBeads()[2],
                pegs[3][0].getBeads()[3],
                "cross 3D diagonal 3", -1, -1);

        lines[currentLine] = new Line(
                pegs[0][0].getBeads()[3],
                pegs[1][1].getBeads()[2],
                pegs[2][2].getBeads()[1],
                pegs[3][3].getBeads()[0],
                "cross 3D diagonal 4", -1, -1);
    }
}