package scorefour.view;

import scorefour.common.BeadColour;
import scorefour.common.Viewable;
import scorefour.model.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

/**
 * {@link BoardView} initializes a fixed visual board and provides a way to {@code draw} these graphics to a {@link Panel}.
 */
public class BoardView implements Viewable {

    private final Board board;

    private BufferedImage boardImage;

    /**
     * Constructs a {@link BoardView} which initializes the board image.
     *<p>
     * To render this view, call the {@code draw} method with a {@link Graphics} object.
     */
    public BoardView(Board board) {
        this.board = board;
        loadBoardImage();
    }

    // Loads the image of the board to be displayed in game.
    private void loadBoardImage() {
        try {
            boardImage =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/board.png")));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load board image.");
        }
    }

    /**
     * The {@code draw} method is accessed by a {@link Panel} class to be drawn on a {@link Frame}
     *
     * @param g the {@link Graphics} context used for rendering
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(boardImage, 0, 0, null);
    }

    // An ASCII representation of the board.
    public void printASCIIBoard() { // Prints the ASCII view of the board to System.out

        // converts board to 3-dimensional char array where | = no bead, X = black and O = white

        char[][][] chars = new char[4][4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if(board.getPegs()[i][j].getBeads()[k] == null)
                    {
                        chars[i][j][k] = '|';
                    }
                    else if(board.getPegs()[i][j].getBeads()[k].getColour() == BeadColour.BLACK)
                    {
                        chars[i][j][k] = 'X';
                    }
                    else
                    {
                        chars[i][j][k] = 'O';
                    }
                }
            }
        }


        // prints the char array to something that looks like the board
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                System.out.print("\n");
                for (int i = row; i < 4; i++) {
                    System.out.print("  ");
                }

                for (int height = 0; height < 4; height++) {
                    System.out.print(chars[row][col][height] + "  ");

                }
            }
        }
        System.out.print("\n");
    }
}
