package scorefour.commands;

import scorefour.core.GameState;
import scorefour.interfaces.Command;

public class AddCommand implements Command {

    private String colour;
    private String position;

    @Override
    public boolean parse(String input) {

        if (!input.toLowerCase().startsWith("add ") || !input.endsWith(".")) {
            return false;
        }

        input = input.substring(0, input.length() - 1);

        String[] parts = input.split(" ");

        if (parts.length != 5) {
            return false;
        }

        if (!parts[0].equalsIgnoreCase("add") ||
            !parts[2].equalsIgnoreCase("bead") ||
            !parts[3].equalsIgnoreCase("to")) {
            return false;
        }

        colour = parts[1];
        position = parts[4];
        return true;
    }

    @Override
    public void execute(GameState gameState) {
        System.out.println("Adding " + colour + " bead to " + position);
        // Implement functionality here (game state could be replaced with board)
        // gameState.addBead(color, position);
    }

    @Override
    public String getHelp() {
        return "add [colour] bead to [position].            add a bead to a specified location";
    }
}
