package scorefour.commands;

import scorefour.core.GameState;
import scorefour.interfaces.Command;

public class DrawBoardCommand implements Command {

    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("draw board.");

    }

    @Override
    public void execute(GameState gameState) {
        System.out.println("Drawing board.");
        // Implement functionality here (game state could be replaced with board)
        // gameState.drawBoard(); or board.drawBoard();
    }

    @Override
    public String getHelp() {
        return "draw board.                                 shows a console drawing representation of the board";
    }
}
