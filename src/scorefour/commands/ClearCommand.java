package scorefour.commands;

import scorefour.core.Game;
import scorefour.common.Command;

public class ClearCommand implements Command {

    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("clear.");

    }

    @Override
    public void execute(Game game) {
        System.out.println("Clearing board.");
        // Implement functionality here (game state could be replaced with board)
        // ActiveGame.clearBoard();
    }

    @Override
    public String getHelp() {
        return "clear.                                      clears the current board";
    }
}
