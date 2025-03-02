package scorefour.commands;

import scorefour.core.Game;
import scorefour.common.Command;

/**
 * The {@code RemoveCommand} allows the user to remove a bead from a specified location, if it exists.
 */
public class RemoveCommand implements Command {

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

    /**
     * Executes the {@link Command} by sending the commands information to the
     * game instance.
     *
     * @param game the {@link Game} instance to interact with
     */
    @Override
    public void execute(Game game) {
        System.out.println("Removing bead from " + position);
        // Implement functionality here
        // game.getPlayingGame().getBoard().removeBead(position);
    }

    /**
     * A string which describes the use of the {@link Command}. This method will be
     * stored in a list to be called by a {@link scorefour.commands.HelpCommand}.
     *
     * @return a string description of a {@link Command}
     */
    @Override
    public String getHelp() {
        return "remove bead from [position].                remove a bead from a specified location";
    }
}
