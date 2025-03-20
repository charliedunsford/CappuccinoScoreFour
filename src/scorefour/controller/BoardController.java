package scorefour.controller;

import scorefour.common.*;
import scorefour.model.Bead;
import scorefour.model.Board;
import scorefour.model.Peg;
import scorefour.view.BoardView;
import scorefour.view.Panel;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * {@code BoardController} manages a boards interactions, updates, and drawing.
 * <p>
 * It coordinates with a {@link Board}, {@link BoardView}, and various other classes to render and
 * control the game board.
 * <p>
 * This class pertains various logic for the {@link Peg} and {@link Bead} classes, as they relate to the
 * board.
 */
public class BoardController implements Interactable, Updatable {

    private final Board board;
    private final BoardView view;
    private final AudioController audioController;
    private final GameManager gameManager;

    private PegController[][] pegControllers;

    /**
     * Constructs a new {@link BoardController} object, which uses the {@link Board} and
     * {@link BoardView} to play and display the game play area.
     * <p>
     * This classes also initializes the {@link PegController}'s associated with the {@link Board}.
     *
     * @param board the {@link Board} where the game takes place
     * @param view the {@link BoardView} which renders the board
     */
    public BoardController(Board board, BoardView view, GameManager gameManager) {
        this.board = board;
        this.view = view;
        this.audioController = new AudioController();
        this.gameManager = gameManager;
        initializePegControllers();
    }

    private void initializePegControllers() {
        Peg[][] pegs = board.getPegs();
        int rows = pegs.length;
        int cols = pegs[0].length;

        pegControllers = new PegController[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                pegControllers[row][col] = new PegController(pegs[row][col], row, col, audioController, gameManager);
            }
        }
    }

    /**
     * Adds a {@link Bead} to the designated {@link Peg}
     *
     * @param position a {@code String} representing a {@link Peg}
     * @param colour a {@link BeadColour} for the added {@link Bead}
     */
    public void addBead(String position, BeadColour colour) {
        int[] parsedPosition = parsePosition(position);
        board.addBead(parsedPosition, colour);
        System.out.println("added " + colour + " bead to " + position);
    }

    /**
     * Removes a {@link Bead} from a designated {@link Peg}
     *
     * @param position a {@code String} representing a {@link Peg}
     */
    public void removeBead(String position) {
        int[] parsedPosition = parsePosition(position);
        board.removeBead(parsedPosition);
        System.out.println("removed bead from " + position);
    }

    /**
     * Clears all the {@link Peg}'s on the {@link Board}.
     */
    public void clearBoard() {
        board.clearBoard();
    }

    /**
     * Parses a {@code String} representing a {@link Peg} position on a board into
     * a integer array format to be used by the program.
     *
     * @param position a {@code String} representing a {@link Peg} position
     * @return an integer array of a char (A-D) and int (1-4)
     */
    private int[] parsePosition(String position) {
        // Stores the first character as row and second integer as col.
        char row = position.charAt(0);
        int col = Character.getNumericValue(position.charAt(1));

        // Returns the integer array of the parsed string.
        return new int[]{row - 'A', col - 1};
    }

    /**
     * Draws the {@link BoardView} and {@link PegController}.
     *
     * @param g the {@link Graphics} context used for rendering
     */
    public void draw(Graphics g) {
        view.draw(g);
        for (PegController[] row : pegControllers) {
            for (PegController pegController : row) {
                pegController.draw(g);
            }
        }
    }

    /**
     * Calls for updates.
     */
    @Override
    public void update() {
        for (PegController[] row : pegControllers) {
            for (PegController pegController : row) {
                pegController.update();
            }
        }
    }

    /**
     * Communicates with controllers when a mouse button has been pressed.
     *
     * @param e the {@link MouseEvent} containing the mouse press status
     */
    @Override
    public void mousePressed(MouseEvent e) {
        for (PegController[] row : pegControllers) {
            for (PegController pegController : row) {
                pegController.mousePressed(e);
            }
        }
    }

    /**
     * Communicates with controllers to set the {@link GameState} and any additional changes depending on the button
     * released.
     *
     * @param e the {@link MouseEvent} containing the mouse release status
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        for (PegController[] row : pegControllers) {
            for (PegController pegController : row) {
                pegController.mouseReleased(e);
            }
        }
    }

    /**
     * Checks when a mouse has been moved and detects where the mouse is in a {@link Panel}.
     *
     * @param e the {@link MouseEvent} containing the new mouse position
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        for (PegController[] row : pegControllers) {
            for (PegController pegController : row) {
                pegController.mouseMoved(e);
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}
