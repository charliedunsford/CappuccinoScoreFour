package scorefour.controller;

import scorefour.common.Updatable;
import scorefour.model.Board;
import scorefour.player.ComputerPlayer;
import scorefour.player.Player;
import scorefour.view.WinView;

import javax.swing.*;
import java.awt.*;

/**
 * A {@link GameManager} handles the game moves, scoreboard, and win checks.
 */
public class GameManager implements Updatable {

    private final Board board;
    private Player whitePlayer;
    private Player blackPlayer;
    private final WinManager winManager;

    private Timer winTimer;
    private Timer computerThinkTimer;
    private WinView winView;
    private Player currentPlayer;

    private int whiteScore, blackScore;
    private boolean gameWon;
    private boolean gameOver = false;

    private final AudioController audioController;

    /**
     * Constructs a {@link GameManager} to handle the game logic.
     *
     * @param board a {@link Board} to check for wins
     * @param whitePlayer a {@link Player}
     * @param blackPlayer a {@link Player}
     */
    public GameManager(Board board, Player whitePlayer, Player blackPlayer, AudioController audioController) {
        this.board = board;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.currentPlayer = whitePlayer;
        this.audioController = audioController;
        winManager = new WinManager(board);
        winTimer = null;
    }

    /**
     * Handles each move by checking if the game has been won, if not it switches
     * the turns.
     */
    public void handleMove() {
        if (gameOver) {
            return;
        }

        if (winManager.isGameWon()) {
            handleWin();
        } else {
            switchTurn();
        }
    }

    // Updates the score and displays the WinView if the game has been won.
    private void handleWin() {
        System.out.println(currentPlayer + " has won!");
        if (!gameWon) {
            if (currentPlayer == whitePlayer) {
                whiteScore++;
            } else {
                blackScore++;
            }
            gameWon = true;
        }

        if (winView == null) {
            winView = new WinView(currentPlayer);
        }

        winTimer = new Timer(2000, e -> {
            resetGame();
            if (currentPlayer instanceof ComputerPlayer) {
                computerTurn();
            }
        });
        winTimer.setRepeats(false);
        winTimer.start();

        gameOver = true;
    }

    // Switches the current player to the not current player.
    private void switchTurn() {
        currentPlayer = (currentPlayer == blackPlayer) ? whitePlayer : blackPlayer;
        if (currentPlayer instanceof ComputerPlayer) {
            computerTurn();
        }
    }

    public void computerTurn() {
        if (computerThinkTimer != null) {
            computerThinkTimer.stop();
        }

        computerThinkTimer = new Timer(1000, e -> {
            int[] move;
            do {
                move = ((ComputerPlayer) currentPlayer).getMove();
            } while (board.getPeg(move).isFull());
            board.addBead(move, currentPlayer.getColour());
            audioController.playEffect(AudioController.BEAD_FALLING);
            handleMove();
        });
        computerThinkTimer.setRepeats(false);
        computerThinkTimer.start();
    }

    /**
     * Returns the current {@link Player}.
     *
     * @return {@link Player}
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Returns the score as a list.
     *
     * @return {@code int[]}
     */
    public int[] getScore() {
        return new int[]{whiteScore, blackScore};
    }

    /**
     * Resets each players score to 0.
     */
    public void resetScore() {
        this.blackScore = 0;
        this.whiteScore = 0;
    }

    /**
     * Does a full reset of the game.
     */
    public void resetGame() {
        gameOver = false;
        gameWon = false;
        winView = null;

        if (winTimer != null) {
            winTimer.stop();
            winTimer = null;
        }

        board.clearBoard();
        currentPlayer = whitePlayer;

        if (currentPlayer instanceof ComputerPlayer) {
            computerTurn();
        }
    }

    public void updatePlayers(Player whitePlayer, Player blackPlayer) {
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;

        this.currentPlayer = whitePlayer;
    }

    /**
     * Resets the game and score when the score reaches a limit.
     */
    @Override
    public void update() {
        if (getScore()[0] > 99 || getScore()[1] > 99) {
            resetGame();
            resetScore();
        }
    }

    /**
     * Draws the {@link WinView}.
     *
     * @param g the {@link Graphics} context used for rendering
     */
    public void draw(Graphics g) {
        if (gameWon && winView != null) {
            winView.draw(g);
        }
    }

    public Board getBoard() {
        return board;
    }
}