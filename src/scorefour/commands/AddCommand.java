package scorefour.commands;

import scorefour.core.Game;
import scorefour.common.Command;

/**
 * The {@code AddCommand} allows the user to add a bead to a position, only if the move is valid.
 */
public class AddCommand implements Command {
    private String colour;
    private String position;

    /**
     * Parsing splits a string into sections and checks if the input the user entered is
     * a valid {@link Command}.
     *
     * @param input a string command
     * @return {@code true} if the string is a valid command, {@code false} otherwise
     */
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

    /**
     * Executes the {@link Command} by sending the commands information to the
     * game instance.
     *
     * @param game the {@link Game} instance to interact with
     */
    @Override
    public void execute(Game game) {
        System.out.println("Adding " + colour + " bead to " + position);
        // Implement functionality here
        // game.getPlayingGame().getBoard().addBead(color, position);
    }

    /**
     * A string which describes the use of the {@link Command}. This method will be
     * stored in a list to be called by a {@link scorefour.commands.HelpCommand}.
     *
     * @return a string description of a {@link Command}
     */
    @Override
    public String getHelp() {
        return "add [colour] bead to [position].            add a bead to a specified location";
    }
}
