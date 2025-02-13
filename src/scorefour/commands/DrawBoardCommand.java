package scorefour.commands;

import scorefour.core.Game;
import scorefour.common.Command;

public class DrawBoardCommand implements Command {

    @Override
    public boolean parse(String input) {
        return input.equalsIgnoreCase("draw board.");

    }

    @Override
    public void execute(Game game) {
        System.out.println("Drawing board.");
        // Implement functionality here
        // ActiveGame.drawBoard(); or board.drawBoard();
    }

    @Override
    public String getHelp() {
        return "draw board.                                 shows a console drawing representation of the board";
    }
}
