package scorefour.controller;

import scorefour.model.Board;
import scorefour.player.Player;

public class GameManager {

    private final Board board;
    private final Player whitePlayer;
    private final Player blackPlayer;
    private int whiteScore, blackScore;
    private final WinManager winManager;
    private Player currentPlayer;

    public GameManager(Board board, Player whitePlayer, Player blackPlayer) {
        this.board = board;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.currentPlayer = whitePlayer;
        winManager = new WinManager(board);
    }

    public void handleMove() {
        if (winManager.isGameWon()) {
            System.out.println(currentPlayer.toString() + " has won!");
            if (currentPlayer == whitePlayer) {
                whiteScore++;
            } else {
                blackScore++;
            }
        }

        if (board.isFull()) {
            System.out.println("Draw!");
        } else {
            switchTurn();
        }
    }

    private void switchTurn() {
        currentPlayer = (currentPlayer == blackPlayer) ? whitePlayer : blackPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int[] getScore() {
        return new int[]{whiteScore, blackScore};
    }

    public void resetScore() {
        this.blackScore = 0;
        this.whiteScore = 0;
    }
}