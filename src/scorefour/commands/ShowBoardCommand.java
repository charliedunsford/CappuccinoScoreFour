package scorefour.commands;

import scorefour.core.GameState;
import scorefour.interfaces.Command;

public class ShowBoardCommand implements Command {

    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("show board.");

    }

    @Override
    public void execute(GameState gameState) {
        System.out.println("Showing board.");
        // Implement functionality here (game state could be replaced with board)
        // gameState.showBoard(); or board.showBoard();
    }

    @Override
    public String getHelp() {
        return "show board.                                 shows a console representation of the board";
    }
}
