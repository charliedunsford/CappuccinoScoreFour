package scorefour.commands;

import scorefour.core.GameState;
import scorefour.interfaces.Command;

public class ClearCommand implements Command {

    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("clear.");

    }

    @Override
    public void execute(GameState gameState) {
        System.out.println("Clearing board.");
        // Implement functionality here (game state could be replaced with board)
        // gameState.clearBoard();
    }

    @Override
    public String getHelp() {
        return "clear.                                      clears the current board";
    }
}
