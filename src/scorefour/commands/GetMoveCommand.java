package scorefour.commands;

import scorefour.core.Game;
import scorefour.common.Command;

public class GetMoveCommand implements Command {
    private String colour;

    @Override
    public boolean parse(String input) {

        if (!input.toLowerCase().startsWith("get ") || !input.endsWith(".")) {
            return false;
        }

        input = input.substring(0, input.length() - 1);

        String[] parts = input.split(" ");

        if (parts.length != 3) {
            return false;
        }

        if (!parts[0].equalsIgnoreCase("get") ||
                !parts[2].equalsIgnoreCase("move")) {
            return false;
        }

        colour = parts[1];
        return true;
    }

    @Override
    public void execute(Game game) {
        System.out.println("Getting " + colour + " move.");
        // Implement functionality here
        // game.getPlayingGame().getBoard().getMoveBead(colour);
    }

    @Override
    public String getHelp() {
        return "get [colour] move.                          have the computer propose a move";
    }
}
