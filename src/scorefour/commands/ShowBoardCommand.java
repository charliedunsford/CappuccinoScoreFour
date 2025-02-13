package scorefour.commands;

import scorefour.core.Game;
import scorefour.common.Command;

public class ShowBoardCommand implements Command {

    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("show board.");

    }

    @Override
    public void execute(Game game) {
        System.out.println("Showing board.");
        // Implement functionality here
        // ActiveGame.showBoard(); or board.showBoard();
    }

    @Override
    public String getHelp() {
        return "show board.                                 shows a console representation of the board";
    }
}
