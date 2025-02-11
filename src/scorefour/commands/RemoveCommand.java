package scorefour.commands;

import scorefour.core.GameState;
import scorefour.interfaces.Command;

public class RemoveCommand implements Command {

    private String position;

    @Override
    public boolean parse(String input) {

        if (!input.toLowerCase().startsWith("remove ") || !input.endsWith(".")) {
            return false;
        }

        input = input.substring(0, input.length() - 1);

        String[] parts = input.split(" ");

        if (parts.length != 4) {
            return false;
        }

        if (!parts[0].equalsIgnoreCase("remove") ||
                !parts[1].equalsIgnoreCase("bead") ||
                !parts[2].equalsIgnoreCase("from")) {
            return false;
        }

        position = parts[3];
        return true;
    }

    @Override
    public void execute(GameState gameState) {
        System.out.println("Removing bead from " + position);
        // Implement functionality here (game state could be replaced with board)
        // gameState.removeBead(position);
    }

    @Override
    public String getHelp() {
        return "remove bead from [position].                remove a bead from a specified location";
    }
}
